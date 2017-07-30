package org.du.personalSite.web.vo.response;

/**
 * Created by duqinyuan on 2017/5/7.
 *
 * @author duqinyuan
 * @version 1.0
 */
public class LoginInfo {
    private String nickname;
    private Boolean islogined;
    private String errormsg;

    public LoginInfo() {
    }

    public LoginInfo(String nickname, Boolean islogined, String errormsg) {
        this.nickname = nickname;
        this.islogined = islogined;
        this.errormsg = errormsg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getIslogined() {
        return islogined;
    }

    public void setIslogined(Boolean islogined) {
        this.islogined = islogined;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }
}
