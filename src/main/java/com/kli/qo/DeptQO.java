package com.kli.qo;

import com.kli.pojo.Dept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptQO {
    Dept dept;
    Integer page;
    Integer pageSize;
}
