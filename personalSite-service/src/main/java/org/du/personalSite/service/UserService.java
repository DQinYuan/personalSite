package org.du.personalSite.service;

import org.du.personalSite.domain.User;
import org.du.personalSite.domain.vo.UserInfo;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by duqinyuan on 2017/5/6.
 *
 * @author duqinyuan
 * @version 1.0
 */
public interface UserService {
    public UserInfo verifyUser(UserInfo inUser) throws Exception;
}
