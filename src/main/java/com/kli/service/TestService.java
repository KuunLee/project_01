package com.kli.service;

import com.kli.dbo.PageBean;

import java.time.LocalDate;

public interface TestService {
    PageBean list(String name, Short gender, LocalDate startTime,
                  LocalDate endTime, Integer page, Integer pageSize);
}
