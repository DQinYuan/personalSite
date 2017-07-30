package org.du.personalSite.service.base.assembler;

import org.du.personalSite.domain.User;
import org.du.personalSite.domain.vo.UserInfo;
import org.springframework.beans.BeanUtils;

/**
 * Created by 燃烧杯 on 2017/7/3.
 */
public class UserAssembler {
    public static User getFromInfo(UserInfo userInfo){
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        return user;
    }
}
