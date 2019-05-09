package com.example.demo.service;

import com.example.demo.utility.Result;

/**
 * 收藏业务类
 * 2019.3.7
 * <p>
 * 单元测试完成
 * 2019.3.9
 *
 * @author tiga
 * @version 1.0
 */
public interface CollectionService {

    /**
     * 收藏博客
     *
     * @param userId 用户Id
     * @param blogId 博客Id
     * @return Result
     * message
     * 1.不存在的用户或博客
     */
    Result addBlogCollection(int userId, int blogId);

    /**
     * 取消收藏博客
     *
     * @param userId 用户Id
     * @param blogId 博客Id
     * @return Result
     * message
     * data
     */
    Result removeBlogCollection(int userId, int blogId);

    /**
     * 查询博客收藏关系
     *
     * @param userId 用户Id
     * @param blogId 博客Id
     * @return
     */
    boolean findBlogCollection(int userId, int blogId);

    /**
     * 关注用户
     *
     * @param focusId 关注的用户Id
     * @param fansId  粉丝用户Id
     * @return Result
     * message
     * data
     */
    Result addUserCollection(int focusId, int fansId);

    /**
     * 取消关注用户
     *
     * @param focusId 关注的用户Id
     * @param fansId  粉丝用户Id
     * @return Result
     * message
     * data
     */
    Result removeUserCollection(int focusId, int fansId);

    /**
     * 查询用户关注关系
     *
     * @param focusId 关注的用户Id
     * @param fansId  粉丝用户Id
     * @return
     */
    boolean findUserCollection(int focusId, int fansId);
}
