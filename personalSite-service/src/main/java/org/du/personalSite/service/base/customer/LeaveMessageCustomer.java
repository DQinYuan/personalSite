package org.du.personalSite.service.base.customer;

import org.du.personalSite.dao.UserDao;
import org.du.personalSite.domain.LeaveMessage;
import org.du.personalSite.domain.User;
import org.du.personalSite.domain.utils.Registry;
import org.du.personalSite.domain.vo.LeaveMessageCustom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 燃烧杯 on 2017/7/12.
 */
public class LeaveMessageCustomer {

    public static LeaveMessageCustom custom(LeaveMessage leaveMessage, User user){
        LeaveMessageCustom custom = new LeaveMessageCustom();

        custom.setCanModified(leaveMessage.canModify(user));
        custom.setLeaveMessage(leaveMessage);

        if ( leaveMessage.getOwner() != null ){
            User owner = Registry.query(UserDao.class).get(User.class, leaveMessage.getOwner());
            custom.setUser(owner);
        }

        return custom;
    }

    public static List<LeaveMessageCustom> batchCustom(List<LeaveMessage> leaveMessages, User user) {
        List<LeaveMessageCustom> customs = new ArrayList<LeaveMessageCustom>();
        for ( LeaveMessage leaveMessage : leaveMessages ){
            customs.add(custom(leaveMessage, user));
        }
        return customs;
    }
}
