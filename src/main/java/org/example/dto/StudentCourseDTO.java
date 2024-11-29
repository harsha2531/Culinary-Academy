package org.example.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data

public class StudentCourseDTO {
    private String student_course_id;
    private StudentDTO student;
    private CourseDTO course;
    private Date registration_date;
}
