package org.du.personalSite.service.impl;

import org.du.personalSite.dao.LeaveMessageDao;
import org.du.personalSite.domain.LeaveMessage;
import org.du.personalSite.domain.User;
import org.du.personalSite.domain.exception.PersonalSiteException;
import org.du.personalSite.domain.vo.LeaveMessageCustom;
import org.du.personalSite.domain.vo.UserInfo;
import org.du.personalSite.service.LeaveMessageService;
import org.du.personalSite.service.base.MarkdownResolver;
import org.du.personalSite.service.base.customer.LeaveMessageCustomer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by 燃烧杯 on 2017/7/12.
 */
@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {

    @Resource
    LeaveMessageDao leaveMessageDao;

    @Resource
    MarkdownResolver markdownResolver;


    @Transactional
    public LeaveMessageCustom saveLeaveMessage(String originalContent, String ip
            , String session, UserInfo user) {

        LeaveMessage leaveMessage = new LeaveMessage();
        leaveMessage.generateContent(originalContent);
        leaveMessage.setIp(ip);
        leaveMessage.setSession(session);
        leaveMessage.setCreateTime(new Date());
        leaveMessage.setLastModifiedTime(new Date());

        if ( user.getRegistered() ){
            leaveMessage.setOwner(user.getId());
        }

        leaveMessageDao.save(leaveMessage);

        return LeaveMessageCustomer.custom(leaveMessage, user);
    }

    @Transactional(readOnly = true)
    public List<LeaveMessageCustom> getAllLeaveMessageCustoms(User user) {
        List<LeaveMessage> leaveMessages = leaveMessageDao.findAll(LeaveMessage.class);
        return LeaveMessageCustomer.batchCustom(leaveMessages, user);
    }

    @Transactional(readOnly = true)
    public String getOriginalContent(long leaveMessageId) throws PersonalSiteException {
        LeaveMessage leaveMessage = leaveMessageDao.get(LeaveMessage.class, leaveMessageId);
        if ( leaveMessage == null ){
            throw new PersonalSiteException("该留言不存在");
        }
        return leaveMessage.getOriginalContent();
    }

    @Transactional
    public String modifyLeaveMessage(User user, long leaveMessageId, String originalContent)
            throws PersonalSiteException {
        LeaveMessage leaveMessage = leaveMessageDao.get(LeaveMessage.class, leaveMessageId);
        if ( leaveMessage == null ){
            throw new PersonalSiteException("该留言不存在");
        }
        if ( !leaveMessage.canModify(user) ){
            throw new PersonalSiteException("您没有修改该留言的权限");
        }

        leaveMessage.generateContent(originalContent);
        leaveMessage.setLastModifiedTime(new Date());

        leaveMessageDao.update(leaveMessage);

        return leaveMessage.getContent();
    }

    @Transactional
    public void deleteLeaveMessage(long leaveMessageId) {
        leaveMessageDao.delete(LeaveMessage.class, leaveMessageId);
    }
}
