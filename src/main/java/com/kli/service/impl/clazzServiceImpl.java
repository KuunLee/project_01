package com.kli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kli.mapper.ClazzMapper;
import com.kli.pojo.Clazz;
import com.kli.pojo.PageBean;
import com.kli.service.clazzService;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class clazzServiceImpl implements clazzService {
    @Resource
    private ClazzMapper clazzMapper;
    @Override
    public boolean insert(Clazz clazz) {
        Clazz clz = queryClassGrade(clazz.getClassRank(),clazz.getGrade());
        if(clz != null) return false;
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
        return true;
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        clazzMapper.deleteByIds(ids);
    }

    @Override
    public boolean update(Clazz clazz) {
        Clazz clz = queryClassGrade(clazz.getClassRank(),clazz.getGrade());
        if(clz != null){
            if(clazz.getId().compareTo(clz.getId()) != 0) return false;
        }
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
        return true;
    }

    @Override
    public PageBean list(Short classRank, String headTeacherName, Integer page, Integer pageSize) {
        //1.设置分页参数
        PageHelper.startPage(page,pageSize);

        //2.执行查询
        List<Clazz> list = clazzMapper.query(classRank,headTeacherName);
        PageBean bean = new PageBean();

        //3.没有查询到结果就返回空集合以及结果数为0
        if(CollectionUtils.isEmpty(list)){
            bean.setTotal((long) 0);
            bean.setRows(list);
        }

        //4.将结果转换为page对象
        Page res = (Page) list;

        //5.将page的内容设置给bean对象
        bean.setTotal(res.getTotal());
        bean.setRows(res.getResult());
        return bean;
    }

    @Override
    public Clazz queryInfoById(Integer id) {
        return clazzMapper.queryById(id);
    }

    private Clazz queryClassGrade(Short classRank, Short grade) {
        return clazzMapper.queryClassGrade(classRank,grade);
    }
}
