package org.du.personalSite.web.controller;

import com.alibaba.fastjson.JSON;
import org.du.personalSite.domain.vo.ArticleInfo;
import org.du.personalSite.domain.vo.ArticleSubmitInfo;
import org.du.personalSite.domain.vo.UserInfo;
import org.du.personalSite.service.ArticleService;
import org.du.personalSite.service.ServiceConstant;
import org.du.personalSite.utils.StringUtils;
import org.du.personalSite.web.utils.CheckUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public ModelAndView writeBlog(HttpSession session) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        if ( !CheckUtils.checkLoginAndLevel(session) ){
            modelAndView.setViewName("index");
        } else {
            modelAndView.setViewName("/privilegePages/write-blogs");
            logger.info("用户" + session.getAttribute("nickname") +"进入写作系统");
        }
        return modelAndView;
    }

    @RequestMapping("save-unpublished-blog")
    public void savaUnpublishedArt(HttpSession session, HttpServletResponse response, ArticleInfo articleInfo, Boolean iscovered) throws Exception{

        ArticleSubmitInfo articleSubmitInfo = new ArticleSubmitInfo();
        response.setContentType("application/json;charset=UTF-8");

        //检查登录与权限
        if ( !CheckUtils.checkLoginAndLevel(session) ){
            return;
        }

        //检查文章的title是否为空
        if ( StringUtils.isBlank(articleInfo.getTitle()) ){
            articleSubmitInfo.setSuccess(false);
            articleSubmitInfo.setMsg("文章标题不能为空");
            articleSubmitInfo.setShowcover(false);

            response.getWriter().write(JSON.toJSONString(articleSubmitInfo));
            return;
        }

        articleInfo.setOwner((UserInfo) session.getAttribute("user"));
        articleInfo.setPublished(false);

        Boolean saveResult = articleService.saveUnpublishedArt(articleInfo, iscovered);

        if (saveResult){
            articleSubmitInfo.setMsg("文章保存成功");
            articleSubmitInfo.setSuccess(true);
            articleSubmitInfo.setShowcover(false);


            logger.info("用户" + session.getAttribute("nickname") +"成功保存Article" + articleInfo.getTitle());

            response.getWriter().write(JSON.toJSONString(articleSubmitInfo));
        } else {
            articleSubmitInfo.setSuccess(false);
            articleSubmitInfo.setShowcover(true);

            logger.info("用户" + session.getAttribute("nickname") +"申请覆盖Article" + articleInfo.getTitle());

            response.getWriter().write(JSON.toJSONString(articleSubmitInfo));
        }



    }

}
