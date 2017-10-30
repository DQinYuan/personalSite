package org.du.personalSite.service;

import org.du.personalSite.domain.Article;
import org.du.personalSite.domain.Comment;
import org.du.personalSite.domain.User;
import org.du.personalSite.domain.exception.PersonalSiteException;
import org.du.personalSite.domain.vo.CommentCustom;
import org.du.personalSite.domain.vo.CommentInfo;

import java.util.List;

/**
 * Created by 燃烧杯 on 2017/6/24.
 */
public interface CommentService {

    /**
     * 对于未注册用户userId传null，入库之后content的信息
     * @param commentInfo
     * @param userId
     * @param ip
     * @param session
     * @return
     * @throws Exception
     */
    Comment saveComment(CommentInfo commentInfo, Long userId, String ip, String session) throws Exception;

    String getOriginalContent(Long id) throws Exception;

    /**
     * 返回newOriginalContent经过markdown解析的content
     * @param user
     * @param commentId
     * @param newOriginalContent
     * @return
     * @throws Exception
     */
    String modifyComment(User user ,long commentId, String newOriginalContent) throws Exception;

    List<CommentCustom> getAllCommentsBelongToArt(User user, String title) throws PersonalSiteException;


    void deleteUserComment(User user, long commentId) throws PersonalSiteException;

    boolean isCommentArtBelonged(User user, long commentId) throws PersonalSiteException;

    Article getCommentArt(Long commentId) throws PersonalSiteException;
}
