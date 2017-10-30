package org.du.personalSite.web.controller;

import org.du.personalSite.domain.vo.ArticleInfo;
import org.du.personalSite.web.vo.article.response.ArticleVo;
import org.du.personalSite.web.vo.comment.response.CommentVo;
import org.du.personalSite.web.vo.response.ArticleSubmitInfo;
import org.du.personalSite.domain.vo.UserInfo;
import org.du.personalSite.service.ArticleService;
import org.du.personalSite.utils.MyStringUtils;
import org.du.personalSite.web.utils.AjaxUtils;
import org.du.personalSite.web.utils.CheckUtils;
import org.du.personalSite.web.utils.MvUtils;
import org.du.personalSite.web.vo.article.response.ArtTitleAndAbs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duqinyuan on 2017/5/10.
 *
 * @author duqinyuan
 * @version 1.0
 */
@Controller
@RequestMapping("articles")
public class ArticleController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    ArticleService articleService;

    @RequestMapping("write-blogs")
    public ModelAndView writeBlog(HttpSession session, String id) throws Exception{

        if ( !CheckUtils.checkLoginAndLevel(session) ){
            return MvUtils.getIllgalRequestMv();
        }
        ModelAndView modelAndView = new ModelAndView();
        if ( MyStringUtils.isNotBlank(id) && MyStringUtils.isNum(id) ){
            ArticleInfo articleInfo = articleService.getById(Long.parseLong(id));
            modelAndView.addObject("article", articleInfo);
        }
        modelAndView.setViewName("/WEB-INF/jsp/privilegePages/write-blogs.jsp");


        logger.info("用户" + session.getAttribute("nickname") +"进入写作系统");
        return modelAndView;
    }

    @RequestMapping("save-unpublished-blog")
    public void savaUnpublishedArt(HttpSession session, HttpServletResponse response,
                                   ArticleInfo articleInfo, Boolean iscovered) throws Exception{

        ArticleSubmitInfo articleSubmitInfo = new ArticleSubmitInfo();

        //检查登录与权限
        if ( !CheckUtils.checkLoginAndLevel(session) ){
            return;
        }

        //检查文章的title是否为空
        if ( MyStringUtils.isBlank(articleInfo.getTitle()) ){
            articleSubmitInfo.setSuccess(false);
            articleSubmitInfo.setMsg("文章标题不能为空");
            articleSubmitInfo.setShowcover(false);

            AjaxUtils.reponseAjax(response, articleSubmitInfo);
            return;
        }

        articleInfo.setOwner((UserInfo) session.getAttribute(WebConstant.USER));

        Boolean saveResult = articleService.saveUnpublishedArt(articleInfo, iscovered);

        if (saveResult){
            articleSubmitInfo.setMsg("文章保存成功");
            articleSubmitInfo.setSuccess(true);
            articleSubmitInfo.setShowcover(false);

            logger.info("用户" + session.getAttribute("nickname") +"成功保存Article"
                    + articleInfo.getTitle());

            AjaxUtils.reponseAjax(response, articleSubmitInfo);
        } else {
            articleSubmitInfo.setSuccess(false);
            articleSubmitInfo.setShowcover(true);

            logger.info("用户" + session.getAttribute("nickname") +"申请覆盖Article"
                    + articleInfo.getTitle());

            AjaxUtils.reponseAjax(response, articleSubmitInfo);
        }
    }

    @RequestMapping(value = "/{cateId}/{pageNum}", method = RequestMethod.GET)
    public @ResponseBody List<ArtTitleAndAbs> getArticles(@PathVariable("cateId") String cateId,
                                     @PathVariable("pageNum") String pageNum)
            throws Exception{
        if ( !MyStringUtils.isNum(pageNum) || !MyStringUtils.isNum(cateId) ){
            return new ArrayList<ArtTitleAndAbs>();
        }

        List<ArticleInfo> artList = articleService.getAllArticles(Integer.parseInt(cateId));

       return ArtTitleAndAbs.infos2its(artList);
    }

    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    public @ResponseBody
    ArticleVo readArticle(@PathVariable("articleId") String articleId
            , HttpSession session) throws Exception {
        if ( !MyStringUtils.isNum(articleId) ){
            ArticleVo dummyVo = new ArticleVo();
            dummyVo.setBrowseTimes(0L);
            dummyVo.setLatestModifTime("0000-00-00");
            dummyVo.setTitle("该文章尚未发表");
            dummyVo.setContent("该文章尚未发表");
            dummyVo.setComments(new ArrayList<CommentVo>());
            return dummyVo;
        }

        UserInfo userInfo = (UserInfo) session.getAttribute(WebConstant.USER);
        ArticleInfo articleInfo = articleService.readArticle(userInfo, articleId);
        return ArticleVo.info2Vo(articleInfo);
    }

    @RequestMapping("articleList")
    public ModelAndView articleList(HttpSession session) throws Exception{
        UserInfo userInfo = (UserInfo) session.getAttribute(WebConstant.USER);

        if ( !CheckUtils.checkLoginAndLevel(session) ){
            return MvUtils.getIllgalRequestMv();
        }

        ModelAndView mv = new ModelAndView();
        List<ArticleInfo> articleInfos = articleService.getAllArticlesByUser(userInfo);

        //数据填充
        mv.addObject("articleInfos", articleInfos);

        mv.setViewName("/WEB-INF/jsp/privilegePages/articleStatus.jsp");


        return mv;
    }

    @RequestMapping("publishToggle")
    public void publishToggle(String articleId, HttpServletResponse response, HttpSession session) throws Exception{
        if ( !CheckUtils.checkLoginAndLevel(session) ){
            return;
        }

        boolean result =  articleService.publishToggle(articleId);
        AjaxUtils.reponseAjax(response, result);
    }

}
