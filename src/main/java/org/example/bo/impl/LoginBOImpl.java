package org.example.bo.impl;

import org.example.bo.LoginBO;
import org.example.dao.DAOFactory;
import org.example.dao.impl.LoginDAO;
import org.example.dto.LoginDTO;
import org.example.entity.Login;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.Login);

    @Override
    public boolean save(LoginDTO dto) throws Exception {
        return loginDAO.save(new Login(dto.getLogin(),dto.getUserID(),dto.getDate()));
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return loginDAO.generateNextId();
    }
}
