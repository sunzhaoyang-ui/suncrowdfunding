package com.sun.crowd.test;

import com.sun.crowd.entity.Admin;
import com.sun.crowd.entity.Role;
import com.sun.crowd.mapper.AdminMapper;
import com.sun.crowd.mapper.RoleMapper;
import com.sun.crowd.service.api.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

// 这里用junit5进行测试
@SpringJUnitConfig(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {
    @Resource
    private DataSource dataSource;

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private AdminService adminService;

    @Resource
    private RoleMapper roleMapper;

    @Test
    public void testTx(){
        Admin admin = new Admin(null, "mei Lee", "123456", "mei", "mei@qq.com", null);
        adminService.saveAdmin(admin);
    }

    @Test
    public void  testConnection() throws SQLException{
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testInsertAdmin(){
        Admin admin = new Admin(null,"Rachel","123123","rui","rui@qq.com",null);
        int count = adminMapper.insert(admin);
        System.out.println(count);
    }

    @Test
    public void testRoleSave(){
        for (int i = 0; i < 235; i++) {
            roleMapper.insert(new Role(null,"role"+i));
        }
    }

}