package org.du.personalSite.web.vo.user.request;

import javax.validation.constraints.NotNull;

/**
 * Created by 燃烧杯 on 2017/10/30.
 */
public class LoginCommit {
    @NotNull(message = "{notnull}")
    private String nickname;
    @NotNull(message = "{notnull}")
    private String password;
    @NotNull(message = "{notnull}")
    private String vcode;

    private Boolean iniPage;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public Boolean getIniPage() {
        return iniPage;
    }

    public void setIniPage(Boolean iniPage) {
        this.iniPage = iniPage;
    }
}
