package org.example.bo.impl;

import org.example.bo.StudentBO;
import org.example.dao.DAOFactory;
import org.example.dao.impl.StudentDAO;
import org.example.dto.StudentDTO;
import org.example.dto.UserDTO;
import org.example.entity.Student;
import org.example.entity.User;
import org.example.bo.StudentBO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.Student);

    @Override
    public boolean save(StudentDTO dto) throws Exception {

        Student student = new Student(
                dto.getStu_id(),
                dto.getStu_name(),
                dto.getStu_phone(),
                dto.getStu_email(),
                dto.getStu_address(),
                new ArrayList<>(),
                new User(dto.getUser().getUser_id(), dto.getUser().getUsername(), dto.getUser().getAddress(),
                        dto.getUser().getUser_phone(), dto.getUser().getUser_email(), dto.getUser().getPosition(),
                        dto.getUser().getPassword()));

        return studentDAO.save(student);
    }

    @Override
    public boolean update(StudentDTO dto) throws Exception {
        Student student = new Student(
                dto.getStu_id(),
                dto.getStu_name(),
                dto.getStu_phone(),
                dto.getStu_email(),
                dto.getStu_address(),
                new ArrayList<>(),
                new User(dto.getUser().getUser_id(), dto.getUser().getUsername(), dto.getUser().getAddress(),
                        dto.getUser().getUser_phone(), dto.getUser().getUser_email(), dto.getUser().getPosition(),
                        dto.getUser().getPassword()));

        return studentDAO.update(student);
    }

    @Override
    public boolean delete(String ID) throws Exception {
        return studentDAO.delete(ID);
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return studentDAO.generateNextId();
    }

    @Override
    public List<StudentDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Student> students = studentDAO.getAll();
        List<StudentDTO> dtoList = new ArrayList<>();
        for (Student student : students) {
            StudentDTO dto = new StudentDTO(
                    student.getStu_id(),
                    student.getStu_name(),
                    student.getStu_phone(),
                    student.getStu_email(),
                    student.getStu_address(),
                    new UserDTO(student.getUser().getUser_id(), student.getUser().getUsername(), student.getUser().getAddress(),
                            student.getUser().getUser_phone(), student.getUser().getUser_email(), student.getUser().getPosition(),
                            student.getUser().getPassword())
            );
            dtoList.add(dto);
        }
        return dtoList;

    }

    @Override
    public List<String> getIds() {
        return studentDAO.getIds();
    }


    @Override
    public Student searchByContact(String id) throws SQLException, ClassNotFoundException{
        return studentDAO.searchByContact(id);
    }

}
