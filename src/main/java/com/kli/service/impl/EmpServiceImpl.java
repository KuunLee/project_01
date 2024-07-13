package com.kli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kli.mapper.EmpMapper;
import com.kli.dbo.Emp;
import com.kli.dbo.PageBean;
import com.kli.qo.EmpQO;
import com.kli.service.EmpService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Resource
    private EmpMapper empMapper;

    /**
     * 新增员工
     * @param emp 员工信息
     * @return 是否进行了新增
     */
    @Override
    public boolean save(Emp emp) {
        Emp query = new Emp();
        query.setUsername(emp.getUsername());
        List<Emp> emps = query(query);
        //员工姓名重复则不进行新增
        if (CollectionUtils.isNotEmpty(emps)) {
            return false;
        }

        empMapper.insert(emp);
        return true;
    }

    public List<Emp> query(Emp emp) {
        return empMapper.query(emp);
    }

    /**
     * 批量删除员工(也可删除单个)
     * @param ids 删除的员工id列表
     */
    @Override
    public void deleteByIds(List<Integer> ids) {
        empMapper.deleteByIds(ids);
    }

    /**
     * 修改员工信息
     * @param emp 修改后的员工信息
     * @return 是否进行了修改
     */
    @Override
    public boolean update(Emp emp) {
        Emp query = new Emp();
        query.setUsername(emp.getUsername());
        List<Emp> emps = empMapper.query(query);
        //如果通过传参用户名可以查询到数据,再判断传参的ID和查询到的数据的ID是否一样，如果不一样，则代表不是同一个数据，此时将用户名修改为相同则返回失败
        if (CollectionUtils.isNotEmpty(emps)) {
            for (Emp employee : emps) {
                if (employee.getId().compareTo(emp.getId()) != 0) {
                    return false;
                }
            }
        }

        empMapper.update(emp);
        return true;
    }

    public PageBean queryWithPage(EmpQO empQO) {
        Integer page = empQO.getPage();
        Integer pageSize = empQO.getPageSize();
        PageBean pageBean = new PageBean();

        //1.设置分页参数
        if (page == null || StringUtils.isBlank(page.toString())) {
            page = 1;
        }

        if (pageSize == null || StringUtils.isBlank(pageSize.toString())) {
            pageSize = 10;
        }

        PageHelper.startPage(page, pageSize);

        //2.查询
        Emp emp = empQO.getData();
        List<Emp> emps = empMapper.query(emp);
        Page result = (Page) emps;

        //3.封装PageBean对象
        pageBean.setTotal(result.getTotal());
        pageBean.setRows(result.getResult());
        return pageBean;
    }

    /**
     * 分页条件查询
     *
     * @param page 页码
     * @param pageSize 分页数
     * @param name 姓名
     * @param gender 性别
     * @param begin 开始日期(入职日期)
     * @param end 结束日期(入职日期)
     * @return 查询结果
     */
    @Override
    public PageBean page(Integer page, Integer pageSize,
                         String name, Short gender,
                         LocalDate begin, LocalDate end) {
        //1.设置分页参数
        PageHelper.startPage(page, pageSize);
        //2.查询数据
        List<Emp> emps = empMapper.list(name, gender, begin, end);
        //使用代码处理按照创建时间倒序排序
//        emps = emps.stream().sorted((e1, e2) -> e2.getCreateTime().compareTo(e1.getCreateTime())).
//                collect(Collectors.toList());
        //3.封装为pageBean对象
        PageBean pageBean = new PageBean();
        Page result = (Page) emps;
        pageBean.setTotal(result.getTotal());
        pageBean.setRows(result.getResult());
        return pageBean;
    }

    /**
     * 查询员工详情
     * @param id 员工id
     * @return 员工信息
     */
    @Override
    public Emp queryInfoById(Integer id) {
        Emp emp = new Emp();
        emp.setId(id);
        List<Emp> emps = query(emp);
        return CollectionUtils.isEmpty(emps) ? null : emps.get(0);
    }

    /**
     * 分页查询(带所有条件)
     * @param empQO 分页查询对象
     * @return 分页查询结果
     */
    @Override
    public PageBean queryAndPaging(EmpQO empQO) {
        //1.参数校验
        if (empQO.getPage() == null || StringUtils.isBlank(empQO.getPage().toString())) {
            empQO.setPage(1);
        }

        if (empQO.getPageSize() == null || StringUtils.isBlank(empQO.getPageSize().toString())) {
            empQO.setPageSize(10);
        }

        if (empQO.getData() == null) {
            empQO.setData(new Emp());
        }

        //2.设置分页参数
        PageHelper.startPage(empQO.getPage(), empQO.getPageSize());

        //3.执行查询并将结果转换为page对象
        List<Emp> emps = empMapper.queryPaging(empQO);
//        List<Emp> emps = empMapper.query(empQO.getData());
        PageBean pageBean = new PageBean();
        if (CollectionUtils.isEmpty(emps)) {
            pageBean.setTotal((long) 0);
            pageBean.setRows(new ArrayList());
            return pageBean;
        }

        Page result = (Page) emps;

        //4.组装参数
        pageBean.setTotal(result.getTotal());
        pageBean.setRows(result.getResult());
        return pageBean;
    }

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    @Override
    public Emp login(String username, String password) {
        return empMapper.queryByUsernameAndPassword(username,password);
    }

    private Emp queryById(Integer id) {
        return empMapper.queryById(id);
    }
}
