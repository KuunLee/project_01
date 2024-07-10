package com.kli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kli.mapper.TestMapper;
import com.kli.pojo.Emp;
import com.kli.pojo.PageBean;
import com.kli.service.TestService;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestMapper testMapper;

    @Override
    public PageBean list(String name, Short gender, LocalDate startTime,
                          LocalDate endTime, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        List<Emp> emps = testMapper.queryEmps(name, gender, startTime, endTime);

        PageBean pageBean = new PageBean();
        if(CollectionUtils.isEmpty(emps)){
            pageBean.setTotal((long)0);
            pageBean.setRows(emps);
            return pageBean;
        }

        Page res = (Page) emps;
        pageBean.setTotal(res.getTotal());
        pageBean.setRows(res.getResult());
        return pageBean;
    }
}
