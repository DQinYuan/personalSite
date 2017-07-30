package org.du.personalSite.web.vo.response.leavemessage;

import org.du.personalSite.domain.vo.LeaveMessageCustom;

/**
 * Created by 燃烧杯 on 2017/7/12.
 */
public class SaveLeaveMessagesInfo {
    Boolean isSuccess;
    String errorInfo;
    LeaveMessageCustom custom;

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

    public LeaveMessageCustom getCustom() {
        return custom;
    }

    public void setCustom(LeaveMessageCustom custom) {
        this.custom = custom;
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
        info.setCustom(custom);
        return info;
    }
}
