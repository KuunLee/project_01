package com.kli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kli.mapper.EmpMapper;
import com.kli.pojo.Emp;
import com.kli.pojo.PageBean;
import com.kli.qo.EmpQO;
import com.kli.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public boolean save(Emp emp) {
        Emp query = new Emp();
        query.setUsername(emp.getUsername());
        List<Emp> emps = query(query);
        if(CollectionUtils.isNotEmpty(emps)){
            return false;
        }
        empMapper.insert(emp);
        return true;
    }

    public List<Emp> query(Emp emp) {
        return empMapper.query(emp);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        empMapper.deleteByIds(ids);
    }

//    private List<Emp> queryByIds(List<Integer> ids) {
//        List<Emp> emps = new ArrayList<>();
//        ids.forEach(id -> {
//            Emp emp = new Emp();
//            emp.setId(id);
//            emps.add(emp);
//        });
//        empMapper.deleteByIds(ids);
//    }

    @Override
    public boolean update(Emp emp) {
        Emp query = new Emp();
        query.setUsername(emp.getUsername());
        List<Emp> emps = empMapper.query(query);
        //如果通过传参用户名可以查询到数据,再判断传参的ID和查询到的数据的ID是否一样，如果不一样，则代表不是同一个数据，此时将用户名修改为相同则返回失败
        if(CollectionUtils.isNotEmpty(emps)){
            for (Emp employee : emps) {
                if(!(employee.getId().compareTo(emp.getId()) == 0)){
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

    /**
     * 分页条件查询
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    @Override
    public PageBean page(Integer page,Integer pageSize,
                         String name, Short gender,
                         LocalDate begin,LocalDate end) {
        //1.设置分页参数
        PageHelper.startPage(page,pageSize);
        //2.查询数据
        List<Emp> emps = empMapper.list(name,gender,begin,end);
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

    @Override
    public Emp queryInfoById(Integer id) {
        Emp emp = new Emp();
        emp.setId(id);
        List<Emp> emps = query(emp);
        if(CollectionUtils.isEmpty(emps)){
            log.error("未查询到数据,id为：{}",id);
            return null;
        }
        return emps.get(0);
    }

    private Emp queryById(Integer id) {
        return empMapper.queryById(id);
    }
}
