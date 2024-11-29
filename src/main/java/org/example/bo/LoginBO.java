package org.example.bo;

import org.example.dto.LoginDTO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO{
    public boolean save(LoginDTO dto) throws Exception;

    String generateNextId() throws SQLException, ClassNotFoundException;
}