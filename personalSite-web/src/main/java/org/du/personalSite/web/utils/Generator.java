package org.du.personalSite.web.utils;

import org.du.personalSite.domain.vo.UserInfo;
import org.du.personalSite.utils.StringUtils;
import org.du.personalSite.web.controller.WebConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by 燃烧杯 on 2017/7/12.
 */
public class Generator {

    public static String generateSessionNum(HttpSession session){
        String sessionNum = (String) session.getAttribute(WebConstant.SESSIONUM);
        if ( StringUtils.isBlank(sessionNum) ){
            String uuid = UUID.randomUUID().toString();
            session.setAttribute(WebConstant.SESSIONUM, uuid);
            return uuid;
        } else {
            return sessionNum;
        }
    }

    //以ip地址为nickname，uuid会话编号为其他信息生成一个虚拟用户
    public static UserInfo generateUser(HttpServletRequest request, HttpSession session){
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname(request.getRemoteAddr());
        userInfo.setOtherPersonalInformation(generateSessionNum(session));
        userInfo.setRegistered(false);
        return userInfo;
    }
}
