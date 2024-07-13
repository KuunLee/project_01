package com.kli.dbo;

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
    private Short gender;
    private Integer classId;
    private LocalDate admissionDate;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}