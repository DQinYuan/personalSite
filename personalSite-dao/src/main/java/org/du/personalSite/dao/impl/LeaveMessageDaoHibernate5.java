package org.du.personalSite.dao.impl;

import org.du.personalSite.dao.LeaveMessageDao;
import org.du.personalSite.dao.base.impl.BaseDaoHibernate5;
import org.du.personalSite.domain.LeaveMessage;
import org.springframework.stereotype.Repository;

/**
 * Created by 燃烧杯 on 2017/7/12.
 */
@Repository
public class LeaveMessageDaoHibernate5 extends BaseDaoHibernate5<LeaveMessage> implements LeaveMessageDao {
}
