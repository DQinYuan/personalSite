package org.du.personalSite.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by duqinyuan on 2017/5/6.
 *
 * @author duqinyuan
 * @version 1.0
 */
@Entity
@Table(name = "user_inf")
public class User implements Serializable{
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ip;   //上一次登录的ip

    @Column(name = "latest_login_time")
    private Date latestLoginTime;         //上一次的登录时间

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column
    private String realname;

    @Column(name = "create_time", nullable = false)
    private Date createTime;               //该用户第一次被存到数据库中的时间

    @Column(name = "is_registered", nullable = false)
    private Boolean isRegistered;

    @Column(nullable = false)
    private Integer level;    //权限等级 0最高

    @Column(name = "other_personal_inf")
    private String otherPersonalInformation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLatestLoginTime() {
        return latestLoginTime;
    }

    public void setLatestLoginTime(Date latestLoginTime) {
        this.latestLoginTime = latestLoginTime;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getOtherPersonalInformation() {
        return otherPersonalInformation;
    }

    public void setOtherPersonalInformation(String otherPersonalInformation) {
        this.otherPersonalInformation = otherPersonalInformation;
    }
}
