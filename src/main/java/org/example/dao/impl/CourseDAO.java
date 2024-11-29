package org.example.dao.impl;

import org.example.dao.CrudDAO;
import org.example.entity.Course;

import java.sql.SQLException;

public interface CourseDAO extends CrudDAO<Course> {
    Course searchByName(String id) throws SQLException, ClassNotFoundException;
}
