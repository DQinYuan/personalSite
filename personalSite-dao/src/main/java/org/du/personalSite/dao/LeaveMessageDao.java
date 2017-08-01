package org.du.personalSite.dao;

import org.du.personalSite.dao.base.BaseDao;
import org.du.personalSite.domain.LeaveMessage;

import java.util.List;

/**
 * Created by 燃烧杯 on 2017/7/12.
 */
public interface LeaveMessageDao extends BaseDao<LeaveMessage> {
    List<LeaveMessage> findAll();
}
