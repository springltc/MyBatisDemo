package com.github.yeecode.mybatisdemo;

import com.github.yeecode.mybatisdemo.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MyBatis多种SQL语句类型示例，包括STATEMENT、PREPARED、CALLABLE三种类型语句的示例。
 */
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        // MyBatis的初始化阶段
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 数据读写阶段
        try (SqlSession session = sqlSessionFactory.openSession()) {
            User userParam = new User();
            userParam.setSchoolName("Sunny School");
            List<User> userList = session.selectList("com.github.yeecode.mybatisdemo.dao.UserMapper.queryUserBySchoolName_A", userParam);
            for (User user : userList) {
                System.out.println("name : " + user.getName() + " ;  email : " + user.getEmail());
            }

            userList = session.selectList("com.github.yeecode.mybatisdemo.dao.UserMapper.queryUserBySchoolName_B", userParam);
            for (User user : userList) {
                System.out.println("name : " + user.getName() + " ;  email : " + user.getEmail());
            }

            Map<String, Integer> param = new HashMap<>();
            param.put("ageMinLimit", 10);
            param.put("ageMaxLimit", 30);
            session.selectOne("com.github.yeecode.mybatisdemo.dao.UserMapper.runCall", param);
            System.out.println("proceduce param :" + param);
            //proceduce param :{maxAge=27, ageMaxLimit=30, count=6, ageMinLimit=10}
        }
    }
}