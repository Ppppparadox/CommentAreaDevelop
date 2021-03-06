package com.example.demo.controller;

import com.example.demo.service.AlphaService;
import com.example.demo.util.DemoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("alpha")
public class alphaController {

       @Autowired
       private AlphaService alphaService;

       @RequestMapping("service")
       @ResponseBody
       public String getService(){return alphaService.getAlphaDao();}

       @RequestMapping("hello")
       @ResponseBody
        public String sayHello(){
            return "Hello Spring Boot.";
        }

        @RequestMapping("/http")
        public void http(HttpServletRequest request,  HttpServletResponse response){
            //http请求行的数据
            System.out.println(request.getMethod());
            System.out.println(request.getServletPath());
            //若干行请求的消息头
            Enumeration<String> enumeration =request.getHeaderNames();
            while (enumeration.hasMoreElements()){
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                System.out.println(name+" : "+value);
            }
            //请求体包含的业务数据
            System.out.println(request.getParameter("code"));

            //返回响应数据
            response.setContentType("text/html;charset=utf-8");
            try (
                    PrintWriter writer=response.getWriter();
                    ){
                writer.write("<h1>ParaDox MoonKnight<h1>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**浏览器向服务器传参有两种方式，一是在通过get请求，在路径后加问号携带参数，如/xxx?id=1。
        另一种是通过post请求，在request请求体中携带表单中的参数，这种参数在路径上是看不到的。这
         两种方式所传的参数，在服务端都可以通过request.getParameter(参数名)这样的方式来获取。而
         RequestParam注解，就相当于是request.getParameter()，是从request对象中获取参数的
         有时，我们也愿意利用请求路径本身来传参，即将参数拼到路径里，如/xxx/1，这里的1就是参数，
         那么在解析路径的时候，也是能获取到这个参数的。而@PathVarible就是解析路径，从中获得对
         应级次的参数。
         **/
        //GET请求
       //students?current=1&&limit=20
       @RequestMapping(path = "/students" , method = RequestMethod.GET)
       @ResponseBody
       public String getStudents(
               @RequestParam(name = "current",required = false,defaultValue = "1")  int current ,
               @RequestParam(name = "limit",required = false,defaultValue = "10")  int limit) {
           System.out.println(current);
           System.out.println(limit);
           return "some students";
       }

       //  student/123
       @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
       @ResponseBody
       public String getStudent(@PathVariable("id") int id){
           System.out.println(id);
           return "a student";
       }

       //POST请求
      @RequestMapping(path = "/student",method = RequestMethod.POST)
      @ResponseBody
      public String saveStudent(String name , int age){
          System.out.println(name);
          System.out.println(age);
          return "success";
      }

      // 响应HTML数据1
      @RequestMapping(path = "/teacher",method = RequestMethod.GET)
      public ModelAndView getTecher(){
           ModelAndView mav = new ModelAndView();
           mav.addObject("name","MoonKnight");
           mav.addObject("age","30");
           mav.setViewName("/demo/view");
           return mav;
      }

      // 响应HTML数据2
      @RequestMapping(path = "/school",method = RequestMethod.GET)
      public String getSchool(Model model){
           model.addAttribute("name","浙江大学");
           model.addAttribute("age","100");
           return "/demo/view";
      }

      //响应JSON数据（异步请求）
      //Java对象  ->  JSON字符串  -> JS对象
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
     public List<Map<String,Object>> getEmp(){
        List<Map<String,Object>> list = new ArrayList<>();

        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age","21");
        emp.put("salary","5700");
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","李四");
        emp.put("age","22");
        emp.put("salary","10000");
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","王五");
        emp.put("age","25");
        emp.put("salary","15000");
        list.add(emp);

        return list;
    }

    // cookie示例
    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response) {
        // 创建cookie
        Cookie cookie = new Cookie("code", DemoUtil.generateUUID());
        // 设置cookie生效的范围
        cookie.setPath("/community/alpha");
        // 设置cookie的生存时间
        cookie.setMaxAge(60 * 10);
        // 发送cookie
        response.addCookie(cookie);

        return "success set cookie";
    }

    @RequestMapping(path = "/cookie/get", method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code) {
        System.out.println(code);
        return "success get cookie";
    }

    // session示例
    @RequestMapping(path = "/session/set", method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("id", 1);
        session.setAttribute("name", "Test");
        return "success set session";
    }

    @RequestMapping(path = "/session/get", method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session) {
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "success set session";
    }

}

