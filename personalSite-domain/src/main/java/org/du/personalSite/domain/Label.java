package org.du.personalSite.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 燃烧杯 on 2017/7/28.
 */
@Entity
@Table(name = "label_inf")
public class Label {
    @Id
    @Column(name = "label_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String name;
    @Column(name = "create_time")
    Date createTime;
    @Column
    Long creater;
    @Column(name = "quote_times")
    Long quoteTimes;    //标签被文章引用的次数

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Long getQuoteTimes() {
        return quoteTimes;
    }

    public void setQuoteTimes(Long quoteTimes) {
        this.quoteTimes = quoteTimes;
    }
}
