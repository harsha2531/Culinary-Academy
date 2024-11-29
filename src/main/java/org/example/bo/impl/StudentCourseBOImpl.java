package org.example.bo.impl;

import org.example.bo.StudentCourseBO;
import org.example.dao.DAOFactory;
import org.example.dao.impl.StudentCourseDAO;
import org.example.dto.CourseDTO;
import org.example.dto.StudentDTO;
import org.example.dto.StudentCourseDTO;
import org.example.dto.UserDTO;
import org.example.entity.*;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseBOImpl implements StudentCourseBO {
    StudentCourseDAO studentCourseDAO = (StudentCourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.Student_Course);

    @Override
    public boolean save(StudentCourseDTO dto) throws Exception {
        return studentCourseDAO.save(new StudentCourse(
                dto.getStudent_course_id(),
                new Student(
                        dto.getStudent().getStu_id(),
                        dto.getStudent().getStu_name(),
                        dto.getStudent().getStu_phone(),
                        dto.getStudent().getStu_email(),
                        dto.getStudent().getStu_address(),
                        new ArrayList<>(),
                        new User()
                ),
                new Course(
                        dto.getCourse().getCourse_id(),
                        dto.getCourse().getCourse_name(),
                        dto.getCourse().getDuration(),
                        dto.getCourse().getCourse_fee(),
                        new ArrayList<>()
                ),
                dto.getRegistration_date(),
                new ArrayList<>()
        ));
    }

    @Override
    public boolean update(StudentCourseDTO dto) throws Exception {
        return studentCourseDAO.update(new StudentCourse(
                dto.getStudent_course_id(),
                new Student(
                        dto.getStudent().getStu_id(),
                        dto.getStudent().getStu_name(),
                        dto.getStudent().getStu_phone(),
                        dto.getStudent().getStu_email(),
                        dto.getStudent().getStu_address(),
                        new ArrayList<>(),
                        new User()
                ),
                new Course(
                        dto.getCourse().getCourse_id(),
                        dto.getCourse().getCourse_name(),
                        dto.getCourse().getDuration(),
                        dto.getCourse().getCourse_fee(),
                        new ArrayList<>()
                ),
                dto.getRegistration_date(),
                new ArrayList<>()
        ));    }

    @Override
    public boolean delete(String ID) throws Exception {
        return studentCourseDAO.delete(ID);
    }
    @Override
    public List<StudentCourseDTO> getAll() throws SQLException, ClassNotFoundException {
        List<StudentCourse> SC = studentCourseDAO.getAll();
        List<StudentCourseDTO> dtoList = new ArrayList<>();
        for (StudentCourse student_course : SC) {
            StudentCourseDTO dto = new StudentCourseDTO(
                    student_course.getStudent_course_id(),
                    new StudentDTO(
                            student_course.getStudent().getStu_id(),
                            student_course.getStudent().getStu_name(),
                            student_course.getStudent().getStu_phone(),
                            student_course.getStudent().getStu_email(),
                            student_course.getStudent().getStu_address(),
                            new UserDTO()),
                    new CourseDTO(
                            student_course.getCourse().getCourse_id(),
                            student_course.getCourse().getCourse_name(),
                            student_course.getCourse().getDuration(),
                            student_course.getCourse().getCourse_fee()
                    ),
                    student_course.getRegistration_date()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return studentCourseDAO.generateNextId();
    }
}
