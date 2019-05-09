package com.example.demo.entity.Remark;

import com.example.demo.service.RemarkService;
import com.example.demo.utility.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RemarkTest {

    @Resource
    private RemarkService remarkService;

    @Test
    public void removeRemark() {
//        Result result = remarkService.removeRemark(5);
    }

    @Test
    public void findRemark(){
//        System.out.println(remarkService.findRemark(10));
    }
}
