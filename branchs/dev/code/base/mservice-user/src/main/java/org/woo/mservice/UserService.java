package org.woo.mservice;


import org.woo.orm.entity.User;

import java.util.Collection;

/**
 * @author Administrator
 * @date 2018 07
 * org.woo.mservice.UserService
 */
public class UserService implements IUserService {
    @Override
    public boolean updateLogOutDate(User master) {
        return false;
    }

    @Override
    public User getUserInfoById(String s) {
        return null;
    }

    @Override
    public Collection<User> getUsers() {
        return null;
    }

    @Override
    public User queryByUserName(String s) {
        return null;
    }

    @Override
    public boolean insertUser(User master) {
        return false;
    }

    @Override
    public boolean updateUserInfo(User master) {
        return false;
    }
}
