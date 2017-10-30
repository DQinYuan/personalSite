package org.du.personalSite.web.vo;

import org.du.personalSite.domain.vo.CommentCustom;
import org.du.personalSite.domain.vo.LeaveMessageCustom;

/**
 * Created by 燃烧杯 on 2017/10/28.
 */
public class ConventionLogic {

    public static String customUserName(CommentCustom custom, long artOwnerId){
        if ( custom.getUser() == null ){
            return custom.getComment().getIp();
        }
        long commentUserId = custom.getUser().getId();
        if ( commentUserId == artOwnerId ) {
            return "作者";
        } else {
            return custom.getUser().getNickname();
        }
    }

    public static String customUserName(LeaveMessageCustom custom){
        return custom.getUser() == null ? custom.getLeaveMessage().getIp() : custom.getUser().getNickname();
    }

    public static Long str2Long(String lon){
        return lon == null ? null : Long.parseLong(lon);
    }

}
