package org.example.dao;

import org.example.dao.impl.custom.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DaoType {
        User, Student, Payment, Course, Student_Course,Login
    }

    public SuperDAO getDAO(DaoType daoType) {
        switch (daoType) {
            case User:
                return new UserDAOImpl();
            case Student:
                return new StudentDAOImpl();
            case Payment:
                return new PaymentDAOImpl();
            case Course:
                return new CourseDAOImpl();
            case Student_Course:
                return new StudentCourseDAOImpl();
            case Login:
                return new LoginDAOImpl();
            default:
                return null;
        }
    }

}
