package org.woo.mservice;


import org.woo.orm.entity.User;

import java.util.Collection;

/**
 * @author Administrator
 * @date 2018 07
 * org.woo.mservice.IUserService
 */
public interface IUserService {
    boolean updateLogOutDate(User user);

    User getUserInfoById(String pkId);

    Collection<User> getUsers();

    User queryByUserName(String name);

    boolean updateUserInfo(User master);

    boolean insertUser(User master);
}
