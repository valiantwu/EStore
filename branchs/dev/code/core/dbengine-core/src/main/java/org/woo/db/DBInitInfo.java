package org.woo.db;

import org.woo.db.context.DBConfig;

import java.util.ArrayList;
import java.util.List;

public class DBInitInfo {
    public static List<DBConfig> beans = null;

    static {
        beans = new ArrayList<DBConfig>();
        DBConfig beanOracle = new DBConfig();
        beanOracle.setDriverName("oracle.jdbc.driver.OracleDriver");
        beanOracle.setUrl("jdbc:oracle:thin:@192.168.1.106:1521:orcl");
        beanOracle.setUserName("woostoreadmin");
        beanOracle.setPassword("woostoreadmin");
        beanOracle.setMinConnections(5);
        beanOracle.setMaxConnections(100);
        beanOracle.setPoolName("testPool");
        beans.add(beanOracle);
    }
}
