package org.woo.db;

import java.util.Map;

/**
 * @author Administrator
 * @date 2018 05
 * org.woo.db.DBRoute
 */
public class DBRoute {
    private Map<String, Object> tableRouterMap;
    String dbRouterKey;

    public static DBRoute of(String dbRouterKey) {
        return new DBRoute();
    }

    public void setDbRouterKey() {

    }
}
