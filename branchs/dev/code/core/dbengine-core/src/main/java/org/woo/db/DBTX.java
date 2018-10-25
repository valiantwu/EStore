package org.woo.db;

import org.woo.db.context.DBConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

public class DBTX {
    private static ThreadLocal<TXContext> tx_holders = new ThreadLocal<>();
    private static ThreadLocal<TXContext> out_tx_holders = new ThreadLocal<>();
    private static ThreadLocal<Throwable> throwableThreadLocal = new ThreadLocal<>();
    private Hashtable<String, IConnectionPool> pools = new Hashtable<String, IConnectionPool>();

    public void init() {
        for (int i = 0; i < DBInitInfo.beans.size(); i++) {
            DBConfig bean = DBInitInfo.beans.get(i);
            ConnectionPool pool = new ConnectionPool(bean);
            if (pool != null) {
                pools.put(bean.getPoolName(), pool);
                System.out.println("Info:Init connection successed ->" + bean.getPoolName());
            }
        }
    }

    public Connection getConnection(String poolName) {
        Connection conn = null;
        if (pools.size() > 0 && pools.containsKey(poolName)) {
            conn = getPool(poolName).getConnection();
        } else {
            System.out.println("Error:Can't find this connecion pool ->" + poolName);
        }
        return conn;
    }

    public void close(String poolName, Connection conn) {
        IConnectionPool pool = getPool(poolName);
        try {
            if (pool != null) {
                pool.releaseConn(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy(String poolName) {
        IConnectionPool pool = getPool(poolName);
        if (pool != null) {
            pool.destroy();
        }
    }

    public IConnectionPool getPool(String poolName) {
        IConnectionPool pool = null;
        if (pools.size() > 0) {
            pool = pools.get(poolName);
        }
        return pool;
    }
}
