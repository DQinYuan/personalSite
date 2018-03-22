import org.du.personalSite.dao.utils.RedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

/**
 * Created by 燃烧杯 on 2018/3/11.
 */
public class LittleTest {

    private static final String ASSIST_TABLE = "ID_SCORE_HASH";

    public static void main(String[] args) {
        Jedis j  = RedisUtils.getJedis();
        Transaction tx = j.multi();
        Response<String> response = tx.hget(ASSIST_TABLE, "1");
        tx.exec();
        System.out.println(response.get());
    }

}
