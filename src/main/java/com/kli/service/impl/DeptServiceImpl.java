package com.kli.service.impl;

import com.kli.mapper.DeptMapper;
import com.kli.pojo.Dept;
import com.kli.service.DeptService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public boolean insert(Dept dept) {
        List<Dept> depts = query(dept);
        if(CollectionUtils.isNotEmpty(depts)){
            return false;
        }
        deptMapper.insert(dept);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        Dept dept = queryById(id);
        if(dept == null){
            return false;
        }
        deptMapper.delete(id);
        return true;
    }

    @Override
    public boolean update(Dept dept) {
        List<Dept> query = queryByName(dept.getName());
        if(CollectionUtils.isNotEmpty(query)){
            if(!dept.getId().equals(query.get(0).getId())){
                return false;
            }
        }
        deptMapper.update(dept);
        return true;
    }

    private List<Dept> queryByName(String name) {
        Dept dept = new Dept();
        dept.setName(name);
        return deptMapper.query(dept);
    }

    @Override
    public List<Dept> query(Dept dept) {
        return deptMapper.query(dept);
    }

    public Dept queryById(Integer id) {
        return deptMapper.queryById(id);

    }
}
