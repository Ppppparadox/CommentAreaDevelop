package com.example.demo;

import com.example.demo.dao.DiscussPostMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.DiscussPost;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.*;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class )
public class TestMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectUser(){
        User user=userMapper.selectById(13);
        System.out.println(user);

        user=userMapper.selectByName("nowcoder23");
        System.out.println(user);

        user=userMapper.selectByEmail("nowcoder117@sina.com");
        System.out.println(user);
    }

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("ParaDox");
        user.setPassword("132!L");
        user.setSalt("abc");
        user.setEmail("@163.com");
        user.setHeaderUrl("http://www.newcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);

        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdate(){
        int rows = userMapper.updateStatus(150,1);
        System.out.println(rows);

        rows=userMapper.updateHeader(150,"http://www.newcoder.com/102.png");
        System.out.println(rows);

        rows=userMapper.updatePassword(150,"1");
        System.out.println(rows);
    }

    @Test
    public void testDiscussPostMapper(){
        List<DiscussPost> discussPostList=discussPostMapper.selectDiscussPost(0,0,10);
        for (DiscussPost discussPost:discussPostList){
            System.out.println(discussPost);
        }

        int discussPostRows = discussPostMapper.selectDiscussPostRows(111);
        System.out.println(discussPostRows);
    }
}
