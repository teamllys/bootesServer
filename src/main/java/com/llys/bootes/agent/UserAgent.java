package com.llys.bootes.agent;


import java.util.Date;

import com.llys.bootes.model.User;
import com.llys.bootes.util.GenericDao;

public class UserAgent {

    public void create(User user) throws Exception {
        user.setCreateTime(new Date());
        Long id = GenericDao.insert(user);
        user.setUserId(id);
    }
    public User find(User user) throws Exception {
        User find = GenericDao.selectById(user, "emailId", user.getEmailId());
        return find;
    }

}
