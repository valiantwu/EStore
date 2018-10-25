package org.woo.orm.user;


import org.woo.orm.dao.BaseDao;
import org.woo.orm.entity.User;

import java.math.BigInteger;

public interface UserDao extends BaseDao<User, BigInteger> {
}
