package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class LogInController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage() {
        return "/site/register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String register(Model model, User user) {
        Map<String,Object> map = userService.register(user);
        if (map == null || map.isEmpty()) {
            model.addAttribute("msg","注册成功，我们已经向您的邮箱发送了一封激活邮件，请尽快激活！");
            model.addAttribute("target", "/index");
            return "/site/operate-result";
        } else {
            model.addAttribute("usernameMessage", map.get("usernameMessage"));
            model.addAttribute("userPasswordMessage", map.get("userPasswordMessage"));
            model.addAttribute("userEmailMessage", map.get("userEmailMessage"));
            return "/site/register";
        }
    }


}
