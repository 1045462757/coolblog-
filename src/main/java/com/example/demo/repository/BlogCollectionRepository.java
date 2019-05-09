package com.example.demo.repository;

import com.example.demo.entity.blog.Blog;
import com.example.demo.entity.relation.BlogCollection;
import com.example.demo.entity.user.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogCollectionRepository extends JpaRepository<BlogCollection, Integer> {

    /**
     * 通过用户和博客查找收藏关系
     *
     * @param collector   用户
     * @param collectBlog 博客
     * @return 收藏关系对象或null
     */
    BlogCollection findByCollectorAndCollectBlog(UserInformation collector, Blog collectBlog);
}
