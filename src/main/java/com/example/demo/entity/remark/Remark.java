package com.example.demo.entity.remark;

import com.example.demo.entity.blog.Blog;
import com.example.demo.entity.user.UserInformation;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 评论表,保存评论的各种信息
 * 2019.3.7
 *
 * @author tiga
 * @version 1.0
 */
@Entity
@Table(name = "remark")
public class Remark implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer remarkId;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;

    //用户信息急加载,需要立即显示评论用户的相关信息
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = UserInformation.class)
    @JoinColumn(name = "authorId", referencedColumnName = "userId")
    private UserInformation author;

    @Column(columnDefinition = "varchar(10000)", nullable = false)
    private String content;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer approve;

    //博客信息懒加载
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Blog.class)
    @JoinColumn(name = "blogId", referencedColumnName = "blogId")
    private Blog blogId;

    public Remark() {
    }

    public Remark(UserInformation author, Blog blogId, String content) {
        this.author = author;
        this.blogId = blogId;
        this.content = content;
        this.approve = 0;
    }

    public Integer getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(Integer remarkId) {
        this.remarkId = remarkId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public UserInformation getAuthor() {
        return author;
    }

    public void setAuthor(UserInformation author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getApprove() {
        return approve;
    }

    public void setApprove(Integer approve) {
        this.approve = approve;
    }

    public Blog getBlogId() {
        return blogId;
    }

    public void setBlogId(Blog blogId) {
        this.blogId = blogId;
    }

    @Override
    public String toString() {
        return "[评论: 评论Id:" + remarkId + ", 作者信息:" + author + ", 博客Id:" + blogId.getBlogId() +
                ", 发表时间:" + createTime + ", 赞同:" + approve + "]";
    }
}
