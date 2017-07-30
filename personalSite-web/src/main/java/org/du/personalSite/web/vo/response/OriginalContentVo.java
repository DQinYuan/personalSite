package org.du.personalSite.web.vo.response;

/**
 * Created by 燃烧杯 on 2017/7/3.
 */
public class OriginalContentVo {
    private Boolean isSuccess;

    private String originalContent;

    private String errorInfo;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getOriginalContent() {
        return originalContent;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public static OriginalContentVo getErrorEntity(String errorInfo){
        OriginalContentVo vo = new OriginalContentVo();
        vo.setIsSuccess(false);
        vo.setErrorInfo(errorInfo);
        return vo;
    }

    public static  OriginalContentVo getSuccessEntity(String originalContent){
        OriginalContentVo vo = new OriginalContentVo();
        vo.setIsSuccess(true);
        vo.setOriginalContent(originalContent);
        return vo;
    }
}
