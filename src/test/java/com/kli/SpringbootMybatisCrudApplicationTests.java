package com.kli;

import com.kli.mapper.EmpMapper;
import com.kli.pojo.Emp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SpringBootTest
class SpringbootMybatisCrudApplicationTests {
    @Autowired
    private EmpMapper empMapper;

//    @Test
//    public void testListEmp(){
//        List<Emp> emps = empMapper.listEmp();
//        emps.forEach(System.out::println);
//    }

//    @Test
//    public void testQuery(){
//        List<Emp> emps = empMapper.query();
//        emps.forEach(System.out::println);
//    }

    @Test
    public void testGenJwt(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name","zhangsanfeng");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "likun")
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis()))
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt(){
        Claims parsed = Jwts.parser().setSigningKey("likun")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiemhhbmdzYW5mZW5nIiwiaWQiOjEsImV4cCI6MTcxOTk5MzI3OH0.-EPD3CMllB1KyIdsNPlwV3MimaLYhiDyBaDf1J0xuA4"
                ).getBody();
        System.out.println(parsed);
    }
}
