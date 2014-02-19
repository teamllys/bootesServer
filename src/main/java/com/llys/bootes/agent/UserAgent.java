package com.llys.bootes.agent;

import java.util.List;

import com.llys.bootes.model.User;
import com.llys.bootes.util.GenericDao;

public class UserAgent {

    public void create(User user) throws Exception {
        Long id = GenericDao.insert(user);
        user.setId(id);
    }
    public User find(User user) throws Exception {
        User find = GenericDao.selectById(user, "userid", user.getUserId());
        return find;
    }

}
