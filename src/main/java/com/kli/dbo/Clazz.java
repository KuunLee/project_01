package com.kli.dbo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {
    private Integer id;
    private Short classRank;
    private Short grade;
    private String headTeacherName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
