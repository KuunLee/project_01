package com.kli.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentVO {
    private Integer id;
    private String name;
    private Short gender;
    private Short classRank;
    private Short grade;
    private LocalDate admissionDate;
    private String headTeacherName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}