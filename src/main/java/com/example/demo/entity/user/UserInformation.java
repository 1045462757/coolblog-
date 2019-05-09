package com.example.demo.entity.user;

import com.example.demo.entity.blog.Blog;
import com.example.demo.entity.relation.BlogCollection;
import com.example.demo.entity.relation.UserCollection;
import com.example.demo.entity.remark.Remark;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 用户信息表，保存用户的各种信息
 * 2019.3.7
 *
 * @author tiga
 * @version 1.0
 */
@Entity
@Table(name = "user_information")
public class UserInformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false)
    private String name;

    private String sex;

    @Column(columnDefinition = "date")
    private Date birthday;

    private Integer age;

    private String headPortrait;

    private String hobby;

    private String introduction;

    @Column(nullable = false)
    private String email;

    private String phone;

    private String qq;

    private String weChat;

    private String work;

    private String home;

    private String school;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 1")
    private Integer level;

    //EAGER急加载,加载用户信息就加载这条属性
    //LAZY懒加载,在加载该属性时加上事务注解，已存在的属性不用加载
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Blog.class, mappedBy = "author")
    private Set<Blog> myBlog;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Remark.class, mappedBy = "author")
    private Set<Remark> myRemark;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = BlogCollection.class, mappedBy = "collector")
    private Set<BlogCollection> blogCollections;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = UserCollection.class, mappedBy = "focus")
    private Set<UserCollection> fans;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = UserCollection.class, mappedBy = "fans")
    private Set<UserCollection> focus;

    public UserInformation() {
    }

    public UserInformation(String name, String email) {
        this.name = name;
        this.email = email;
        this.level = 1;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Set<Blog> getMyBlog() {
        return myBlog;
    }

    public void setMyBlog(Set<Blog> myBlog) {
        this.myBlog = myBlog;
    }

    public Set<Remark> getMyRemark() {
        return myRemark;
    }

    public void setMyRemark(Set<Remark> myRemark) {
        this.myRemark = myRemark;
    }

    public Set<BlogCollection> getBlogCollections() {
        return blogCollections;
    }

    public void setBlogCollections(Set<BlogCollection> blogCollections) {
        this.blogCollections = blogCollections;
    }

    public Set<UserCollection> getFans() {
        return fans;
    }

    public void setFans(Set<UserCollection> fans) {
        this.fans = fans;
    }

    public Set<UserCollection> getFocus() {
        return focus;
    }

    public void setFocus(Set<UserCollection> focus) {
        this.focus = focus;
    }

    @Override
    public String toString() {
        return "[用户信息: 用户Id:" + userId + ", 姓名:" + name + "]";
    }

}
