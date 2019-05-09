package com.example.demo.controller;

import com.example.demo.controller.business.remark.RemarkController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RemarkControllerTest {

    @Resource
    private RemarkController remarkController;

    @Test
    public void CreateRemarkTest() {
//        System.out.println(remarkController.CreateRemark(1, 103, "妙"));
    }

    @Test
    public void DeleteRemarkTest() {
//        System.out.println(remarkController.DeleteRemark(1, 4));
    }

    @Test
    public void UpdateRemarkTest(){
//        System.out.println(remarkController.UpdateRemark(1, 4, "测试修改"));
    }
}
