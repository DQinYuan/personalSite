package org.du.personalSite.dao.impl;

import com.alibaba.fastjson.JSON;
import org.du.personalSite.dao.FriendLinkDao;
import org.du.personalSite.dao.base.impl.BaseDaoHibernate5;
import org.du.personalSite.dao.utils.RedisUtils;
import org.du.personalSite.domain.FriendLink;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 燃烧杯 on 2018/3/9.
 */
@Repository
public class FriendLinkDaoRedis3 implements FriendLinkDao {

    private static final String TABLE_NAME = "SYS_FRIEND_LINK_TABLE";

    public List<FriendLink> findAllLink() {
        Jedis j = RedisUtils.getJedis();
        List<String> jsons = j.lrange(TABLE_NAME, 0, -1);
        RedisUtils.returnResource(j);
        List<FriendLink> links = new ArrayList<FriendLink>();
        for ( String json : jsons ){
            links.add(JSON.parseObject(json, FriendLink.class));
        }
        return links;
    }
}
