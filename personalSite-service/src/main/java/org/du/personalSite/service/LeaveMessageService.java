package org.du.personalSite.service;

import org.du.personalSite.domain.User;
import org.du.personalSite.domain.exception.PersonalSiteException;
import org.du.personalSite.domain.vo.LeaveMessageCustom;
import org.du.personalSite.domain.vo.UserInfo;

import java.util.List;

/**
 * Created by 燃烧杯 on 2017/7/12.
 */
public interface LeaveMessageService {
    LeaveMessageCustom saveLeaveMessage(String originalContent, String ip, String session,UserInfo user);

    List<LeaveMessageCustom> getAllLeaveMessageCustoms(User user);

    String getOriginalContent(long leaveMessageId) throws PersonalSiteException;

    String modifyLeaveMessage(User user, long leaveMessageId, String originalContent) throws PersonalSiteException;

    void deleteLeaveMessage(long leaveMessageId);
}
