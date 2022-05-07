package com.example.demo;

import com.example.demo.dao.AlphaDao;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class )
class DemoApplicationTests  implements ApplicationContextAware {

     private  ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
          this.applicationContext=applicationContext;
    }

    @Test
    public void textApplicationContext()  {
        System.out.println(applicationContext);

        AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
        System.out.println(alphaDao.select());

        alphaDao = applicationContext.getBean("AlphaHibernate",AlphaDao.class);
        System.out.println(alphaDao.select());
    }

    @Test
    public void textBeanManagement(){
        AlphaService service = applicationContext.getBean(AlphaService.class);
        System.out.println(service);
    }

    @Test
    public void textSimpleDateFormat(){
        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }

    }

