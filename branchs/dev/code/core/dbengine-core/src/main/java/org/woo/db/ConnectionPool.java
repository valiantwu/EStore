package org.woo.db;

import org.woo.db.context.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class ConnectionPool implements IConnectionPool {
    private DBConfig DBConfig;
    private boolean isActive = false;
    private int contActive = 0;
    private List<Connection> freeConnection = new Vector<Connection>();
    private List<Connection> activeConnection = new Vector<Connection>();
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    public ConnectionPool(DBConfig DBConfig) {
        super();
        this.DBConfig = DBConfig;
        init();
        checkPool();
    }

    /**
     *
     */
    public void init() {
        try {
            Class.forName(DBConfig.getDriverName());
            for (int i = 0; i < DBConfig.getInitConnections(); i++) {
                Connection conn;
                conn = newConnection();
                // 初始化最小连接数
                if (conn != null) {
                    freeConnection.add(conn);
                    contActive++;
                }
            }
            isActive = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public Connection getCurrentConnection() {
        Connection conn = threadLocal.get();
        if (!isValid(conn)) {
            conn = getConnection();
        }
        return conn;
    }

    /**
     * @return
     */
    public synchronized Connection getConnection() {
        Connection conn = null;
        try {
            if (contActive < this.DBConfig.getMaxActiveConnections()) {
                if (freeConnection.size() > 0) {
                    conn = freeConnection.get(0);
                    if (conn != null) {
                        threadLocal.set(conn);
                    }
                    freeConnection.remove(0);
                } else {
                    conn = newConnection();
                }

            } else {
                wait(this.DBConfig.getConnTimeOut());
                conn = getConnection();
            }
            if (isValid(conn)) {
                activeConnection.add(conn);
                contActive++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private synchronized Connection newConnection()
            throws ClassNotFoundException, SQLException {
        Connection conn = null;
        if (DBConfig != null) {
            Class.forName(DBConfig.getDriverName());
            conn = DriverManager.getConnection(DBConfig.getUrl(),
                    DBConfig.getUserName(), DBConfig.getPassword());
        }
        return conn;
    }

    /**
     *
     * @param conn
     * @throws SQLException
     */
    public synchronized void releaseConn(Connection conn) throws SQLException {
        if (isValid(conn) && !(freeConnection.size() > DBConfig.getMaxConnections())) {
            freeConnection.add(conn);
            activeConnection.remove(conn);
            contActive--;
            threadLocal.remove();
            notifyAll();
        }
    }

    private boolean isValid(Connection conn) {
        try {
            if (conn == null || conn.isClosed()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public synchronized void destroy() {
        for (Connection conn : freeConnection) {
            try {
                if (isValid(conn)) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (Connection conn : activeConnection) {
            try {
                if (isValid(conn)) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        isActive = false;
        contActive = 0;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void checkPool() {
        if (DBConfig.isCheakPool()) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("free" + freeConnection.size());
                    System.out.println("active" + activeConnection.size());
                    System.out.println("total" + contActive);
                }
            }, DBConfig.getLazyCheck(), DBConfig.getPeriodCheck());
        }
    }
}