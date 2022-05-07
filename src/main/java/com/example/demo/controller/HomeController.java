package com.example.demo.controller;

import com.example.demo.entity.DiscussPost;
import com.example.demo.entity.Page;
import com.example.demo.entity.User;
import com.example.demo.service.DiscussPostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

//    @RequestMapping(path = "/index",method = RequestMethod.GET)
//    public String getIndexPage(Model model){
//        List<DiscussPost> list = discussPostService.selectDiscussPost(0,0,10);
//        List<Map<String,Object>> discussPosts = new ArrayList<>();
//        if(list!=null){
//            for (DiscussPost post:list){
//                Map<String,Object> map = new HashMap<>();
//                map.put("post",post);
//                User user=userService.selectUserById(post.getUserId());
//                map.put("user",user);
//                discussPosts.add(map);
//            }
//        }
//        model.addAttribute("discussPosts",discussPosts);
//        return "/index";
//    }

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        // 方法调用前SpringMVC会自动实例化Model和Page，并将Page注入Model，
        // 所以在thymeleaf中可以直接访问Page对象中的数据

        // 设置总共有多少条数据
        page.setRows(discussPostService.selectDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> discussPostList = discussPostService.selectDiscussPost(0, page.getOffset(), page.getLimit());
        List<Map<String,Object>> discussPostMap = new ArrayList<>();

        if (discussPostList != null) {
            for (DiscussPost discussPost : discussPostList){
                Map<String,Object> map = new HashMap<>();
                map.put("discussPost", discussPost);
                User user = userService.selectUserById(discussPost.getUserId());
                map.put("user",user);
                discussPostMap.add(map);
            }
        }

        model.addAttribute("discussPostMap",discussPostMap);
        return "/index";
    }

}
