package com.kli.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private Short grade;
    private Short gender;
    private Short clazz;
    private LocalDate admissionDate;
    private String headTeacherName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
