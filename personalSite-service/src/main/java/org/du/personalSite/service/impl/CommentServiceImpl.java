package org.du.personalSite.service.impl;

import org.du.personalSite.dao.ArticleDao;
import org.du.personalSite.dao.CommentDao;
import org.du.personalSite.domain.Article;
import org.du.personalSite.domain.Comment;
import org.du.personalSite.domain.User;
import org.du.personalSite.domain.exception.PersonalSiteException;
import org.du.personalSite.domain.vo.CommentCustom;
import org.du.personalSite.domain.vo.CommentInfo;
import org.du.personalSite.service.ArticleService;
import org.du.personalSite.service.CommentService;
import org.du.personalSite.service.base.MarkdownResolver;
import org.du.personalSite.service.base.customer.CommentCustomer;
import org.du.personalSite.utils.TimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.List;

/**
 * Created by 燃烧杯 on 2017/6/24.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    MarkdownResolver markdownResolver;

    @Resource
    CommentDao commentDao;

    @Resource
    ArticleDao articleDao;

    @Resource
    ArticleService articleService;

    @Transactional
    public Comment saveComment(CommentInfo commentInfo, Long userId, String ip, String session) throws Exception {

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentInfo, comment);

        //markdown解析
        comment.generateContent(comment.getOriginalContent());

        //时间参数处理
        comment.setCreateTime(TimeUtils.getNowTime());
        comment.setLatestModifTime(TimeUtils.getNowTime());

        //评论发表者信息
        comment.setOwner(userId);
        comment.setIp(ip);

        //会话信息
        comment.setSession(session);

        commentDao.save(comment);

        Comment retInfo = new Comment();
        BeanUtils.copyProperties(comment, retInfo);

        return retInfo;
    }

    @Transactional(readOnly = true)
    public String getOriginalContent(Long id) throws Exception {
        Comment comment = commentDao.get(Comment.class, id);
        if ( comment == null ){
            return  "";
        }

        return comment.getOriginalContent();
    }

    @Transactional
    public String modifyComment(User user ,long commentId, String newOriginalContent) throws Exception {
        Comment comment = commentDao.get(Comment.class, commentId);
        if ( comment == null ){
            throw new PersonalSiteException("该评论不存在");
        }
        if ( !comment.canModify(user) ){
            throw new PersonalSiteException("该评论已无法修改");
        }

        comment.generateContent(newOriginalContent);
        comment.setLatestModifTime(TimeUtils.getNowTime());

        commentDao.update(comment);

        return comment.getContent();
    }

    @Transactional(readOnly = true)
    public List<CommentCustom> getAllCommentsBelongToArt(User user, String title) throws PersonalSiteException {
        if ( !articleService.isArticleOwner(user, title) ){
            throw new PersonalSiteException("该用户无权限获取评论列表");
        }

        List<Comment> comments = commentDao.getCommentsByArticle(articleService.getIdFromTitle(title));
        return CommentCustomer.batchCustom(user, comments);
    }

    @Transactional
    public void deleteUserComment(User user, long commentId) throws PersonalSiteException {
        if ( !isCommentArtBelonged(user, commentId) ){
            throw new PersonalSiteException("该用户无权删除评论");
        }

        commentDao.delete(Comment.class, commentId);
    }

    public boolean isCommentArtBelonged(User user, long commentId) throws PersonalSiteException {

        if ( user.getId() == getCommentArt(commentId).getOwner().getId() ){
            return true;
        }

        return false;
    }

    public Article getCommentArt(Long commentId) throws PersonalSiteException {
        Comment comment = commentDao.get(Comment.class, commentId);
        if ( comment == null ){
            throw new PersonalSiteException("该评论不存在");
        }

        return articleDao.get(Article.class, comment.getArticleId());
    }


}
