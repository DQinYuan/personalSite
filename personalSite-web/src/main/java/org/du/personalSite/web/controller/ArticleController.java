package org.du.personalSite.web.controller;

import org.du.personalSite.domain.vo.ArticleInfo;
import org.du.personalSite.web.vo.response.ArticleSubmitInfo;
import org.du.personalSite.domain.vo.UserInfo;
import org.du.personalSite.service.ArticleService;
import org.du.personalSite.utils.StringUtils;
import org.du.personalSite.web.utils.AjaxUtils;
import org.du.personalSite.web.utils.CheckUtils;
import org.du.personalSite.web.utils.MvUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public ModelAndView writeBlog(HttpSession session, String artTitle) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        if ( !CheckUtils.checkLoginAndLevel(session) ){
            modelAndView.setViewName("index");
        } else {
            if ( StringUtils.isNotBlank(artTitle) ){
                ArticleInfo articleInfo = articleService.getByTitle(artTitle);
                modelAndView.addObject("article", articleInfo);
            }
            modelAndView.setViewName("/privilegePages/write-blogs");
        }

        logger.info("用户" + session.getAttribute("nickname") +"进入写作系统");
        return modelAndView;
    }

    @RequestMapping("save-unpublished-blog")
    public void savaUnpublishedArt(HttpSession session, HttpServletResponse response, ArticleInfo articleInfo, Boolean iscovered) throws Exception{

        ArticleSubmitInfo articleSubmitInfo = new ArticleSubmitInfo();

        //检查登录与权限
        if ( !CheckUtils.checkLoginAndLevel(session) ){
            return;
        }

        //检查文章的title是否为空
        if ( StringUtils.isBlank(articleInfo.getTitle()) ){
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

            logger.info("用户" + session.getAttribute("nickname") +"成功保存Article" + articleInfo.getTitle());

            AjaxUtils.reponseAjax(response, articleSubmitInfo);
        } else {
            articleSubmitInfo.setSuccess(false);
            articleSubmitInfo.setShowcover(true);

            logger.info("用户" + session.getAttribute("nickname") +"申请覆盖Article" + articleInfo.getTitle());

            AjaxUtils.reponseAjax(response, articleSubmitInfo);
        }
    }

    @RequestMapping("getArticles")
    public void getArticles(Integer cateId,String pageNum  ,HttpServletResponse response) throws Exception{
        if ( !StringUtils.isNum(pageNum) ){
            return;
        }

        List<ArticleInfo> artList = articleService.getArticlesByPage(cateId, Integer.parseInt(pageNum));

        AjaxUtils.reponseAjax(response, artList);

    }

    @RequestMapping("readArticle")
    public ModelAndView readArticle(String articleId, HttpSession session) throws Exception {
        if ( !StringUtils.isNum(articleId) ){
            return MvUtils.getIllgalRequestMv();
        }

        UserInfo userInfo = (UserInfo) session.getAttribute(WebConstant.USER);
        ArticleInfo articleInfo = articleService.readArticle(userInfo, articleId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("article", articleInfo);
        mv.setViewName("content-page");
        return mv;
    }

    @RequestMapping("articleList")
    public ModelAndView articleList(HttpSession session) throws Exception{
        UserInfo userInfo = (UserInfo) session.getAttribute(WebConstant.USER);
        ModelAndView mv = new ModelAndView();
        if ( !CheckUtils.checkLoginAndLevel(session) ){
            mv.setViewName("index");
        } else {
            List<ArticleInfo> articleInfos = articleService.getAllArticlesByUser(userInfo);

            //数据填充
            mv.addObject("articleInfos", articleInfos);

            mv.setViewName("privilegePages/articleStatus");
        }

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
