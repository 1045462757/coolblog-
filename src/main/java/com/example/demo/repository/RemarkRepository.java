package com.example.demo.repository;

import com.example.demo.entity.blog.Blog;
import com.example.demo.entity.remark.Remark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemarkRepository extends JpaRepository<Remark, Integer> {

    /**
     * 通过评论Id查找评论
     *
     * @param remarkId 评论Id
     * @return 存在返回该评论, 不存在返回null
     */
    Remark findByRemarkId(int remarkId);

    /**
     * 删除所有该博客所有的评论
     *
     * @param blogId 博客Id
     * @return 删除的条数
     */
    int deleteAllByBlogId(Blog blogId);

}
