package com.example.demo.repository;

import com.example.demo.entity.blog.Blog;
import com.example.demo.service.BlogService;
import com.example.demo.service.RemarkService;
import com.example.demo.utility.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RemarkRepositoryTest {

    @Resource
    private RemarkService remarkService;

    @Resource
    private BlogService blogService;

    @Test
    public void deleteAllByBlogId() {
//        Result result = blogService.getBlog(33);
//        Blog blog = (Blog) result.getData();
//        System.out.println(remarkService.removeAllRemark(blog));
    }
}
