package com.example.demo.service.Implements;

import com.example.demo.entity.blog.Blog;
import com.example.demo.entity.remark.Remark;
import com.example.demo.repository.BlogRepository;
import com.example.demo.service.BlogService;
import com.example.demo.utility.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class BlogServiceImplements implements BlogService {

    @Resource
    private BlogRepository blogRepository;

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    @Transactional
    public Result saveBlog(Blog blog) {
        if (blog != null) {
            blogRepository.save(blog);
            logger.info("保存一条博客:" + blog);
            return new Result(Result.success, blog);
        } else {
            return new Result(Result.failed, "blog为空");
        }
    }

    @Override
    public List<Blog> findBlogLikeTitle(String title) {
        if (title != null && title.length() != 0) {
            List<Blog> validBlogList = blogRepository.findByTitleContaining(title);
            if (validBlogList != null) {
                logger.info("模糊查询博客: 博客标题:" + title + ": " + validBlogList);
                return validBlogList;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Result getBlog(int blogId) {
        Blog validBlog = blogRepository.findByBlogId(blogId);
        if (validBlog != null) {
            int browse = validBlog.getBrowse();
            validBlog.setBrowse(browse + 1);
//            logger.info("查询博客:" + validBlog);
            return new Result(Result.success, validBlog);
        } else {
            return new Result(Result.failed, "该博客不存在");
        }
    }

    @Override
    @Transactional
    public Result modifyBlog(Blog blog) {
        if (blog != null) {
            Blog validBlog = blogRepository.findByBlogId(blog.getBlogId());
            if (validBlog != null) {
                validBlog.setTitle(blog.getTitle());
                validBlog.setContent(blog.getContent());
                validBlog.setLastModifyTime(new Date());
                logger.info("修改博客信息:" + blog);
                return new Result(Result.success, blog);
            } else {
                return new Result(Result.failed, "不存在的博客");
            }
        } else {
            return new Result(Result.failed, "blog为空");
        }
    }

    @Override
    @Transactional
    public Result removeBlog(int blogId) {
        Blog validBlog = blogRepository.findByBlogId(blogId);
        if (validBlog != null) {
            blogRepository.deleteById(blogId);
            logger.info("删除博客:" + validBlog);
            return new Result(Result.success, validBlog);
        } else {
            return new Result(Result.failed, "不存在的博客");
        }
    }

    @Override
    @Transactional
    public Set<Remark> getRemark(int blogId) {
        Blog validBlog = blogRepository.findByBlogId(blogId);
        if (validBlog != null) {
            Set<Remark> blogRemarks = validBlog.getRemarks();
            blogRemarks.size();
            logger.info("获取博客评论: 博客Id:" + blogId + " :" + blogRemarks);
            return blogRemarks;
        } else {
            return null;
        }
    }

    @Override
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }
}
