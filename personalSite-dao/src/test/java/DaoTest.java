import org.du.personalSite.dao.ArticleDao;
import org.du.personalSite.dao.UserDao;
import org.du.personalSite.domain.Article;
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
    @Ignore
    @Transactional
    @Rollback(false)
    @Test
    public void insertUser(){
        User me = new User();
        me.setNickname("DQYuan");
        me.setPassword("3905084dqy");
        me.setRealname("杜沁园");
        me.setCreateTime(new Date());
        me.setRegistered(true);
        me.setLevel(0);
        me.setOtherPersonalInformation("无");

        User youke1 = new User();
        youke1.setNickname("YouKe1");
        youke1.setPassword("123456");
        youke1.setRealname("游客1");
        youke1.setCreateTime(new Date());
        youke1.setRegistered(true);
        youke1.setLevel(1);
        youke1.setOtherPersonalInformation("无");


        User youke2 = new User();
        youke2.setNickname("YouKe2");
        youke2.setPassword("123456");
        youke2.setRealname("游客2");
        youke2.setCreateTime(new Date());
        youke2.setRegistered(true);
        youke2.setLevel(1);
        youke2.setOtherPersonalInformation("无");

        User youke3 = new User();
        youke3.setNickname("YouKe3");
        youke3.setPassword("123456");
        youke3.setRealname("游客3");
        youke3.setCreateTime(new Date());
        youke3.setRegistered(true);
        youke3.setLevel(1);
        youke3.setOtherPersonalInformation("无");


        try {
            userDao.save(me);
            userDao.save(youke1);
            userDao.save(youke2);
            userDao.save(youke3);
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
        me.setPassword("3905084dqy");
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

    @Test
    @Transactional
    public void testAc(){
        UserDao userDao = (UserDao) ac.getBean("userDao");

        User youke = new User();
        youke.setNickname("YouKe1");
        youke.setPassword("123456");

        try {
            youke = userDao.getByNicknameAndPass(youke);
            System.out.println(youke.getRealname());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
