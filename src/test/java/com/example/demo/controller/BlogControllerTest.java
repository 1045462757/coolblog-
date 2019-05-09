package com.example.demo.controller;

import com.example.demo.controller.business.blog.BlogController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogControllerTest {

    @Resource
    private BlogController blogController;

    @Test
    public void RetrieveBlogTest() {
//        System.out.println(blogController.RetrieveBlog(1, 32));
    }
}
