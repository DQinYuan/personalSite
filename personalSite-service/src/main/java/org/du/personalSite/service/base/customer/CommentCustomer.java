package org.du.personalSite.service.base.customer;

import org.du.personalSite.dao.CommentDao;
import org.du.personalSite.dao.UserDao;
import org.du.personalSite.domain.Comment;
import org.du.personalSite.domain.User;
import org.du.personalSite.domain.utils.Registry;
import org.du.personalSite.domain.vo.CommentCustom;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 燃烧杯 on 2017/7/3.
 */
public class CommentCustomer {

    public static List<CommentCustom> batchCustom(User user , List<Comment> comments){
        List<CommentCustom> commentCustoms = new ArrayList<CommentCustom>();
        for ( Comment comment : comments ){
            commentCustoms.add(custom(user, comment));
        }
        return commentCustoms;
    }

    public static CommentCustom custom(User user , Comment comment){
        if ( comment == null ){
            return null;
        }

        CommentCustom commentCustom = simpleCustom(user, comment);
        if ( comment.getResponseCommentId() != null ){  //如果存在parentCustom
            Comment parentComment = Registry.query(CommentDao.class).get(Comment.class, comment.getResponseCommentId());
            CommentCustom parentCustom = simpleCustom(user, parentComment);
            commentCustom.setParentCustom(parentCustom);
        }

        return commentCustom;
    }

    //即不设置parentCustom
    public static CommentCustom simpleCustom(User user , Comment comment){
        if ( comment == null ){
            return null;
        }

        //包装评论对象
        CommentCustom commentCustom = new CommentCustom();
        commentCustom.setComment(comment);

        //包装是否具有修改权限
        commentCustom.setCanModify(comment.canModify(user));

        //包装评论者的User对象
        Long owner = comment.getOwner();
        if ( owner == null ){
            return commentCustom;
        }
        User ownerUser = Registry.query(UserDao.class).get(User.class, owner);
        commentCustom.setUser(ownerUser);



        return commentCustom;
    }
}
