package com.example.demo.utility;

import com.example.demo.entity.blog.Blog;
import com.example.demo.entity.remark.Remark;
import com.example.demo.entity.user.UserInformation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 工具类
 * 2019.3.7
 *
 * @author tiga
 * @version 1.0
 */
public class Utility {

    /**
     * 网页输出信息
     *
     * @param response response
     * @param alert    message
     * @param href     link
     */
    public static void output(HttpServletResponse response, String alert, String href) {
        try {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("<script>");
            out.println("alert('" + alert + "');");
            if (href.equals("")) {
                out.println("history.back();");
            } else {
                out.println("location.href='" + href + "';");
            }
            out.println("location.href='" + href + "';");
            out.println("</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * md5加密算法
     *
     * @param password 密码
     * @return 加密后的密码
     */
    public static String md5(String password) {
        if (password != null && password.length() != 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                //需要加密的信息
                byte[] input = password.getBytes();
                //加密过的信息
                byte[] output = messageDigest.digest(input);
                //将md5处理后的output结果转出字符串
                //利用Base64算法转出字符串
                return Base64.encodeBase64String(output);
            } catch (NoSuchAlgorithmException e) {
                System.out.println("加密失败");
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * 生成10位随机密码
     *
     * @return 生成的随机密码
     */
    public static String randomPassword() {
        String val = "";
        Random random = new Random();
        //length为几位密码
        for (int i = 0; i < 10; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 业务Json构造
     */
    public static String ResultBody(int status, String message, Object data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        if (message != null) {
            jsonObject.put("message", message);
        }
        if (data != null) {
            jsonObject.put("data", data);
        }
        return jsonObject.toString();
    }

    /**
     * 单个博客信息Json构造
     */
    public static Map BlogBody(Blog blog) {
        Map<String, Object> blogMap = new HashMap<>();
        Map<String, Object> authorMap = new HashMap<>();
        JSONArray jsonArray = new JSONArray();
        blogMap.put("blogId", blog.getBlogId());
        blogMap.put("title", blog.getTitle());
        blogMap.put("content", blog.getContent());
        blogMap.put("summary", blog.getSummary());
        blogMap.put("createTime", blog.getCreateTime().toString());
        if (blog.getLastModifyTime() != null) {
            blogMap.put("lastModifyTime", blog.getLastModifyTime().toString());
        } else {
            blogMap.put("lastModifyTime", blog.getLastModifyTime());
        }
        blogMap.put("approve", blog.getApprove());
        blogMap.put("browse", blog.getBrowse());
        blogMap.put("collect", blog.getCollect());
        authorMap.put("name", blog.getAuthor().getName());
        authorMap.put("userId", blog.getAuthor().getUserId());
        blogMap.put("author", authorMap);
        Set<Remark> remarkSet = blog.getRemarks();
        //对评论集合进行排序
        List<Remark> remarkList = new ArrayList<>(remarkSet);
        Collections.sort(remarkList, new Comparator<Remark>() {
            @Override
            public int compare(Remark o1, Remark o2) {
                return o1.getRemarkId() > o2.getRemarkId() ? -1 : 1;
            }
        });
        for (Remark remark : remarkList) {
            jsonArray.add(RemarkBody(remark));
        }
        blogMap.put("remarks", jsonArray);
        return blogMap;
    }

    /**
     * 多个博客信息Json构造
     */
    public static JSONArray BlogListBody(List<Blog> blogList) {
        if (blogList != null && blogList.size() > 0) {
            JSONArray blogArray = new JSONArray();
            for (Blog blog : blogList) {
                blogArray.add(BlogBody(blog));
            }
            return blogArray;
        } else {
            return new JSONArray();
        }
    }

    /**
     * 单个评论信息Json构造
     */
    public static Map RemarkBody(Remark remark) {
        Map<String, Object> remarkMap = new HashMap<>();
        Map<String, Object> authorMap = new HashMap<>();
        remarkMap.put("remarkId", remark.getRemarkId());
        remarkMap.put("content", remark.getContent());
        if (remark.getCreateTime() != null) {
            remarkMap.put("createTime", remark.getCreateTime().toString());
        } else {
            remarkMap.put("createTime", getNowDate());
        }
        remarkMap.put("approve", remark.getApprove());
        authorMap.put("name", remark.getAuthor().getName());
        authorMap.put("userId", remark.getAuthor().getUserId());
        remarkMap.put("author", authorMap);
        return remarkMap;
    }

    /**
     * 单个用户信息Json构造
     */
    public static Map UserInformationBody(UserInformation userInformation) {
        Map<String, Object> userInformationMap = new HashMap<>();
        userInformationMap.put("userId", userInformation.getUserId());
        userInformationMap.put("name", userInformation.getName());
        userInformationMap.put("sex", userInformation.getSex());
        userInformationMap.put("age", userInformation.getAge());
        userInformationMap.put("email", userInformation.getEmail());
        userInformationMap.put("phone", userInformation.getPhone());
        userInformationMap.put("qq", userInformation.getQq());
        userInformationMap.put("hobby", userInformation.getHobby());
        userInformationMap.put("profession", userInformation.getWork());
        userInformationMap.put("address", userInformation.getHome());
        userInformationMap.put("school", userInformation.getSchool());
        userInformationMap.put("introduction", userInformation.getIntroduction());
        userInformationMap.put("weChat", userInformation.getWeChat());
        if (userInformation.getBirthday() != null) {
            userInformationMap.put("birthday", userInformation.getBirthday().toString().substring(0, 10));
        }
        userInformationMap.put("headPortrait", userInformation.getHeadPortrait());
        return userInformationMap;
    }

    /**
     * 头像地址构造
     */
    public static String avatarAddress(String serverAddress, String fileName) {
        return serverAddress + "avatar/" + fileName;
    }

    /**
     * 博客图片地址构造
     */
    public static String blogAddress(String serverAddress, String fileName) {
        return serverAddress + "blog/" + fileName;
    }

    /**
     * 根据生日计算年龄
     *
     * @param birthday 生日
     * @return 年龄
     */
    public static int getAgeByBirth(Date birthday) {
        int age;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间
            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);
            if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {//兼容性更强,异常后返回数据
            return 0;
        }
    }

    /**
     * 获取现在时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getNowDate() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return ft.format(dNow);
    }

}
