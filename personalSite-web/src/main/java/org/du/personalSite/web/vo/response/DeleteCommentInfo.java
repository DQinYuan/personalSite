package org.du.personalSite.web.vo.response;

/**
 * Created by 燃烧杯 on 2017/7/7.
 */
public class DeleteCommentInfo {
    private Boolean isSuccess;
    private String errorInfo;

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
}
