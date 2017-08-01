package org.du.personalSite.domain;

import org.apache.commons.lang3.StringEscapeUtils;
import org.du.personalSite.domain.utils.MarkdowmInter;
import org.du.personalSite.domain.utils.Registry;
import org.du.personalSite.utils.TimeUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 燃烧杯 on 2017/7/12.
 */
@Entity
@Table(name = "leave_message_inf", indexes = @Index(columnList = "latest_modified_time"))
public class LeaveMessage {
    @Id
    @Column(name = "leave_message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String ip;

    @Lob
    @Column(length = 16777215, name = "original_content", nullable = false)
    @Basic(fetch = FetchType.LAZY)
    String originalContent;

    @Lob
    @Column(length = 16777215, nullable = false)
    @Basic(fetch = FetchType.LAZY)
    String content;

    @Column
    Long owner;

    @Column
    String session;        //发表的留言时所处的会话，使用uuid

    @Column(name = "create_time", nullable = false)
    Date createTime;

    @Column(name = "latest_modified_time", nullable = false)
    Date latestModifTime;

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

    public String getOriginalContent() {
        return originalContent;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifiedTime() {
        return latestModifTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.latestModifTime = lastModifiedTime;
    }

    public boolean canModify(User user){
        if ( user == null ){
            return false;
        }
        if ( user.isVisual() ){
            if ( !user.getOtherPersonalInformation().equals(getSession()) ){
                return false;
            }
        } else {
            if ( user.getId() != getOwner() ){
                return false;
            }
        }
        if ( TimeUtils.getNowTime().getTime() - getCreateTime().getTime() > DomainConstant.CAN_MODIFY_TIME ){
            return false;
        }
        return true;
    }

    public void generateContent(String originalContent){
        setOriginalContent(originalContent);
        String escapedStr = StringEscapeUtils.escapeHtml4(originalContent);
        setContent(Registry.query(MarkdowmInter.class).resolve(escapedStr));
    }
}
