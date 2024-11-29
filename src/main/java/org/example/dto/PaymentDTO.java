package org.example.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class PaymentDTO {
    private String pay_id;
    private Date pay_date;
    private double pay_amount;
    private StudentCourseDTO studentCourse;
}