package org.woo.dispatcher.servlet;


import org.woo.db.ConnectionPool;
import org.woo.db.DBConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.sql.SQLException;

public class DBServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -9079584933635341665L;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initConnectionPool(config);
    }

    public void initConnectionPool(ServletConfig config) {
        DBConfig dbBean = new DBConfig();
        dbBean.setDriverName(config.getInitParameter("driver"));
        dbBean.setUrl(config.getInitParameter("url"));
        dbBean.setUserName(config.getInitParameter("username"));
        dbBean.setPassword(config.getInitParameter("password"));
        dbBean.setPoolName("oracleConn");
        ConnectionPool connectionPool = new ConnectionPool(dbBean);
        try {
            System.out.println(connectionPool.getConnection().getSchema());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
