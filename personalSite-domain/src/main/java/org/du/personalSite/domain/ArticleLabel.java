package org.du.personalSite.domain;

import javax.persistence.*;

/**
 * Created by 燃烧杯 on 2017/7/28.
 */
@Entity
@Table(name = "article_label")
public class ArticleLabel {
    @Id
    @Column(name = "article_label_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "article_id")
    Long articleId;
    @Column(name = "label_id")
    Long labelId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }
}
