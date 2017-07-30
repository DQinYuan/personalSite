package org.du.personalSite.service;

import org.du.personalSite.domain.Article;
import org.du.personalSite.domain.User;
import org.du.personalSite.domain.exception.PersonalSiteException;
import org.du.personalSite.domain.vo.CommentCustom;
import org.du.personalSite.domain.vo.CommentInfo;
import org.du.personalSite.domain.vo.UserInfo;

import java.util.List;

/**
 * Created by 燃烧杯 on 2017/6/24.
 */
public interface CommentService {
    //对于未注册用户userId传null
    void saveComment(CommentInfo commentInfo, Long userId, String ip, String session) throws Exception;

    String getOriginalContent(Long id) throws Exception;

    void modifyComment(User user ,long commentId, String newOriginalContent) throws Exception;

    List<CommentCustom> getAllCommentsBelongToArt(User user, String title) throws PersonalSiteException;


    void deleteUserComment(User user, long commentId) throws PersonalSiteException;

    boolean isCommentArtBelonged(User user, long commentId) throws PersonalSiteException;

    Article getCommentArt(Long commentId) throws PersonalSiteException;
}
