package org.du.personalSite.web.vo.article.response;

import org.du.personalSite.domain.vo.ArticleInfo;
import org.du.personalSite.web.vo.comment.response.CommentVo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 燃烧杯 on 2017/10/28.
 */
public class ArticleVo {
    Long browseTimes;
    String latestModifTime;
    String title;
    String content;
    List<CommentVo> comments;

    public static ArticleVo info2Vo(ArticleInfo info){
        ArticleVo vo = new ArticleVo();
        vo.setBrowseTimes(info.getBrowseTimes());
        if ( info.getLatestModifTime() != null ){
            vo.setLatestModifTime(
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(info.getLatestModifTime())
            );
        }
        vo.setTitle(info.getTitle());
        vo.setContent(info.getContent());
        Long ownerId = null;
        if ( info.getOwner() != null && info.getCommentCustoms() != null){
            vo.setComments(CommentVo.customs2vos(info.getCommentCustoms(), info.getOwner().getId()));
        } else {
            vo.setComments(new ArrayList<CommentVo>());
        }

        return vo;
    }



    public Long getBrowseTimes() {
        return browseTimes;
    }

    public void setBrowseTimes(Long browseTimes) {
        this.browseTimes = browseTimes;
    }

    public String getLatestModifTime() {
        return latestModifTime;
    }

    public void setLatestModifTime(String latestModifTime) {
        this.latestModifTime = latestModifTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CommentVo> getComments() {
        return comments;
    }

    public void setComments(List<CommentVo> comments) {
        this.comments = comments;
    }
}
