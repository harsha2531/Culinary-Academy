package org.example.bo;

import org.example.dto.StudentCourseDTO;

import java.sql.SQLException;
import java.util.List;

public interface StudentCourseBO extends SuperBO{
    public boolean save(StudentCourseDTO dto) throws Exception;

    public boolean update(StudentCourseDTO dto) throws Exception;

    public boolean delete(String ID)throws Exception;

    List<StudentCourseDTO> getAll() throws SQLException, ClassNotFoundException;

    public String generateNextId() throws SQLException, ClassNotFoundException;
}
