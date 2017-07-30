package org.du.personalSite.domain;



import org.apache.commons.lang3.StringEscapeUtils;
import org.du.personalSite.domain.utils.MarkdowmInter;
import org.du.personalSite.domain.utils.Registry;

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
@Table(name = "article_inf")
public class Article implements Serializable{
    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Lob
    @Column(length = 16777215)
    private String artAbstract;    //摘要

    @Column
    private Integer category;

    @Lob
    @Column(length = 16777215, name = "original_content")
    @Basic(fetch = FetchType.LAZY)
    private String originalContent;         //文章的markdown代码内容

    @Lob
    @Column(length = 16777215)
    @Basic(fetch = FetchType.LAZY)
    private String content;                       //文章的html内容

    @Column(name = "create_time", nullable = false)
    private Date createTime;               //该文章第一次被存到数据库的时间

    @Column(name = "latest_modified_time")
    private Date latestModifTime;    //最近被修改的时间

    @Column(name = "browse_times")
    private Long browseTimes;    //文章被浏览次数

    @Column(nullable = false)
    private Boolean isPublished;            //true说明已发表，false说明还在草稿箱

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    public Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
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

    public Long getBrowseTimes() {
        return browseTimes;
    }

    public void setBrowseTimes(Long browseTimes) {
        this.browseTimes = browseTimes;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean published) {
        isPublished = published;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getArtAbstract() {
        return artAbstract;
    }

    public void setArtAbstract(String artAbstract) {
        this.artAbstract = artAbstract;
    }

    /**
     * 被阅读num次，browseTimes要增加响应次数
     * @param num  被阅读的次数
     */
    public void beReaded(long num){
        Long numNow = getBrowseTimes();
        if ( numNow == null ){
            setBrowseTimes(num);
        } else {
            numNow += num;
            setBrowseTimes(numNow);
        }

    }

    public void publishToggle() {
        setIsPublished(getIsPublished()?false:true);
    }

    public void generateContent(String originalContent){
        setOriginalContent(originalContent);
        String escapedStr = StringEscapeUtils.escapeHtml4(originalContent);
        setContent(Registry.query(MarkdowmInter.class).resolve(escapedStr));
    }
}
