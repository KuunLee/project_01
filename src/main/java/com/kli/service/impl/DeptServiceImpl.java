package com.kli.service.impl;

import com.kli.mapper.DeptLogMapper;
import com.kli.mapper.DeptMapper;
import com.kli.mapper.EmpMapper;
import com.kli.pojo.Dept;
import com.kli.pojo.DeptLog;
import com.kli.service.DeptLogService;
import com.kli.service.DeptService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptMapper deptMapper;

    @Resource
    private EmpMapper empMapper;

    @Resource
    private DeptLogService deptLogService;
    /**
     * 新增部门
     *
     * @param dept 部门信息
     * @return 是否进行了新增
     */
    @Override
    public boolean insert(Dept dept) {
        Dept query = queryByName(dept.getName());
        if (query != null) {
            if (StringUtils.equals(dept.getName(), query.getName())) {
                return false;
            }
        }

        deptMapper.insert(dept);
        return true;
    }

    /**
     * 删除部门
     *
     * @param id 部门id
     * @return 是否删除成功
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean delete(Integer id) {
        Dept dept = queryById(id);

        if (dept == null) {
            return false;
        }

        try {
            deptMapper.delete(id);

            int i = 1/0;

            empMapper.deleteByDeptId(id);
        }  finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("正在执行删除部门操作,删除的部门id为：" + id);
            deptLogService.insert(deptLog);
        }
        return true;
    }

    /**
     * 修改部门信息
     *
     * @param dept 部门信息
     * @return 是否进行了修改
     */
    @Override
    public boolean update(Dept dept) {
        Dept query = queryByName(dept.getName());
        //若通过部门名称可以查询到数据，且查询到的和前端传的不是同一个数据，此时修改姓名为相同则返回false不进行修改
        if (query != null) {
            if (dept.getId().compareTo(query.getId()) != 0) {
                if (StringUtils.equals(dept.getName(), query.getName())) {
                    return false;
                }
            }
        }

        deptMapper.update(dept);
        return true;
    }


    /**
     * 查询部门信息
     *
     * @param dept 部门信息
     * @return 查询结果
     */
    @Override
    public List<Dept> query(Dept dept) {
        return deptMapper.query(dept);
    }

    public Dept queryById(Integer id) {
        return deptMapper.queryById(id);
    }

    private Dept queryByName(String name) {
        return deptMapper.queryByName(name);
    }
}
