package com.example.demo.service;

import com.example.demo.entity.relation.BlogCollection;
import com.example.demo.entity.user.UserAccount;
import com.example.demo.entity.user.UserInformation;
import com.example.demo.utility.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void registerTest() {
//        UserAccount userAccount = new UserAccount();
//        userAccount.setAccount("123456789");
//        userAccount.setPassword("123456");
//        Result result = userService.register(userAccount);
//        System.out.println(result.getMessage());
    }

    @Test
    public void loginTest() {
//        UserAccount userAccount = new UserAccount();
//        userAccount.setAccount("12345678");
//        userAccount.setPassword("12345678");
//        Result result = userService.login(userAccount);
//        System.out.println(result.getMessage());
    }

    @Test
    public void getInformation() {
//        Result result = userService.getInformation(1);
//        UserInformation userInformation = (UserInformation) result.getData();
//        Set<BlogCollection> blogs = userInformation.getBlogCollections();
//        for (BlogCollection blogCollection : blogs) {
//            System.out.print("收藏用户:" + blogCollection.getCollector());
//            System.out.println("  收藏的博客Id:" + blogCollection.getCollectBlog().getBlogId());
//            System.out.println(blogCollection.getBlogCollectionId());
//            System.out.println(blogCollection.getCollectBlog());
//        }
    }

    @Test
    public void getInformationByName() {
//        Result result = userService.getInformationLikeName("测");
//        System.out.println(result.getMessage());
//        System.out.println(result.getData());
    }

    @Test
    public void modifyInformation() {
//        UserInformation userInformation = new UserInformation();
//        userInformation.setUserId(3);
//        userInformation.setName("测试");
//        Result result = userService.modifyInformation(userInformation);
//        System.out.println(result.getMessage());
    }

}
