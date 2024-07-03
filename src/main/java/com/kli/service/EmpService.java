package com.kli.service;

import com.kli.pojo.Emp;
import com.kli.pojo.PageBean;
import com.kli.qo.EmpQO;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 新增员工
     * @param emp
     * @return
     */
    boolean save(Emp emp);

    /**
     * 批量(单个)删除员工
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 更新员工
     * @param emp
     * @return
     */
    boolean update(Emp emp);

    /**
     * 查询员工信息
     * @param emp
     * @return
     */
    List<Emp> query(Emp emp);

    /**
     * 分页条件查询(部分参数)
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    PageBean page(Integer page, Integer pageSize,String name, Short gender, LocalDate begin,LocalDate end);

    /**
     * 查询员工详情
     * @param id
     * @return
     */
    Emp queryInfoById(Integer id);

    /**
     * 分页条件查询(全部参数)
     * @param empQO
     * @return
     */
    PageBean queryAndPaging(EmpQO empQO);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    Emp login(String username, String password);
}
