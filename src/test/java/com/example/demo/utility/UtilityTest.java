package com.example.demo.utility;

import com.example.demo.service.RemarkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilityTest {

    @Resource
    private RemarkService remarkService;

    @Test
    public void JsonTest() {
//        System.out.println(Utility.ResultBody(500, "具体错误信息", "没有登录"));
//        System.out.println(Utility.ResultBody(500, "具体错误信息", null));
//        System.out.println(Utility.ResultBody(500, null, null));
//        System.out.println(Utility.ResultBody(500, null, "data"));
    }

    @Test
    public void RemarkBodyTest() {
//        System.out.println(Utility.RemarkBody(remarkService.findRemark(29)));
    }

    @Test
    public void randomPassword() {
        System.out.println(Utility.randomPassword());
    }

    @Test
    public void getAgeByBirthday(){
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date birthday = format.parse("2999-04-14");
//            System.out.println(Utility.getAgeByBirth(birthday));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void getNowDate(){
//        System.out.println(Utility.getNowDate());
    }
}
