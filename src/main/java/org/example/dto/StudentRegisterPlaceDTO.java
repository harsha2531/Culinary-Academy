package org.example.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class StudentRegisterPlaceDTO {
    private StudentCourseDTO student_course;
    private PaymentDTO student_payment;
}
