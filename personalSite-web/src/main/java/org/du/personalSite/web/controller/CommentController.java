package org.du.personalSite.web.controller;

import org.du.personalSite.domain.Article;
import org.du.personalSite.domain.Comment;
import org.du.personalSite.domain.exception.PersonalSiteException;
import org.du.personalSite.domain.vo.CommentCustom;
import org.du.personalSite.domain.vo.CommentInfo;
import org.du.personalSite.service.ArticleService;
import org.du.personalSite.web.utils.CheckUtils;
import org.du.personalSite.web.utils.Generator;
import org.du.personalSite.web.utils.MvUtils;
import org.du.personalSite.domain.vo.UserInfo;
import org.du.personalSite.service.CommentService;
import org.du.personalSite.utils.MyStringUtils;
import org.du.personalSite.web.utils.AjaxUtils;
import org.du.personalSite.web.vo.ConventionLogic;
import org.du.personalSite.web.vo.comment.request.CommentCommit;
import org.du.personalSite.web.vo.comment.request.ModifyCommentCommit;
import org.du.personalSite.web.vo.comment.response.CommentSubmitInfo;
import org.du.personalSite.web.vo.comment.response.ModifyCommentInfo;
import org.du.personalSite.web.vo.response.OriginalContentVo;
import org.du.personalSite.web.vo.response.DeleteCommentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 燃烧杯 on 2017/6/24.
 */
@Controller
@RequestMapping("comments")
public class CommentController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    CommentService commentService;

    @Resource
    ArticleService articleService;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody CommentSubmitInfo saveComments(@Validated @RequestBody CommentCommit commit,
                                                        BindingResult result,
                                   HttpSession session, HttpServletRequest request) throws Exception{
        CommentSubmitInfo commentSubmitInfo = new CommentSubmitInfo();

        /*校验代码*/
        if ( result.hasErrors() ){
            String msg = result.getAllErrors().get(0).getDefaultMessage();
            commentSubmitInfo.setIsSuccess(false);
            commentSubmitInfo.setErrorInfo(msg);
            return commentSubmitInfo;
        }

        if ( !MyStringUtils.isNum(commit.getArticleId()) ){
            commentSubmitInfo.setIsSuccess(false);
            commentSubmitInfo.setErrorInfo("该文章不存在");
            return commentSubmitInfo;
        }

        if ( commit.getResponseCommentId() != null &&
                !MyStringUtils.isNum(commit.getResponseCommentId()) ){
            commentSubmitInfo.setIsSuccess(false);
            commentSubmitInfo.setErrorInfo("参数格式不对");
            return commentSubmitInfo;
        }

        UserInfo userInfo = (UserInfo) session.getAttribute(WebConstant.USER);

        //如果上句userInfo为null话，则生成以ip地址为昵称，uuid为其他信息的不入库的虚拟用户，
        if ( userInfo == null ){
            userInfo = Generator.generateUser(request, session);
            session.setAttribute(WebConstant.USER, userInfo);
            session.setAttribute(WebConstant.NICKNAME, userInfo.getNickname());
        }

        CommentInfo commentInfo = new CommentInfo();
        commentInfo.setArticleId(Long.parseLong(commit.getArticleId()));
        commentInfo.setOriginalContent(commit.getOriginalContent());
        commentInfo.setResponseCommentId(ConventionLogic.str2Long(commit.getResponseCommentId()));

        Comment dbInfo =  commentService.saveComment(commentInfo, userInfo.getId(),
                request.getRemoteAddr(), Generator.generateSessionNum(session));

        logger.info("用户" + userInfo.getNickname() + "评论文章" + commentInfo.getArticleId() + "成功");

        commentSubmitInfo.setIsSuccess(true);
        commentSubmitInfo.setContent(dbInfo.getContent());
        commentSubmitInfo.setCommentId(dbInfo.getId());
        return commentSubmitInfo;
    }

    @RequestMapping("originalContent")
    public void getOriginalContent(String commentId, HttpServletResponse response) throws Exception{

        if ( !MyStringUtils.isNum(commentId) ){
            AjaxUtils.reponseAjax(response, OriginalContentVo.getErrorEntity("参数格式错误"));
            return;
        }

        String originalContent = commentService.getOriginalContent(Long.parseLong(commentId));
        if ( MyStringUtils.isBlank(originalContent) ){
            AjaxUtils.reponseAjax(response, OriginalContentVo.getErrorEntity("该评论不存在"));
            return;
        }

        AjaxUtils.reponseAjax(response, OriginalContentVo.getSuccessEntity(originalContent));
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.PUT)
    public @ResponseBody
    ModifyCommentInfo modifyComments(@PathVariable("commentId") String commentId
            , @Validated @RequestBody ModifyCommentCommit commit , BindingResult result
                                     , HttpSession session)
    throws Exception{
        ModifyCommentInfo info = new ModifyCommentInfo();

        /*校验代码*/
        if ( result.hasErrors() ){
            String msg = result.getAllErrors().get(0).getDefaultMessage();
            info.setIsSuccess(false);
            info.setErrorInfo(msg);
            return info;
        }

        if ( !MyStringUtils.isNum(commentId) ){
            info.setIsSuccess(false);
            info.setErrorInfo("该评论不存在");
            return info;
        }

        UserInfo userInfo = (UserInfo) session.getAttribute(WebConstant.USER);

        try {
            String content = commentService.modifyComment(userInfo, Long.parseLong(commentId),
                    commit.getNewOriginalContent());
            info.setIsSuccess(true);
            info.setContent(content);
            return info;
        } catch (PersonalSiteException e){
            info.setIsSuccess(false);
            info.setErrorInfo(e.getMessage());
            return info;
        }

    }

    @RequestMapping("commentsList")
    public ModelAndView commentsList(String id, HttpSession session){
        if (!CheckUtils.checkLoginAndLevel(session) || !MyStringUtils.isNum(id)){
            return MvUtils.getIllgalRequestMv();
        }

        UserInfo userInfo = (UserInfo) session.getAttribute(WebConstant.USER);
        List<CommentCustom> commentCustoms;
        Article article;
        try {
            article = articleService.getById(Long.parseLong(id));
            commentCustoms = commentService.getAllCommentsBelongToArt(userInfo, article.getTitle());
        } catch (PersonalSiteException e){
            return  MvUtils.getIllgalRequestMv();
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("commentCustoms", commentCustoms);
        mv.addObject("title", article.getTitle());
        mv.addObject("article", article);
        mv.setViewName("/WEB-INF/jsp/privilegePages/commentsList.jsp");
        return mv;
    }

    @RequestMapping(value = "deleteComment", method = RequestMethod.POST)
    public void deleteComment(String commentId, HttpSession session, HttpServletResponse response){
        DeleteCommentInfo info = new DeleteCommentInfo();

        if ( !MyStringUtils.isNum(commentId) ){
            info.setIsSuccess(false);
            info.setErrorInfo("非法请求");
            AjaxUtils.reponseAjax(response, info);
            return;
        }

        if ( !CheckUtils.checkLoginAndLevel(session) ){
            info.setIsSuccess(false);
            info.setErrorInfo("权限不足");
            AjaxUtils.reponseAjax(response, info);
            return;
        }

        try {
            UserInfo user = (UserInfo)session.getAttribute(WebConstant.USER);

            commentService.deleteUserComment(user, Long.parseLong(commentId));
            info.setIsSuccess(true);
            logger.info("用户" + user.getNickname() + "删除评论" + commentId + "成功");
            AjaxUtils.reponseAjax(response, info);
        } catch (PersonalSiteException e){
            info.setIsSuccess(false);
            info.setErrorInfo(e.getMessage());
            AjaxUtils.reponseAjax(response, info);
        }

    }
}
