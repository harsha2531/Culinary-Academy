package org.example.dao.impl;

import org.example.dao.CrudDAO;
import org.example.entity.Login;


public interface LoginDAO extends CrudDAO<Login> {
    Login getLastLogin();
}
