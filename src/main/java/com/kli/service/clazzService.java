package com.kli.service;

import com.kli.dbo.Clazz;
import com.kli.dbo.PageBean;

import java.util.List;

public interface clazzService {
    boolean insert(Clazz clazz);

    void deleteByIds(List<Integer> ids);

    boolean update(Clazz clazz);

    PageBean list(Short classRank, String headTeacherName, Integer page, Integer pageSize);

    Clazz queryInfoById(Integer id);
}
