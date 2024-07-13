package com.kli.qo;

import com.kli.dbo.Emp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpQO {
    Emp data;
    LocalDate beginTime;
    LocalDate endTime;
    Integer page;
    Integer pageSize;
}
