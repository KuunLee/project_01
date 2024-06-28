package com.kli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kli.mapper.EmpMapper;
import com.kli.pojo.Emp;
import com.kli.pojo.PageBean;
import com.kli.qo.EmpQO;
import com.kli.service.EmpService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public boolean insert(Emp emp) {
        List<Emp> emps = query(emp);
        if(!CollectionUtils.isEmpty(emps)){
            return false;
        }
        empMapper.insert(emp);
        return true;
    }

    private List<Emp> query(Emp emp) {
        return empMapper.query(emp);
    }

    @Override
    public boolean delete(Integer id) {
        Emp emp = queryById(id);
        if(emp == null){
            return false;
        }
        empMapper.delete(id);
        return true;
    }

    @Override
    public void update(Emp emp) {
        empMapper.update(emp);
    }

    @Override
    public PageBean queryWithPage(EmpQO empQO) {
        Integer page = empQO.getPage();
        Integer pageSize = empQO.getPageSize();
        PageBean pageBean = new PageBean();

        //1.设置分页参数
        if(page == null || StringUtils.isBlank(page.toString())){
            page = 1;
        }
        if(pageSize == null || StringUtils.isBlank(pageSize.toString())){
            pageSize = 10;
        }
        PageHelper.startPage(page,pageSize);

        //2.查询
        Emp emp = empQO.getEmp();
        List<Emp> emps = empMapper.query(emp);
        Page result = (Page) emps;

        //3.封装PageBean对象
        pageBean.setTotal(result.getTotal());
        pageBean.setRows(result.getResult());
        return pageBean;
    }

    @Override
    public PageBean list(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Emp> emps = empMapper.query(new Emp());
        Page result = (Page) emps;
        PageBean pageBean = new PageBean();
        pageBean.setTotal(result.getTotal());
        pageBean.setRows(result.getResult());
        return pageBean;
    }

    private Emp queryById(Integer id) {
        return empMapper.queryById(id);
    }
}
