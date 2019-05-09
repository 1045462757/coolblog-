package com.example.demo.service.Implements;

import com.example.demo.entity.blog.Blog;
import com.example.demo.entity.relation.BlogCollection;
import com.example.demo.entity.relation.UserCollection;
import com.example.demo.entity.remark.Remark;
import com.example.demo.entity.user.UserAccount;
import com.example.demo.entity.user.UserInformation;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.UserInformationRepository;
import com.example.demo.service.UserService;
import com.example.demo.utility.Result;
import com.example.demo.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImplements implements UserService {

    @Resource
    private UserAccountRepository userAccountRepository;

    @Resource
    private UserInformationRepository userInformationRepository;

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    @Transactional
    public Result register(UserAccount userAccount, String email) {
        if (userAccount != null) {
            UserAccount validUserAccount = userAccountRepository.findByAccount(userAccount.getAccount());
            if (validUserAccount == null) {
                UserInformation userInformation = userInformationRepository.findByEmail(email);
                if (userInformation == null) {
                    userAccountRepository.save(userAccount);
                    userInformationRepository.save(new UserInformation(userAccount.getAccount(), email));
                    logger.info("注册新用户:" + userAccount.getAccount());
                    return new Result(Result.success, userAccount);
                } else {
                    return new Result(Result.failed, "该邮箱已被注册");
                }
            } else {
                return new Result(Result.failed, "该账号已被注册");
            }
        } else {
            return new Result(Result.failed, "userAccount为空");
        }
    }

    @Override
    @Transactional
    public Result login(UserAccount userAccount) {
        if (userAccount != null) {
            UserAccount validUserAccount = userAccountRepository.findByAccount(userAccount.getAccount());
            if (validUserAccount != null) {
                if (userAccount.getPassword().equals(validUserAccount.getPassword())) {
                    validUserAccount.setLastLoginTime(new Date());
                    logger.info("登录用户:" + validUserAccount.getAccount());
                    return new Result(Result.success, validUserAccount);
                } else {
                    return new Result(Result.failed, "密码错误");
                }
            } else {
                return new Result(Result.failed, "账号不存在");
            }
        } else {
            return new Result(Result.failed, "userAccount为空");
        }
    }

    @Override
    public Result getInformation(int userId) {
        UserInformation userInformation = userInformationRepository.findByUserId(userId);
        if (userInformation != null) {
            logger.info("查询用户信息:" + userInformation);
            return new Result(Result.success, userInformation);
        } else {
            return new Result(Result.failed, "该用户不存在");
        }
    }

    @Override
    public Result getAccount(int userId) {
        UserAccount userAccount = userAccountRepository.findByUserId(userId);
        if (userAccount != null) {
            logger.info("查询用户账号:" + userAccount);
            return new Result(Result.success, userAccount);
        } else {
            return new Result(Result.failed, "该用户不存在");
        }
    }

    @Override
    public List<UserInformation> getInformationLikeName(String name) {
        if (name != null && name.length() != 0) {
            List<UserInformation> userInformationList = userInformationRepository.findByNameContaining(name);
            if (userInformationList != null && userInformationList.size() > 0) {
                logger.info("模糊查询用户信息: 用户名:" + name + ": " + userInformationList);
                return userInformationList;
            }
        }
        return null;
    }


    @Override
    public Result getInformationByEmail(String email) {
        if (email != null && email.length() != 0) {
            UserInformation userInformation = userInformationRepository.findByEmail(email);
            if (userInformation != null) {
                logger.info("查询用户信息:" + userInformation);
                return new Result(Result.success, userInformation);
            } else {
                return new Result(Result.failed, "该用户不存在");
            }
        } else {
            return new Result(Result.failed, "参数为空");
        }
    }

    @Override
    @Transactional
    public Result modifyInformation(UserInformation userInformation) {
        if (userInformation != null) {
            UserInformation validUserInformation = userInformationRepository.findByUserId(userInformation.getUserId());
            if (validUserInformation != null) {
                validUserInformation.setAge(Utility.getAgeByBirth(userInformation.getBirthday()));
                validUserInformation.setBirthday(userInformation.getBirthday());
                validUserInformation.setEmail(userInformation.getEmail());
                validUserInformation.setHobby(userInformation.getHobby());
                validUserInformation.setHome(userInformation.getHome());
                validUserInformation.setIntroduction(userInformation.getIntroduction());
                validUserInformation.setName(userInformation.getName());
                validUserInformation.setPhone(userInformation.getPhone());
                validUserInformation.setQq(userInformation.getQq());
                validUserInformation.setWeChat(userInformation.getWeChat());
                validUserInformation.setWork(userInformation.getWork());
                validUserInformation.setSchool(userInformation.getSchool());
                validUserInformation.setSex(userInformation.getSex());
                logger.info("修改用户信息:" + validUserInformation);
                return new Result(Result.success, validUserInformation);
            } else {
                return new Result(Result.failed, "不存在的用户");
            }
        } else {
            return new Result(Result.failed, "userInformation为空");
        }
    }

    @Override
    @Transactional
    public Result modifyPassword(int userId, String password) {
        if (password != null && password.length() != 0) {
            UserAccount validUserAccount = userAccountRepository.findByUserId(userId);
            if (validUserAccount != null) {
                validUserAccount.setPassword(Utility.md5(password));
                logger.info("修改用户密码:" + validUserAccount);
                return new Result(Result.success, validUserAccount);
            } else {
                return new Result(Result.failed, "用户不存在");
            }
        } else {
            return new Result(Result.failed, "参数为空");
        }
    }

    @Override
    @Transactional
    public Result modifyAvatar(int userId, String avatar) {
        if (avatar != null && avatar.length() != 0) {
            UserInformation validUserInformation = userInformationRepository.findByUserId(userId);
            if (validUserInformation != null) {
                validUserInformation.setHeadPortrait(avatar);
                logger.info("修改用户头像:" + validUserInformation);
                return new Result(Result.success, validUserInformation);
            } else {
                return new Result(Result.failed, "用户不存在");
            }
        } else {
            return new Result(Result.failed, "参数为空");
        }
    }

    @Override
    @Transactional
    public Set<Blog> getUserBlog(int userId) {
        UserInformation userInformation = userInformationRepository.findByUserId(userId);
        if (userInformation != null) {
            Set<Blog> userBlog = userInformation.getMyBlog();
            userBlog.size();
            logger.info("获取用户博客: 用户Id:" + userId + " :" + userBlog);
            return userBlog;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Set<Remark> getUserRemark(int userId) {
        UserInformation userInformation = userInformationRepository.findByUserId(userId);
        if (userInformation != null) {
            Set<Remark> userRemark = userInformation.getMyRemark();
            userRemark.size();
            logger.info("获取用户评论: 用户Id:" + userId + " :" + userRemark);
            return userRemark;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Set<Blog> getUserBlogCollection(int userId) {
        UserInformation userInformation = userInformationRepository.findByUserId(userId);
        if (userInformation != null) {
            Set<BlogCollection> blogCollections = userInformation.getBlogCollections();
            Set<Blog> collectionBlog = new HashSet<>();
            for (BlogCollection blogCollection : blogCollections) {
                collectionBlog.add(blogCollection.getCollectBlog());
            }
            logger.info("获取用户收藏博客: 用户Id:" + userId + " 博客Id:" + collectionBlog);
            return collectionBlog;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Set<Integer> getUserFans(int userId) {
        UserInformation userInformation = userInformationRepository.findByUserId(userId);
        if (userInformation != null) {
            Set<UserCollection> userCollections = userInformation.getFans();
            Set<Integer> fans = new HashSet<>();
            for (UserCollection userCollection : userCollections) {
                fans.add(userCollection.getFans().getUserId());
            }
            logger.info("获取用户粉丝: 用户Id:" + userId + " 粉丝Id:" + fans);
            return fans;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Set<Integer> getUserFocus(int userId) {
        UserInformation userInformation = userInformationRepository.findByUserId(userId);
        if (userInformation != null) {
            Set<UserCollection> userCollections = userInformation.getFocus();
            Set<Integer> focus = new HashSet<>();
            for (UserCollection userCollection : userCollections) {
                focus.add(userCollection.getFocus().getUserId());
            }
            logger.info("获取用户关注: 用户Id:" + userId + " 关注Id:" + focus);
            return focus;
        } else {
            return null;
        }
    }
}
