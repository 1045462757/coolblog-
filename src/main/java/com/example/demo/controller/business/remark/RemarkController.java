package com.example.demo.controller.business.remark;

import com.example.demo.entity.blog.Blog;
import com.example.demo.entity.remark.Remark;
import com.example.demo.entity.user.UserInformation;
import com.example.demo.service.BlogService;
import com.example.demo.service.RemarkService;
import com.example.demo.service.UserService;
import com.example.demo.utility.Result;
import com.example.demo.utility.Utility;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 评论控制器
 * <p>
 * 增加RestFul接口
 * 2019年4月4日16:47:28
 *
 * @author tiga
 * @version 1.1
 * @since 2019.3.18
 */
@Controller
//生产环境
//@CrossOrigin(origins = "http://www.hylovecode.cn")
//本地测试
@CrossOrigin(origins = "http://localhost:8081")
public class RemarkController {

    @Resource
    private RemarkService remarkService;

    @Resource
    private BlogService blogService;

    @Resource
    private UserService userService;

    /*
     * RestfulApi
     */

    /**
     * 发表评论
     *
     * @param userId  作者Id
     * @param blogId  博客Id
     * @param content 评论内容
     * @return status:200,101,102,103,104
     * @since 2019年4月4日16:30:09
     */
    @PostMapping(value = "remark")
    @ResponseBody
    public String CreateRemark(int userId, int blogId, String content) {
        if (content != null && content.length() != 0) {
            Result findBlogResult = blogService.getBlog(blogId);
            if (findBlogResult.getStatus().equals(Result.success)) {
                Result findUserResult = userService.getInformation(userId);
                if (findUserResult.getStatus().equals(Result.success)) {
                    Remark remark = new Remark((UserInformation) findUserResult.getData(), (Blog) findBlogResult.getData(), content);
                    Result saveRemarkResult = remarkService.saveRemark(remark);
                    if (saveRemarkResult.getStatus().equals(Result.success)) {
                        return Utility.ResultBody(200, null, Utility.RemarkBody((Remark) saveRemarkResult.getData()));
                    } else {
                        return Utility.ResultBody(104, saveRemarkResult.getMessage(), null);
                    }
                } else {
                    return Utility.ResultBody(103, findUserResult.getMessage(), null);
                }
            } else {
                return Utility.ResultBody(102, findBlogResult.getMessage(), null);
            }
        } else {
            return Utility.ResultBody(101, "参数不合法", null);
        }
    }

    /**
     * 删除评论
     *
     * @param userId   作者Id
     * @param remarkId 评论Id
     * @return status:200,111,112,113
     * @since 2019年4月4日16:40:42
     */
    @DeleteMapping(value = "remark")
    @ResponseBody
    public String DeleteRemark(int userId, int remarkId) {
        Remark remark = remarkService.findRemark(remarkId);
        if (remark != null) {
            if (remark.getAuthor().getUserId() == userId) {
                Result deleteRemarkResult = remarkService.removeRemark(remarkId);
                if (deleteRemarkResult.getStatus().equals(Result.success)) {
                    return Utility.ResultBody(200, null, null);
                } else {
                    return Utility.ResultBody(113, deleteRemarkResult.getMessage(), null);
                }
            } else {
                return Utility.ResultBody(112, "没有权限，该用户不是该评论的作者", null);
            }
        } else {
            return Utility.ResultBody(111, "不存在的评论", null);
        }
    }

}
