package org.du.personalSite.web.controller;

import org.du.personalSite.domain.DomainConstant;
import org.du.personalSite.utils.TimeUtils;
import org.du.personalSite.web.vo.user.request.LoginCommit;
import org.du.personalSite.web.vo.response.LoginInfo;
import org.du.personalSite.domain.vo.UserInfo;
import org.du.personalSite.service.UserService;
import org.du.personalSite.web.imgserver.AuthImg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public @ResponseBody LoginInfo userLogin(@Validated @RequestBody LoginCommit commit,
                                             BindingResult aresult, HttpSession session
                                    , HttpServletRequest request) throws Exception{
        LoginInfo loginInfo = new LoginInfo();
          /*校验代码*/
        if ( aresult.hasErrors() ){
            String msg = aresult.getAllErrors().get(0).getDefaultMessage();
            loginInfo.setIslogined(false);
            loginInfo.setErrormsg(msg);
            return loginInfo;
        }

        Boolean islogined = (Boolean) session.getAttribute(WebConstant.ISLOGINED);

        //如果已经登录成功
        if ( islogined != null && islogined  == true ){
            String nickname = (String) session.getAttribute(WebConstant.NICKNAME);
            loginInfo.setNickname(nickname);
            loginInfo.setIslogined(islogined);
            return loginInfo;
        }
        if ( commit.getIniPage() != null && commit.getIniPage() == true ){
            loginInfo.setIslogined(false);
            loginInfo.setNickname(request.getRemoteAddr());
            return loginInfo;
        }

        //验证码不正确
        if ( !commit.getVcode().equals(session.getAttribute(AuthImg.RAND)) ){
            session.setAttribute(WebConstant.ISLOGINED, false);
            loginInfo.setIslogined(false);
            loginInfo.setErrormsg("验证码错误");
            logger.info("ip地址" + request.getRemoteAddr() + "验证码错误");
            return loginInfo;
        }

        UserInfo user = new UserInfo();
        user.setNickname(commit.getNickname());
        user.setPassword(commit.getPassword());
        user.setIp(request.getRemoteAddr());
        user.setLatestLoginTime(TimeUtils.getNowTime());
        int result;
        UserInfo outUser = userService.verifyUser(user);
        if ( outUser == null ){
            result = DomainConstant.LOGIN_FAIL;
        }else {
            result = DomainConstant.LOGIN_SUCCESS;
        }

        if ( result == DomainConstant.LOGIN_FAIL ){
            session.setAttribute(WebConstant.ISLOGINED, false);
            loginInfo.setIslogined(false);
            loginInfo.setErrormsg("用户名或者密码错误");
            logger.info("ip地址" + request.getRemoteAddr() + "用户名或者密码错误");
        }
        if ( result == DomainConstant.LOGIN_SUCCESS ){
            session.setAttribute(WebConstant.ISLOGINED, true);
            loginInfo.setIslogined(true);
            loginInfo.setNickname(user.getNickname());
            session.setAttribute(WebConstant.NICKNAME, outUser.getNickname());
            session.setAttribute(WebConstant.LEVEL, outUser.getLevel());
            session.setAttribute(WebConstant.USER, outUser);
            logger.info("用户" + user.getNickname() + "登录成功");
        }
        return loginInfo;
    }
}
