package org.du.personalSite.domain;


import org.apache.commons.lang3.StringEscapeUtils;
import org.du.personalSite.domain.utils.MarkdowmInter;
import org.du.personalSite.domain.utils.Registry;
import org.du.personalSite.utils.TimeUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 燃烧杯 on 2017/6/23.
 */
@Entity
@Table(name = "comment_inf", indexes = @Index(columnList = "latest_modified_time"))
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "create_time")
    private Date createTime;

    @Column(nullable = false, name = "latest_modified_time")
    private  Date latestModifTime;

    @Column
    private String session;       //发表评论时所处的会话，使用uuid

    @Column(name = "owner")
    private Long owner;              //发表该评论的用户id

    @Column(name = "ip")
    private String ip;        //发表评论时的ip，owner为null时使用ip地址标明owner

    @Column(nullable = false, name = "article_id")
    private Long articleId;                   //评论的文章id

    @Column(name = "response_comment_id")
    private Long responseCommentId;            //回复的评论id，如果为null则表示回复的是文章作者

    @Lob
    @Column(length = 16777215, nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private String content;                 //经过markdown解析得到内容

    @Lob
    @Column(length = 16777215, nullable = false, name = "original_content")
    @Basic(fetch = FetchType.LAZY)
    private String originalContent;      //markdown原始内容

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLatestModifTime() {
        return latestModifTime;
    }

    public void setLatestModifTime(Date latestModifTime) {
        this.latestModifTime = latestModifTime;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getResponseCommentId() {
        return responseCommentId;
    }

    public void setResponseCommentId(Long responseCommentId) {
        this.responseCommentId = responseCommentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOriginalContent() {
        return originalContent;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }

    public boolean isSubcomment(){
        if ( this.responseCommentId == null ){
            return false;
        }
        return true;
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
