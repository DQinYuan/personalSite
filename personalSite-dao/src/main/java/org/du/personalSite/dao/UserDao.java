package org.du.personalSite.dao;

import org.du.personalSite.dao.base.BaseDao;
import org.du.personalSite.domain.User;

/**
 * Created by duqinyuan on 2017/5/6.
 *
 * @author duqinyuan
 * @version 1.0
 */
public interface UserDao extends BaseDao<User> {
    User getByNicknameAndPass(User user) throws Exception;
}
