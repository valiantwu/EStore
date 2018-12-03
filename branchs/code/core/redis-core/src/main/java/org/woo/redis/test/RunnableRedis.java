package org.woo.redis.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

public class RunnableRedis implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RunnableRedis.class);

    String watchkeys = "watchkeys";// 监视keys
    Jedis jedis = new Jedis("192.168.0.106", 6379);
    String userinfo;

    public RunnableRedis() {
    }

    public RunnableRedis(String uinfo) {
        this.userinfo = uinfo;
    }

    @Override
    public void run() {
        try {
            jedis.watch(watchkeys);// watchkeys

            String val = jedis.get(watchkeys);
            int valint = Integer.valueOf(val);

            if (valint <= 100 && valint >= 1) {

                Transaction tx = jedis.multi();// 开启事务
                // tx.incr("watchkeys");
                tx.incrBy("watchkeys", -1);

                List<Object> list = tx.exec();// 提交事务，如果此时watchkeys被改动了，则返回null

                if (list == null || list.size() == 0) {

                    String failuserifo = "fail" + userinfo;
                    String failinfo = "用户：" + failuserifo + "商品争抢失败，抢购失败";
                    logger.info(failinfo);
                    /* 抢购失败业务逻辑 */
                    jedis.setnx(failuserifo, failinfo);
                } else {
                    for (Object succ : list) {
                        String succuserifo = "succ" + succ.toString() + userinfo;
                        String succinfo = "用户：" + succuserifo + "抢购成功，当前抢购成功人数:"
                                + (1 - (valint - 100));
                        logger.info(succinfo);
                         /* 抢购成功业务逻辑 */
                        jedis.setnx(succuserifo, succinfo);
                    }

                }

            } else {
                String failuserifo = "kcfail" + userinfo;
                String failinfo1 = "用户：" + failuserifo + "商品被抢购完毕，抢购失败";
                logger.info(failinfo1);
                jedis.setnx(failuserifo, failinfo1);
                // Thread.sleep(500);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

    }
}
