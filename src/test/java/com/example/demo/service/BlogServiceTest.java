package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogServiceTest {

    @Resource
    private BlogService blogService;

    @Test
    public void subString(){
//        String str = "今天天气真好!";
//        System.out.println(str.substring(0,2));
    }
}
