package org.du.personalSite.service.impl;

import org.du.personalSite.dao.FriendLinkDao;
import org.du.personalSite.domain.FriendLink;
import org.du.personalSite.service.FriendLinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 燃烧杯 on 2018/3/9.
 */

@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Resource
    FriendLinkDao friendLinkDao;

    public List<FriendLink> getAllFriendLinks() {
        return friendLinkDao.findAllLink();
    }
}
