package com.example.demo.controller.business.blog;

import com.example.demo.entity.blog.Blog;
import com.example.demo.entity.user.UserInformation;
import com.example.demo.service.BlogService;
import com.example.demo.service.CollectionService;
import com.example.demo.service.UserService;
import com.example.demo.utility.Result;
import com.example.demo.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 博客控制器
 * <p>
 * 增加RestFul接口
 *
 * @author tiga
 * @version 1.2
 * @since 2019.3.18
 * 2019年4月1日16:30:19
 */
@Controller
//生产环境
//@CrossOrigin(origins = "http://www.hylovecode.cn")
//本地测试
@CrossOrigin(origins = "http://localhost:8081")
public class BlogController {

    @Resource
    private BlogService blogService;

    @Resource
    private UserService userService;

    @Resource
    private CollectionService collectionService;

    private Logger logger = LogManager.getLogger(this.getClass());

    @Value("${static.blog.path}")
    private String blogPath;

    @Value("${static.server.address}")
    private String serverAddress;

    /*
     * RestfulApi
     */

    /**
     * 发表博客
     *
     * @param userId  作者Id
     * @param title   博客标题
     * @param content 博客内容
     * @param summary 博客简介
     * @return status:200,101,102,103
     * @since 2019年4月1日15:22:44
     */
    @PostMapping(value = "blog")
    @ResponseBody
    public String CreateBlog(int userId, String title, String content, String summary) {
        if (title != null && title.length() != 0 && content != null && content.length() != 0 && summary != null && summary.length() != 0) {
            Result findUserResult = userService.getInformation(userId);
            if (findUserResult.getStatus().equals(Result.success)) {
                Blog blog = new Blog(title, content, summary);
                blog.setAuthor((UserInformation) findUserResult.getData());
                Result saveBlogResult = blogService.saveBlog(blog);
                if (saveBlogResult.getStatus().equals(Result.success)) {
                    return Utility.ResultBody(200, null, null);
                } else {
                    return Utility.ResultBody(103, saveBlogResult.getMessage(), null);
                }
            } else {
                return Utility.ResultBody(102, findUserResult.getMessage(), null);
            }
        } else {
            return Utility.ResultBody(101, "参数不合法", null);
        }
    }

    /**
     * 删除博客
     *
     * @param userId 作者Id
     * @param blogId 博客Id
     * @return status:200,111,112,113
     * @since 2019年4月1日15:28:28
     */
    @DeleteMapping(value = "blog")
    @ResponseBody
    public String DeleteBlog(int userId, int blogId) {
        Result findBlogResult = blogService.getBlog(blogId);
        if (findBlogResult.getStatus().equals(Result.success)) {
            Blog blog = (Blog) findBlogResult.getData();
            if (blog.getAuthor().getUserId() == userId) {
                Result deleteBlogResult = blogService.removeBlog(blog.getBlogId());
                if (deleteBlogResult.getStatus().equals(Result.success)) {
                    return Utility.ResultBody(200, null, null);
                } else {
                    return Utility.ResultBody(113, deleteBlogResult.getMessage(), null);
                }
            } else {
                return Utility.ResultBody(112, "没有权限，该用户不是该博客的作者", null);
            }
        } else {
            return Utility.ResultBody(111, findBlogResult.getMessage(), null);
        }
    }

    /**
     * 修改博客
     *
     * @param userId  作者Id
     * @param blogId  博客Id
     * @param title   标题
     * @param content 内容
     * @param summary 简介
     * @return status:200,121,122,123
     * @since 2019年4月1日15:31:55
     */
    @PutMapping(value = "blog")
    @ResponseBody
    public String UpdateBlog(int userId, int blogId, String title, String content, String summary) {
        Result findBlogResult = blogService.getBlog(blogId);
        if (findBlogResult.getStatus().equals(Result.success)) {
            Blog blog = (Blog) findBlogResult.getData();
            if (blog.getAuthor().getUserId() == userId) {
                blog.setBlogId(blogId);
                blog.setTitle(title);
                blog.setContent(content);
                blog.setSummary(summary);
                Result modifyBlogResult = blogService.modifyBlog(blog);
                if (modifyBlogResult.getStatus().equals(Result.success)) {
                    return Utility.ResultBody(200, null, null);
                } else {
                    return Utility.ResultBody(123, modifyBlogResult.getMessage(), null);
                }
            } else {
                return Utility.ResultBody(122, "没有权限，该用户不是该博客的作者", null);
            }
        } else {
            return Utility.ResultBody(121, findBlogResult.getMessage(), null);
        }
    }

    /**
     * 查看博客
     *
     * @param userId 用户Id
     * @param blogId 博客Id
     * @return status:200,131
     * @since 2019年4月1日15:41:40
     */
    @GetMapping(value = "blog")
    @ResponseBody
    public String RetrieveBlog(int userId, int blogId) {
        Result findBlogResult = blogService.getBlog(blogId);
        if (findBlogResult.getStatus().equals(Result.success)) {
            Blog validBlog = (Blog) findBlogResult.getData();
            validBlog.setRemarks(blogService.getRemark(blogId));
            Map<String, Object> blogMap = Utility.BlogBody(validBlog);
            blogMap.put("isCollect", collectionService.findBlogCollection(userId, blogId));
            return Utility.ResultBody(200, null, blogMap);
        } else {
            return Utility.ResultBody(131, findBlogResult.getMessage(), null);
        }
    }

    /**
     * 获取博客集合
     *
     * @param userId 作者Id
     * @param type   查询类型(1：用户自己的博客，2：用户收藏的博客，3：精彩博客)
     * @return status:200,141
     * @since 2019年4月1日15:43:40
     */
    @GetMapping(value = "blog/user")
    @ResponseBody
    public String RetrieveBlogList(int userId, int type) {
        Result findUserResult = userService.getInformation(userId);
        if (findUserResult.getStatus().equals(Result.success)) {
            Set<Blog> blogSet;
            if (type == 1) {
                blogSet = userService.getUserBlog(userId);
            } else if (type == 2) {
                blogSet = userService.getUserBlogCollection(userId);
            } else if (type == 3) {
                //获取精彩博客
                List<Blog> blogList = blogService.getAll();
                return Utility.ResultBody(200, null, Utility.BlogListBody(blogList));
            } else {
                return Utility.ResultBody(142, "错误的查询类型", null);
            }
            //对博客集合进行排序
            List<Blog> blogList = new ArrayList<>(blogSet);
            Collections.sort(blogList, new Comparator<Blog>() {
                @Override
                public int compare(Blog o1, Blog o2) {
                    return o1.getBlogId() > o2.getBlogId() ? -1 : 1;
                }
            });
            return Utility.ResultBody(200, null, Utility.BlogListBody(blogList));
        } else {
            return Utility.ResultBody(141, findUserResult.getMessage(), null);
        }
    }

    /**
     * 收藏博客
     *
     * @param userId 用户Id
     * @param blogId 博客Id
     * @return status:200,151
     * @since 2019年4月5日17:23:08
     */
    @PostMapping(value = "blogCollection")
    @ResponseBody
    public String CreateBlogCollection(int userId, int blogId) {
        Result createBlogCollectionResult = collectionService.addBlogCollection(userId, blogId);
        if (createBlogCollectionResult.getStatus().equals(Result.success)) {
            return Utility.ResultBody(200, null, null);
        } else {
            return Utility.ResultBody(151, createBlogCollectionResult.getMessage(), null);
        }
    }

    /**
     * 取消收藏博客
     *
     * @param userId 用户Id
     * @param blogId 博客Id
     * @return status:200,161
     * @since 2019年4月5日17:44:20
     */
    @DeleteMapping(value = "blogCollection")
    @ResponseBody
    public String DeleteBlogCollection(int userId, int blogId) {
        Result deleteBlogCollectionResult = collectionService.removeBlogCollection(userId, blogId);
        if (deleteBlogCollectionResult.getStatus().equals(Result.success)) {
            return Utility.ResultBody(200, null, null);
        } else {
            return Utility.ResultBody(161, deleteBlogCollectionResult.getMessage(), null);
        }
    }

    /**
     * 上传图片
     *
     * @param img 图片
     * @return status:200,171
     */
    @PostMapping(value = "blog/image")
    @ResponseBody
    public String uploadBlogImage(MultipartFile img) {
        String fileName = UUID.randomUUID() + img.getOriginalFilename();
        try {
            img.transferTo(new File(blogPath + fileName));
            logger.info("[保存图片 : " + blogPath + fileName + ']');
            return Utility.ResultBody(200, null, Utility.blogAddress(serverAddress, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Utility.ResultBody(171, "好像失败了", null);
    }
}
