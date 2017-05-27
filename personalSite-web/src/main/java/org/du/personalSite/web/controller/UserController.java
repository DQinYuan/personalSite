package org.du.personalSite.web.controller;

import com.alibaba.fastjson.JSON;
import org.du.personalSite.domain.DomainConstant;
import org.du.personalSite.domain.User;
import org.du.personalSite.domain.vo.LoginInfo;
import org.du.personalSite.domain.vo.UserInfo;
import org.du.personalSite.service.UserService;
import org.du.personalSite.utils.StringUtils;
import org.du.personalSite.web.utils.AjaxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by duqinyuan on 2017/5/6.
 *
 * @author duqinyuan
 * @version 1.0
 */
@Controller
@RequestMapping("users")
public class UserController {
    @Resource
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public void userLogin(UserInfo user, String vcode, HttpSession session, HttpServletRequest request, HttpServletResponse response, Boolean iniPage) throws Exception{
        //返回json数据
        LoginInfo loginInfo = new LoginInfo();

        Boolean islogined = (Boolean) session.getAttribute("islogined");

        //如果已经登录成功
        if ( islogined != null && islogined  == true ){
            String nickname = (String) session.getAttribute("nickname");
            loginInfo.setNickname(nickname);
            loginInfo.setIslogined(islogined);
            AjaxUtils.reponseAjax(response, loginInfo);
            return;
        }
        if ( iniPage != null && iniPage == true ){
            return;
        }

        //验证码不正确
        if ( !vcode.equals(session.getAttribute("rand")) ){
            session.setAttribute("islogined", false);
            loginInfo.setIslogined(false);
            loginInfo.setErrormsg("验证码错误");
            AjaxUtils.reponseAjax(response, loginInfo);
            logger.info("ip地址" + request.getRemoteAddr() + "验证码错误");
            return;
        }

        //用户名或者密码不能为空
        if ( StringUtils.isBlank(user.getNickname()) || StringUtils.isBlank(user.getPassword()) ){
            session.setAttribute("islogined", false);
            loginInfo.setIslogined(false);
            loginInfo.setErrormsg("用户名或者密码不能为空");
            AjaxUtils.reponseAjax(response, loginInfo);
            logger.info("ip地址" + request.getRemoteAddr() + "用户名或者密码为空");
            return;
        }

        user.setIp(request.getRemoteAddr());
        user.setLatestLoginTime(new Date());
        int result;
        UserInfo outUser = userService.verifyUser(user);
        if ( outUser == null ){
            result = DomainConstant.LOGIN_FAIL;
        }else {
            result = DomainConstant.LOGIN_SUCCESS;
        }

        if ( result == DomainConstant.LOGIN_FAIL ){
            session.setAttribute("islogined", false);
            loginInfo.setIslogined(false);
            loginInfo.setErrormsg("用户名或者密码错误");
            logger.info("ip地址" + request.getRemoteAddr() + "用户名或者密码错误");
        }
        if ( result == DomainConstant.LOGIN_SUCCESS ){
            session.setAttribute("islogined", true);
            loginInfo.setIslogined(true);
            loginInfo.setNickname(user.getNickname());
            session.setAttribute("nickname", outUser.getNickname());
            session.setAttribute("level", outUser.getLevel());
            session.setAttribute("user", outUser);
            logger.info("用户" + user.getNickname() + "登录成功");
        }
        AjaxUtils.reponseAjax(response, loginInfo);
    }
}
