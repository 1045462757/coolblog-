package com.example.demo.service;

import com.example.demo.entity.blog.Blog;
import com.example.demo.entity.remark.Remark;
import com.example.demo.utility.Result;

/**
 * 评论业务类
 * 2019.3.7
 * <p>
 * 单元测试完成
 * 2019.3.9
 * <p>
 * 增加查找评论接口
 * 2019年4月4日16:36:56
 *
 * @author tiga
 * @version 1.1
 */
public interface RemarkService {

    /**
     * 保存一个评论
     *
     * @param remark 该评论
     * @return Result
     * message
     * 1.remark为空
     * data
     * remark
     */
    Result saveRemark(Remark remark);

    /**
     * 修改一个评论
     *
     * @param remark 该评论
     * @return Result
     * message
     * 1.remark为空
     * 2.不存在的博客
     * data
     * 修改后的remark
     */
    Result modifyRemark(Remark remark);

    /**
     * 通过评论Id删除评论
     *
     * @param remarkId 评论Id
     * @return Result
     * message
     * 1.不存在的评论
     * data
     * 删除的remark
     */
    Result removeRemark(int remarkId);

    /**
     * 通过评论Id查找博客
     *
     * @param remarkId 评论Id
     * @return 该评论或null
     */
    Remark findRemark(int remarkId);

    /**
     * 删除该博客所有的评论
     *
     * @param blogId 博客Id
     * @return 删除的评论条数
     */
    int removeAllRemark(Blog blogId);
}
