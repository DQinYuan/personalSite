package org.du.personalSite.web.utils;

import org.du.personalSite.domain.DomainConstant;

import javax.servlet.http.HttpSession;

/**
 * Created by duqinyuan on 2017/5/11.
 *
 * @author duqinyuan
 * @version 1.0
 */
public class CheckUtils {
    //检测访问用户是否具有高权限，具有高权限返回true
    public static boolean checkLoginAndLevel(HttpSession session){
        Integer userlevel = (Integer) session.getAttribute("level");
        if ( userlevel == null || userlevel != DomainConstant.HIGH_LEVEL_USER){
            return false;
        }

        return true;
    }
}
