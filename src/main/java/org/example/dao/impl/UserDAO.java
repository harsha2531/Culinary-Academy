package org.example.dao.impl;


import org.example.dao.CrudDAO;
import org.example.entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    public User searchByUsername(String username);

    List<String> getUserIds();
}
