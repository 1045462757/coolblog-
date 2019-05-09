package com.example.demo.service;

import com.example.demo.entity.remark.Remark;
import com.example.demo.utility.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RemarkServiceTest {

    @Resource
    private RemarkService remarkService;

    @Test
    public void saveRemarkTest() {
//        Remark remark = new Remark();
//        remark.setContent("测试");
//        remark.setCreateTime(new Date());
//        Result result = remarkService.saveRemark(remark);
    }

    @Test
    public void modifyRemark() {
//        Remark remark = new Remark();
//        remark.setRemarkId(5);
//        remark.setContent("测试修改评论");
//        Result result = remarkService.modifyRemark(remark);
    }
}
