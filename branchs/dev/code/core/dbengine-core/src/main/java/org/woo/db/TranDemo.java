package org.woo.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.woo.db.context.DBConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Administrator
 * @date 2018 06
 * org.woo.db.TranDemo
 */
public class TranDemo {
    private static final Logger logger = LoggerFactory.getLogger(TranDemo.class);
    // 定义MySQL的数据库驱动程序
    public static final String DBDRIVER = "oracle.jdbc.OracleDriver";
    // 定义MySQL数据库的连接地址
    public static final String DBURL = "jdbc:oracle:thin:@192.168.1.106:1521:orcl";
    // MySQL数据库的连接用户名
    public static final String DBUSER = "woostoreadmin";
    // MySQL数据库的连接密码
    public static final String DBPASS = "woostoreadmin";

    public static void main(String args[]) throws ClassNotFoundException, SQLException {    // 所有异常抛出
        Connection conn = null;        // 数据库连接
        Statement stmt = null;        // 定义数据库操作
        ConnectionPool connectionPool=new ConnectionPool(new DBConfig(DBDRIVER,DBURL,DBUSER,DBPASS,"demo"));
        connectionPool.init();
        conn = connectionPool.getConnection();
        conn.setAutoCommit(false);
        stmt = conn.createStatement();
        stmt.addBatch("INSERT INTO TB_USER(FNAME,FAGE,FBIRTHDAY)" +
                " VALUES ('LXH-1',11,'1975-03-05')");
        stmt.addBatch("INSERT INTO TB_USER(FNAME,FAGE,FBIRTHDAY)" +
                " VALUES ('LXH-2',12,'1976-03-05')");
        stmt.addBatch("INSERT INTO TB_USER(FNAME,FAGE,FBIRTHDAY)" +
                " VALUES ('LXH-3',13,'1977-06-01')");
        stmt.addBatch("INSERT INTO TB_USER(FNAME,FAGE,FBIRTHDAY)" +
                " VALUES ('LXH-4',14,'1965-03-05')");
        int temp[] = new int[0];
        try {
            temp= stmt.executeBatch();
            conn.commit();
            logger.info("更新"+temp.length+"条数据");
            logger.info("delete"+stmt.execute("delete TB_USER where FNAME in('LXH-1','LXH-2','LXH-3','LXH-4')"));
            conn.commit();
        }catch (SQLException e){
            logger.error(e.getLocalizedMessage());
            conn.rollback();
        }finally {
            connectionPool.destroy();
            stmt.close();
            conn.close();
        }
    }
}
