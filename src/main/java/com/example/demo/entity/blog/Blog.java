package com.example.demo.entity.blog;

import com.example.demo.entity.relation.BlogCollection;
import com.example.demo.entity.remark.Remark;
import com.example.demo.entity.user.UserInformation;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * 博客表,保存博客的各种信息
 * 2019.3.7
 *
 * @author tiga
 * @version 1.0
 */
@Entity
@Table(name = "blog")
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @Column(nullable = false)
    private String summary;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;

    private Date lastModifyTime;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer approve;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer browse;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer collect;

    //作者Id从数据库中查找,其余信息懒加载
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = UserInformation.class)
    @JoinColumn(name = "authorId", referencedColumnName = "userId")
    private UserInformation author;

    //评论信息懒加载,博客详情再加载
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Remark.class, mappedBy = "blogId", cascade = CascadeType.REMOVE)
    private Set<Remark> remarks;

    //收藏关系懒加载，博客详情再加载
    @OneToMany(fetch = FetchType.LAZY, targetEntity = BlogCollection.class, mappedBy = "collectBlog", cascade = CascadeType.REMOVE)
    private Set<BlogCollection> blogCollections;

    public Blog() {
    }

    public Blog(String title, String content, String summary) {
        this.title = title;
        this.content = content;
        this.summary = summary;
        this.approve = 0;
        this.browse = 0;
        this.collect = 0;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getApprove() {
        return approve;
    }

    public void setApprove(Integer approve) {
        this.approve = approve;
    }

    public Integer getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public UserInformation getAuthor() {
        return author;
    }

    public void setAuthor(UserInformation author) {
        this.author = author;
    }

    public Set<Remark> getRemarks() {
        return remarks;
    }

    public void setRemarks(Set<Remark> remarks) {
        this.remarks = remarks;
    }

    public Set<BlogCollection> getBlogCollections() {
        return blogCollections;
    }

    public void setBlogCollections(Set<BlogCollection> blogCollections) {
        this.blogCollections = blogCollections;
    }

    @Override
    public String toString() {
        return "[博客: 博客Id:" + blogId + ", 标题:" + title + ", 简介:" + summary + ", 发表时间:" + createTime + ", 作者Id:" + author.getUserId() +
                ", 最后修改时间:" + lastModifyTime + ", 赞同:" + approve + ", 浏览:" + browse + ", 收藏:" + collect + "]";
    }
}
