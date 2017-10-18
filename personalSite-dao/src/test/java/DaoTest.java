import org.du.personalSite.dao.ArticleDao;
import org.du.personalSite.dao.UserDao;
import org.du.personalSite.domain.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by duqinyuan on 2017/5/6.
 *
 * @author duqinyuan
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/ac_personalSite_dao.xml"})
public class DaoTest {
    @Resource
    UserDao userDao;

    @Resource
    ArticleDao articleDao;

    @Resource
    ApplicationContext ac;

    //用于插入用户的方法，@Rollback(false)保证了Junit不会自动回滚事务

    @Transactional
    @Rollback(false)
    @Test
    public void insertUser(){
//        User me = new User();
//        me.setNickname("DQYuan");
//        me.generatePassword("19950207");
//        me.setRealname("杜沁园");
//        me.setCreateTime(new Date());
//        me.setRegistered(true);
//        me.setLevel(0);
//        me.setOtherPersonalInformation("无");
//
//        User nanxia = new User();
//        nanxia.setNickname("南夏");
//        nanxia.generatePassword("19941214");
//        nanxia.setRealname("夏窦莹");
//        nanxia.setCreateTime(new Date());
//        nanxia.setRegistered(true);
//        nanxia.setLevel(0);
//        nanxia.setOtherPersonalInformation("无");

        User xj = new User();
        xj.setNickname("msgsxj");
        xj.generatePassword("msgsxj");
        xj.setRealname("许坚");
        xj.setCreateTime(new Date());
        xj.setRegistered(true);
        xj.setLevel(0);
        xj.setOtherPersonalInformation("无");

        try {
//            userDao.save(me);
//            userDao.save(nanxia);
            userDao.save(xj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    @Transactional
    public void query(){
        User me = new User();
        me.setNickname("DQYuan");
        me.generatePassword("3905084dqy");
        try {
            me = userDao.getByNicknameAndPass(me);
            if ( me == null )
            {
                System.out.println("密码或用户名不对");
                return;
            }
            System.out.println(me.getRealname());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    @Transactional
    public void testAc(){
        UserDao userDao = (UserDao) ac.getBean("userDao");

        User youke = new User();
        youke.setNickname("YouKe1");
        youke.generatePassword("123456");

        try {
            youke = userDao.getByNicknameAndPass(youke);
            System.out.println(youke.getRealname());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
