package com.kli.service;

import com.kli.pojo.Emp;
import com.kli.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface TestService {
    PageBean list(String name, Short gender, LocalDate startTime,
                  LocalDate endTime, Integer page, Integer pageSize);
}
