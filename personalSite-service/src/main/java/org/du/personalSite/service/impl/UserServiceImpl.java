package org.du.personalSite.service.impl;

import org.du.personalSite.service.base.assembler.UserAssembler;
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
        User user = UserAssembler.getFromInfo(inUser);

        User currUser = userDao.getByNicknameAndPass(user);
        //如果该用户不存在或者该用户还处于未注册状态
        if ( currUser == null || !currUser.getRegistered() ){
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


}
