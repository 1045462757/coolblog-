package com.example.demo.entity.user;

import com.example.demo.entity.blog.Blog;
import com.example.demo.entity.remark.Remark;
import com.example.demo.repository.UserInformationRepository;
import com.example.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInformationTest {

    @Resource
    private UserService userService;

    @Resource
    private UserInformationRepository userInformationRepository;

    private Logger logger = LogManager.getLogger(this.getClass());

    @Test
    public void getMyBlogTest() {
//        Set<Blog> blogs = userService.getUserBlog(1);
//        logger.info("获取该用户的所有博客:" + blogs);
//        for (Blog blog : blogs) {
////            System.out.println(blog);
//            logger.info("获取该用户的所有博客:" + blog);
//        }
    }

    @Test
    public void getMyRemarkTest() {
//        Set<Remark> remarks = userService.getUserRemark(1);
//        for (Remark remark : remarks) {
//            System.out.println(remark);
//        }
    }

    @Test
    public void blogCollectionsTest() {
//        Set<Blog> blogs = userService.getUserBlogCollection(2);
//        for (Blog blog : blogs) {
//            System.out.println(blog);
//        }
    }

    @Test
    public void getFansTest() {
//        Set<Integer> fans = userService.getUserFans(1);
//        for (Integer integer : fans) {
//            System.out.println(integer);
//        }
    }

    @Test
    public void getFocusTest() {
//        Set<Integer> focus = userService.getUserFocus(1);
//        for (Integer integer : focus) {
//            System.out.println(integer);
//        }
    }

    @Test
    public void createTest() {
//        UserInformation userInformation = new UserInformation("张三", "123@qq.com");
//        userInformationRepository.save(userInformation);
    }
}
