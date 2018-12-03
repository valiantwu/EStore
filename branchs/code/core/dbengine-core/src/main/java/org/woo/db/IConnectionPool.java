package org.woo.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionPool {
    Connection getConnection();

    Connection getCurrentConnection();

    void releaseConn(Connection conn) throws SQLException;

    void destroy();

    boolean isActive();

    void checkPool();
}
