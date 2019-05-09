package com.example.demo.service.Implements;

import com.example.demo.entity.blog.Blog;
import com.example.demo.entity.relation.BlogCollection;
import com.example.demo.entity.relation.UserCollection;
import com.example.demo.entity.user.UserInformation;
import com.example.demo.repository.BlogCollectionRepository;
import com.example.demo.repository.UserCollectionRepository;
import com.example.demo.service.BlogService;
import com.example.demo.service.CollectionService;
import com.example.demo.service.UserService;
import com.example.demo.utility.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class CollectionServiceImplements implements CollectionService {

    @Resource
    private UserService userService;

    @Resource
    private BlogService blogService;

    @Resource
    private BlogCollectionRepository blogCollectionRepository;

    @Resource
    private UserCollectionRepository userCollectionRepository;

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    @Transactional
    public Result addBlogCollection(int userId, int blogId) {
        Result getUserResult = userService.getInformation(userId);
        Result getBlogResult = blogService.getBlog(blogId);
        if (getUserResult.getStatus().equals(Result.success) && getBlogResult.getStatus().equals(Result.success)) {
            UserInformation validUser = (UserInformation) getUserResult.getData();
            Blog validBlog = (Blog) getBlogResult.getData();
            int collect = validBlog.getCollect();
            validBlog.setCollect(collect + 1);
            BlogCollection blogCollection = new BlogCollection();
            blogCollection.setCollectBlog(validBlog);
            blogCollection.setCollector(validUser);
            blogCollectionRepository.save(blogCollection);
            logger.info("保存收藏:" + " 收藏用户Id:" + validUser.getUserId() + ", 收藏博客Id:" + validBlog.getBlogId());
            return new Result(Result.success, null);
        } else {
            return new Result(Result.failed, "不存在的用户或博客");
        }
    }

    @Override
    @Transactional
    public Result removeBlogCollection(int userId, int collectionBlogId) {
        Result getUserResult = userService.getInformation(userId);
        Result getBlogResult = blogService.getBlog(collectionBlogId);
        if (getUserResult.getStatus().equals(Result.success) && getBlogResult.getStatus().equals(Result.success)) {
            UserInformation validUser = (UserInformation) getUserResult.getData();
            Blog validBlog = (Blog) getBlogResult.getData();
            BlogCollection blogCollection = blogCollectionRepository.findByCollectorAndCollectBlog(validUser, validBlog);
            if (blogCollection != null) {
                int collect = validBlog.getCollect();
                validBlog.setCollect(collect - 1);
                blogCollectionRepository.deleteById(blogCollection.getBlogCollectionId());
                logger.info("删除收藏:" + " 收藏用户Id:" + validUser.getUserId() + ", 收藏博客Id:" + validBlog.getBlogId());
                return new Result(Result.success, null);
            } else {
                return new Result(Result.failed, "不存在的收藏关系");
            }
        } else {
            return new Result(Result.failed, "不存在的用户或博客");
        }
    }

    @Override
    public boolean findBlogCollection(int userId, int blogId) {
        Result getUserResult = userService.getInformation(userId);
        Result getBlogResult = blogService.getBlog(blogId);
        if (getUserResult.getStatus().equals(Result.success) && getBlogResult.getStatus().equals(Result.success)) {
            UserInformation validUser = (UserInformation) getUserResult.getData();
            Blog validBlog = (Blog) getBlogResult.getData();
            BlogCollection blogCollection = blogCollectionRepository.findByCollectorAndCollectBlog(validUser, validBlog);
            return (blogCollection != null);
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public Result addUserCollection(int focusId, int fansId) {
        Result getFocusResult = userService.getInformation(focusId);
        Result getFansResult = userService.getInformation(fansId);
        if (getFocusResult.getStatus().equals(Result.success) && getFansResult.getStatus().equals(Result.success)) {
            UserInformation focus = (UserInformation) getFocusResult.getData();
            UserInformation fans = (UserInformation) getFansResult.getData();
            UserCollection userCollection = new UserCollection();
            userCollection.setFocus(focus);
            userCollection.setFans(fans);
            userCollectionRepository.save(userCollection);
            logger.info("保存关注:" + " 关注用户Id:" + focus.getUserId() + ", 粉丝Id:" + fans.getUserId());
            return new Result(Result.success, null);
        } else {
            return new Result(Result.failed, "不存在的用户");
        }
    }

    @Override
    @Transactional
    public Result removeUserCollection(int focusId, int fansId) {
        Result getFocusResult = userService.getInformation(focusId);
        Result getFansResult = userService.getInformation(fansId);
        if (getFocusResult.getStatus().equals(Result.success) && getFansResult.getStatus().equals(Result.success)) {
            UserInformation focus = (UserInformation) getFocusResult.getData();
            UserInformation fans = (UserInformation) getFansResult.getData();
            UserCollection userCollection = userCollectionRepository.findByFocusAndFans(focus, fans);
            if (userCollection != null) {
                userCollectionRepository.deleteById(userCollection.getUserCollectionId());
                logger.info("删除关注:" + " 关注用户Id:" + focus.getUserId() + ", 粉丝Id:" + fans.getUserId());
                return new Result(Result.success, null);
            } else {
                return new Result(Result.failed, "不存在的关注关系");
            }
        } else {
            return new Result(Result.failed, "不存在的用户");
        }
    }

    @Override
    public boolean findUserCollection(int focusId, int fansId) {
        Result getFocusResult = userService.getInformation(focusId);
        Result getFansResult = userService.getInformation(fansId);
        if (getFocusResult.getStatus().equals(Result.success) && getFansResult.getStatus().equals(Result.success)) {
            UserInformation focus = (UserInformation) getFocusResult.getData();
            UserInformation fans = (UserInformation) getFansResult.getData();
            UserCollection userCollection = userCollectionRepository.findByFocusAndFans(focus, fans);
            return (userCollection != null);
        } else {
            return false;
        }
    }
}
