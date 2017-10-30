package org.du.personalSite.web.vo.leavemessage.response;

import org.du.personalSite.domain.vo.LeaveMessageCustom;

/**
 * Created by 燃烧杯 on 2017/7/12.
 */
public class SaveLeaveMessagesInfo {
    Boolean isSuccess;
    String content;
    Long leaveMessageId;
    String errorInfo;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getLeaveMessageId() {
        return leaveMessageId;
    }

    public void setLeaveMessageId(Long leaveMessageId) {
        this.leaveMessageId = leaveMessageId;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public static SaveLeaveMessagesInfo getErrorEntity(String errorInfo){
        SaveLeaveMessagesInfo info = new SaveLeaveMessagesInfo();
        info.setIsSuccess(false);
        info.setErrorInfo(errorInfo);
        return info;
    }

    public static SaveLeaveMessagesInfo getSuccessEntity(LeaveMessageCustom custom){
        SaveLeaveMessagesInfo info = new SaveLeaveMessagesInfo();
        info.setIsSuccess(true);
        info.setContent(custom.getLeaveMessage().getContent());
        info.setLeaveMessageId(custom.getLeaveMessage().getId());
        return info;
    }
}
