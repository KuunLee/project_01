package com.kli.qo;

import com.kli.pojo.Emp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpQO {
    Emp emp;
    Integer page;
    Integer pageSize;
}
