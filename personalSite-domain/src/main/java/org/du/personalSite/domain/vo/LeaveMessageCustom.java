package org.du.personalSite.domain.vo;

import org.du.personalSite.domain.LeaveMessage;
import org.du.personalSite.domain.User;

/**
 * Created by 燃烧杯 on 2017/7/12.
 */
public class LeaveMessageCustom {
    LeaveMessage leaveMessage;
    User user;
    Boolean canModified;

    public LeaveMessage getLeaveMessage() {
        return leaveMessage;
    }

    public void setLeaveMessage(LeaveMessage leaveMessage) {
        this.leaveMessage = leaveMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getCanModified() {
        return canModified;
    }

    public void setCanModified(Boolean canModified) {
        this.canModified = canModified;
    }
}
