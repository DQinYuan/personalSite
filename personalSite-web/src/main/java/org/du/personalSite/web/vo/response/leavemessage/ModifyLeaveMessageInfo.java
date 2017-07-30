package org.du.personalSite.web.vo.response.leavemessage;

/**
 * Created by 燃烧杯 on 2017/7/13.
 */
public class ModifyLeaveMessageInfo {
    boolean isSuccess;
    String errorInfo;
    String content;

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static ModifyLeaveMessageInfo getErrorEntity(String errorInfo){
        ModifyLeaveMessageInfo info = new ModifyLeaveMessageInfo();
        info.setIsSuccess(false);
        info.setErrorInfo(errorInfo);
        return info;
    }

    public static ModifyLeaveMessageInfo getSuccessEntity(String content){
        ModifyLeaveMessageInfo info = new ModifyLeaveMessageInfo();
        info.setIsSuccess(true);
        info.setContent(content);
        return info;
    }
}
