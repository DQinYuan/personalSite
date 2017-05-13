package org.du.personalSite.dao.impl;

import org.du.personalSite.dao.UserDao;
import org.du.personalSite.dao.base.impl.BaseDaoHibernate5;
import org.du.personalSite.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by duqinyuan on 2017/5/6.
 *
 * @author duqinyuan
 * @version 1.0
 */
@Repository("userDao")
public class UserDaoHibernate5 extends BaseDaoHibernate5<User> implements UserDao {

    public User getByNicknameAndPass(User user) throws Exception {
        List<User> userList = find("select u from User u where u.nickname = ?0 and u.password=?1"
                , user.getNickname() , user.getPassword());
        if ( userList.size() == 0 ) return null;
        return userList.get(0);
    }
}
