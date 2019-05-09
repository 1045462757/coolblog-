package com.example.demo.service;

import com.example.demo.entity.blog.Blog;
import com.example.demo.entity.remark.Remark;
import com.example.demo.entity.user.UserAccount;
import com.example.demo.entity.user.UserInformation;
import com.example.demo.utility.Result;

import java.util.List;
import java.util.Set;

/**
 * 用户业务类
 * 2019.3.7
 * <p>
 * 单元测试完成
 * 2019.3.9
 *
 * @author tiga
 * @version 1.0
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param userAccount 用户账号对象(包含Account,password)
     * @return Result
     * message
     * 1.userAccount为空
     * 2.该账号已存在
     * data
     * 注册信息userAccount
     */
    Result register(UserAccount userAccount, String email);

    /**
     * 用户登录
     *
     * @param userAccount 用户账号对象(包含Account,password)
     * @return Result
     * message
     * 1.userAccount为空
     * 2.该用户不存在
     * 3.密码错误
     * data
     * 登录信息userAccount
     */
    Result login(UserAccount userAccount);

    /**
     * 根据用户Id获取用户信息
     *
     * @param userId 用户id
     * @return Result
     * message
     * 1.该用户不存在
     * 2.查找成功
     * data
     * 该用户的信息userInformation
     */
    Result getInformation(int userId);

    /**
     * 根据用户Id获取用户账号
     *
     * @param userId 用户id
     * @return Result
     * message
     * 1.该用户不存在
     * 2.查找成功
     * data
     * 该用户的信息userAccount
     */
    Result getAccount(int userId);

    /**
     * 根据用户姓名模糊查找用户信息
     *
     * @param name 用户姓名
     * @return 符合条件的用户信息对象集合
     */
    List<UserInformation> getInformationLikeName(String name);

    /**
     * 根据邮箱获取用户信息
     *
     * @param email 邮箱
     * @return Result
     * message
     * 1.该用户不存在
     * 2.查找成功
     * data
     * 该用户的信息userInformation
     */
    Result getInformationByEmail(String email);

    /**
     * 修改用户信息
     *
     * @param userInformation 要修改的用户信息
     * @return Result
     * message
     * 1.userInformation为空
     * 2.不存在的用户
     * data
     * 修改后的userInformation
     */
    Result modifyInformation(UserInformation userInformation);

    /**
     * 修改用户密码
     *
     * @param userId   用户Id
     * @param password 密码
     * @return Result
     * message
     * 1.userInformation为空
     * 2.不存在的用户
     * data
     * 修改后的userInformation
     */
    Result modifyPassword(int userId, String password);

    /**
     * 修改用户头像
     *
     * @param userId 用户Id
     * @param avatar 头像地址
     * @return
     */
    Result modifyAvatar(int userId, String avatar);

    /**
     * 获取用户的博客
     *
     * @param userId 用户Id
     * @return 用户的博客集合或null
     */
    Set<Blog> getUserBlog(int userId);

    /**
     * 获取用户的评论
     *
     * @param userId 用户Id
     * @return 用户的评论集合或null
     */
    Set<Remark> getUserRemark(int userId);

    /**
     * 获取用户收藏的博客
     *
     * @param userId 用户Id
     * @return 用户收藏的博客集合或null
     */
    Set<Blog> getUserBlogCollection(int userId);

    /**
     * 获取用户的粉丝Id
     *
     * @param userId 用户Id
     * @return 用户的粉丝Id集合或null
     */
    Set<Integer> getUserFans(int userId);

    /**
     * 获取用户的关注Id
     *
     * @param userId 用户Id
     * @return 用户的关注Id集合或null
     */
    Set<Integer> getUserFocus(int userId);
}
