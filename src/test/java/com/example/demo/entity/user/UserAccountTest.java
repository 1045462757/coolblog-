package com.example.demo.entity.user;

import com.example.demo.repository.UserAccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAccountTest {

    @Resource
    private UserAccountRepository userAccountRepository;

    @Test
    public void CreateTest() {
//        UserAccount userAccount = new UserAccount("12345678", "12345678");
//        userAccountRepository.save(userAccount);
    }
}
