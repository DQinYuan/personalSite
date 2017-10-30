package org.du.personalSite.web.vo.comment.response;

/**
 * Created by 燃烧杯 on 2017/7/6.
 */
public class ModifyCommentInfo {
    Boolean isSuccess;

    String content;

    String errorInfo;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
