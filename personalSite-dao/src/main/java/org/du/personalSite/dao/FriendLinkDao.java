package org.du.personalSite.dao;

import org.du.personalSite.domain.FriendLink;

import java.util.List;

/**
 * Created by 燃烧杯 on 2018/3/9.
 */
public interface FriendLinkDao {
    public List<FriendLink> findAllLink();
}
