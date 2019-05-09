package com.example.demo.repository;

import com.example.demo.entity.blog.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    /**
     * 通过博客Id查找博客
     *
     * @param blogId 该博客Id
     * @return 存在返回该博客，不存在返回null
     */
    Blog findByBlogId(int blogId);

    /**
     * 通过标题模糊查询博客
     *
     * @param title 标题
     * @return 符合条件的博客集合, 不存在返回null
     */
    List<Blog> findByTitleContaining(String title);

    @Override
    List<Blog> findAll();

    void deleteByBlogId(int blogId);
}
