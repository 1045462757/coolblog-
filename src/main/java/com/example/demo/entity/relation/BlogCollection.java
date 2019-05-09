package com.example.demo.entity.relation;

import com.example.demo.entity.blog.Blog;
import com.example.demo.entity.user.UserInformation;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 博客收藏表,保存用户与博客之间的收藏关系
 * 2019.3.7
 *
 * @author tiga
 * @version 1.0
 */
@Entity
@Table(name = "blog_collection")
public class BlogCollection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogCollectionId;

    //收藏用户信息懒加载
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserInformation.class)
    @JoinColumn(name = "collectorId", referencedColumnName = "userId")
    private UserInformation collector;

    //收藏博客信息懒加载
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Blog.class)
    @JoinColumn(name = "blogId", referencedColumnName = "blogId")
    private Blog collectBlog;

    public Integer getBlogCollectionId() {
        return blogCollectionId;
    }

    public void setBlogCollectionId(Integer blogCollectionId) {
        this.blogCollectionId = blogCollectionId;
    }

    public UserInformation getCollector() {
        return collector;
    }

    public void setCollector(UserInformation collector) {
        this.collector = collector;
    }

    public Blog getCollectBlog() {
        return collectBlog;
    }

    public void setCollectBlog(Blog collectBlog) {
        this.collectBlog = collectBlog;
    }

    @Override
    public String toString() {
        return "[博客收藏关系: Id:" + blogCollectionId + ", 收藏用户Id:" + collector.getUserId() + ", 收藏博客Id:" + collectBlog.getBlogId() + "]";
    }
}
