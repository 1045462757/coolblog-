package com.example.demo.service;

import com.example.demo.entity.blog.Blog;
import com.example.demo.entity.remark.Remark;
import com.example.demo.utility.Result;

import java.util.List;
import java.util.Set;

/**
 * 博客业务类
 * 2019.3.7
 * <p>
 * 单元测试完成
 * 2019.3.9
 *
 * @author tiga
 * @version 1.0
 */
public interface BlogService {

    /**
     * 保存博客
     *
     * @param blog 要保存的博客对象
     * @return Result
     * message
     * 1.blog为空
     * data
     * 保存的blog
     */
    Result saveBlog(Blog blog);

    /**
     * 根据标题模糊查询博客
     *
     * @param title 标题
     * @return 符合条件的博客集合或null
     */
    List<Blog> findBlogLikeTitle(String title);

    /**
     * 通过博客Id查找博客
     *
     * @param blogId 博客Id
     * @return Result
     * message
     * 1.该博客不存在
     * data
     * 该blog
     */
    Result getBlog(int blogId);

    /**
     * 修改博客
     *
     * @param blog 要修改的博客
     * @return Result
     * message
     * 1.blog为空
     * 2.不存在的博客
     * data
     * 修改后的blog
     */
    Result modifyBlog(Blog blog);

    /**
     * 通过博客Id删除博客
     *
     * @param blogId 博客Id
     * @return Result
     * message
     * 1.不存在的博客
     * data
     * 删除的blog
     */
    Result removeBlog(int blogId);

    /**
     * 获取博客的所有评论
     *
     * @param blogId 该博客Id
     * @return 所有评论集合
     */
    Set<Remark> getRemark(int blogId);

    /**
     * 获取所有博客
     *
     * @return 所有博客集合
     */
    List<Blog> getAll();
}
