package org.example.dao.impl;


import org.example.dao.CrudDAO;
import org.example.entity.Student;

import java.sql.SQLException;

public interface StudentDAO extends CrudDAO<Student> {
    public Student searchByContact(String id) throws SQLException, ClassNotFoundException;
}
