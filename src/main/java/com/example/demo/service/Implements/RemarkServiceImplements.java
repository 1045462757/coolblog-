package com.example.demo.service.Implements;

import com.example.demo.entity.blog.Blog;
import com.example.demo.entity.remark.Remark;
import com.example.demo.repository.RemarkRepository;
import com.example.demo.service.RemarkService;
import com.example.demo.utility.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class RemarkServiceImplements implements RemarkService {

    @Resource
    private RemarkRepository remarkRepository;

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    @Transactional
    public Result saveRemark(Remark remark) {
        if (remark != null) {
            remarkRepository.save(remark);
            logger.info("保存一条评论:" + remark);
            return new Result(Result.success, remark);
        } else {
            return new Result(Result.failed, "remark为空");
        }
    }

    @Override
    @Transactional
    public Result modifyRemark(Remark remark) {
        if (remark != null) {
            Remark validRemark = remarkRepository.findByRemarkId(remark.getRemarkId());
            if (validRemark != null) {
                validRemark.setContent(remark.getContent());
                logger.info("修改评论信息:" + remark);
                return new Result(Result.success, validRemark);
            } else {
                return new Result(Result.failed, "不存在的博客");
            }
        } else {
            return new Result(Result.failed, "remark为空");
        }
    }

    @Override
    public Result removeRemark(int remarkId) {
        Remark validRemark = remarkRepository.findByRemarkId(remarkId);
        if (validRemark != null) {
            remarkRepository.deleteById(remarkId);
            logger.info("删除博客:" + validRemark);
            return new Result(Result.success, validRemark);
        } else {
            return new Result(Result.failed, "不存在的评论");
        }
    }

    @Override
    public Remark findRemark(int remarkId) {
        return remarkRepository.findByRemarkId(remarkId);
    }

    @Override
    @Transactional
    public int removeAllRemark(Blog blogId) {
        return remarkRepository.deleteAllByBlogId(blogId);
    }
}
