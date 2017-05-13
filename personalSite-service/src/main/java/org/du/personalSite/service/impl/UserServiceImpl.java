package org.du.personalSite.service.impl;

import org.springframework.beans.BeanUtils;
import org.du.personalSite.dao.UserDao;
import org.du.personalSite.domain.User;
import org.du.personalSite.domain.vo.UserInfo;
import org.du.personalSite.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by duqinyuan on 2017/5/6.
 *
 * @author duqinyuan
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Transactional
    public UserInfo verifyUser(UserInfo inUser) throws Exception {
        //vo转po
        User user = getFromInfo(inUser);

        User currUser = userDao.getByNicknameAndPass(user);
        if ( currUser == null ){
            return null;
        }

        currUser.setIp(user.getIp());
        currUser.setLatestLoginTime(user.getLatestLoginTime());
        userDao.update(currUser);

        //po转vo
        UserInfo outuser = new UserInfo();
        BeanUtils.copyProperties(currUser, outuser);
        return outuser;
    }

    public User getFromInfo(UserInfo userInfo){
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        return user;
    }
}
