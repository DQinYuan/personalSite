package org.du.personalSite.domain;

/**
 * Created by duqinyuan on 2017/5/10.
 *
 * @author duqinyuan
 * @version 1.0
 */
public interface DomainConstant {
    public static final int LOGIN_SUCCESS = 1;
    public static final int LOGIN_FAIL = 0;

    public static final int HIGH_LEVEL_USER = 0;

    //发表评论的十分钟之内可以修改评论
    public static final long CAN_MODIFY_TIME = 600000;
}
