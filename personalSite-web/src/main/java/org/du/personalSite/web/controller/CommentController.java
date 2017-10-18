package org.du.personalSite.web.controller;

import org.du.personalSite.domain.Article;
import org.du.personalSite.domain.exception.PersonalSiteException;
import org.du.personalSite.domain.vo.CommentCustom;
import org.du.personalSite.domain.vo.CommentInfo;
import org.du.personalSite.service.ArticleService;
import org.du.personalSite.web.utils.CheckUtils;
import org.du.personalSite.web.utils.Generator;
import org.du.personalSite.web.utils.MvUtils;
import org.du.personalSite.web.vo.response.CommentSubmitInfo;
import org.du.personalSite.domain.vo.UserInfo;
import org.du.personalSite.service.CommentService;
import org.du.personalSite.utils.MyStringUtils;
import org.du.personalSite.web.utils.AjaxUtils;
import org.du.personalSite.web.vo.response.OriginalContentVo;
import org.du.personalSite.web.vo.response.DeleteCommentInfo;
import org.du.personalSite.web.vo.response.ModifyCommentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "saveComments", method = RequestMethod.POST)
    public void saveComments(CommentInfo commentInfo,
                             HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception{
        CommentSubmitInfo commentSubmitInfo = new CommentSubmitInfo();

        if ( commentInfo.getArticleId() == null ){
            commentSubmitInfo.setIsSuccess(false);
            commentSubmitInfo.setErrorInfo("缺少请求参数，无法评论");
            AjaxUtils.reponseAjax(response, commentSubmitInfo);
            return;
        }

        if ( MyStringUtils.isBlank(commentInfo.getOriginalContent()) ){
            commentSubmitInfo.setIsSuccess(false);
            commentSubmitInfo.setErrorInfo("评论内容不能为空");
            AjaxUtils.reponseAjax(response, commentSubmitInfo);
            return;
        }

        UserInfo userInfo = (UserInfo) session.getAttribute(WebConstant.USER);

        //如果上句userInfo为null话，则生成以ip地址为昵称，uuid为其他信息的不入库的虚拟用户，
        if ( userInfo == null ){
            userInfo = Generator.generateUser(request, session);
            session.setAttribute(WebConstant.USER, userInfo);
            session.setAttribute(WebConstant.NICKNAME, userInfo.getNickname());
        }

        commentService.saveComment(commentInfo, userInfo.getId(), request.getRemoteAddr(),
                Generator.generateSessionNum(session));

        logger.info("用户" + userInfo.getNickname() + "评论文章" + commentInfo.getArticleId() + "成功");

        commentSubmitInfo.setIsSuccess(true);
        AjaxUtils.reponseAjax(response, commentSubmitInfo);
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

    @RequestMapping(value = "modifyComments", method = RequestMethod.POST)
    public void modifyComments(String commentId, String newOriginalContent,
                               HttpServletResponse response, HttpSession session)
    throws Exception{
        ModifyCommentInfo info = new ModifyCommentInfo();

        if ( MyStringUtils.isBlank(commentId) || !MyStringUtils.isNum(commentId) ){
            info.setIsSuccess(false);
            info.setErrorInfo("参数格式错误");
            AjaxUtils.reponseAjax(response, info);
            return;
        }

        if ( MyStringUtils.isBlank(newOriginalContent) ){
            info.setIsSuccess(false);
            info.setErrorInfo("评论内容不能为空");
            AjaxUtils.reponseAjax(response, info);
            return;
        }

        UserInfo userInfo = (UserInfo) session.getAttribute(WebConstant.USER);

        try {
            commentService.modifyComment(userInfo, Long.parseLong(commentId), newOriginalContent);
            info.setIsSuccess(true);
            AjaxUtils.reponseAjax(response, info);
        } catch (PersonalSiteException e){
            info.setIsSuccess(false);
            info.setErrorInfo(e.getMessage());
            AjaxUtils.reponseAjax(response, info);
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
        mv.setViewName("privilegePages/commentsList");
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
