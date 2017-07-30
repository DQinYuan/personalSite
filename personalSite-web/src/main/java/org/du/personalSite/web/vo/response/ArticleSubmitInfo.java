package org.du.personalSite.web.vo.response;

/**
 * Created by duqinyuan on 2017/5/11.
 *
 * @author duqinyuan
 * @version 1.0
 */
public class ArticleSubmitInfo {
    Boolean isSuccess;      //是否提交成功
    String msg;        //信息
    Boolean showcover;          //是否命令客户端弹出覆盖对话框

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getShowcover() {
        return showcover;
    }

    public void setShowcover(Boolean showcover) {
        this.showcover = showcover;
    }
}
