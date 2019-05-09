package com.example.demo.controller.business.user;

import com.example.demo.entity.user.UserAccount;
import com.example.demo.entity.user.UserInformation;
import com.example.demo.service.CollectionService;
import com.example.demo.service.SendMailService;
import com.example.demo.service.UserService;
import com.example.demo.utility.Result;
import com.example.demo.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
//生产环境
//@CrossOrigin(origins = "http://www.hylovecode.cn")
//本地测试
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private CollectionService collectionService;

    @Resource
    private SendMailService sendMailService;

    private Logger logger = LogManager.getLogger(this.getClass());

    @Value("${static.avatar.path}")
    private String avatarPath;

    @Value("${static.server.address}")
    private String serverAddress;

    /*
     * RestfulApi
     */

    /**
     * 登录
     *
     * @param userAccount 账号对象
     * @return ResultBody
     * status:101,message:参数错误
     * status:102,message:1.该用户不存在,2.密码错误
     * data:该用户的Id
     * @since 2019年4月6日17:48:13
     */
    @PostMapping(value = "user")
    @ResponseBody
    public String Login(@RequestBody UserAccount userAccount) {
        if (userAccount != null) {
            userAccount.setPassword(Utility.md5(userAccount.getPassword()));
            Result loginResult = userService.login(userAccount);
            if (loginResult.getStatus().equals(Result.success)) {
                UserAccount validUserAccount = (UserAccount) loginResult.getData();
                return Utility.ResultBody(200, null, validUserAccount.getUserId());
            } else {
                return Utility.ResultBody(102, loginResult.getMessage(), null);
            }
        } else {
            return Utility.ResultBody(101, "参数错误", null);
        }
    }

    /**
     * 注册
     *
     * @param account  账号
     * @param password 密码
     * @param email    邮箱
     * @return ResultBody
     * status:111,message:参数错误
     * status:112,message:该账号已存在
     * @since 2019年4月6日17:49:58
     */
    @PostMapping(value = "user/account")
    @ResponseBody
    public String Register(@RequestParam String account, @RequestParam String password, @RequestParam String email) {
        if (account != null && account.length() != 0 && password != null && password.length() != 0 && email != null && email.length() != 0) {
            UserAccount userAccount = new UserAccount(account, Utility.md5(password));
            Result registerResult = userService.register(userAccount, email);
            if (registerResult.getStatus().equals(Result.success)) {
                return Utility.ResultBody(200, null, null);
            } else {
                return Utility.ResultBody(112, registerResult.getMessage(), null);
            }
        } else {
            return Utility.ResultBody(111, "参数错误", null);
        }
    }

    /**
     * 获取用户信息
     *
     * @param userId 用户Id
     * @return status:200,131
     * @since 2019年4月6日10:30:23
     */
    @GetMapping(value = "user/information")
    @ResponseBody
    public String getInformation(int userId) {
        Result findUserInformationResult = userService.getInformation(userId);
        if (findUserInformationResult.getStatus().equals(Result.success)) {
            return Utility.ResultBody(200, null, Utility.UserInformationBody((UserInformation) findUserInformationResult.getData()));
        } else {
            return Utility.ResultBody(131, findUserInformationResult.getMessage(), null);
        }
    }

    /**
     * 修改用户信息
     *
     * @param userInformation 用户信息
     * @return status:200,141
     * @since 2019年4月6日10:50:13
     */
    @PutMapping(value = "user/information")
    @ResponseBody
    public String UpdateUserInformation(@RequestBody UserInformation userInformation) {
        Result modifyResult = userService.modifyInformation(userInformation);
        if (modifyResult.getStatus().equals(Result.success)) {
            return Utility.ResultBody(200, null, null);
        } else {
            return Utility.ResultBody(141, modifyResult.getMessage(), null);
        }
    }

    /**
     * 上传头像
     *
     * @param file   头像
     * @param userId 用户Id
     * @return status:200,151,152,153
     */
    @PostMapping(value = "avatar")
    @ResponseBody
    public String uploadAvatar(MultipartFile file, int userId) {
        Result findUserInformationResult = userService.getInformation(userId);
        if (findUserInformationResult.getStatus().equals(Result.success)) {
            String fileName = UUID.randomUUID() + file.getOriginalFilename();
            try {
                file.transferTo(new File(avatarPath + fileName));
                Result modifyResult = userService.modifyAvatar(userId, Utility.avatarAddress(serverAddress, fileName));
                if (modifyResult.getStatus().equals(Result.success)) {
                    logger.info("[保存用户头像 : " + avatarPath + fileName + ']');
                    return Utility.ResultBody(200, null, Utility.avatarAddress(serverAddress, fileName));
                } else {
                    return Utility.ResultBody(153, modifyResult.getMessage(), null);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Utility.ResultBody(152, "好像失败了", null);
        } else {
            return Utility.ResultBody(151, findUserInformationResult.getMessage(), null);
        }
    }

    /**
     * 关注用户
     *
     * @param focusId 关注用户Id
     * @param fansId  粉丝Id
     * @return status:200,161
     * @since 2019年4月7日17:19:24
     */
    @PostMapping(value = "userCollection")
    @ResponseBody
    public String CreateBlogCollection(int focusId, int fansId) {
        Result createUserCollectionResult = collectionService.addUserCollection(focusId, fansId);
        if (createUserCollectionResult.getStatus().equals(Result.success)) {
            return Utility.ResultBody(200, null, null);
        } else {
            return Utility.ResultBody(161, createUserCollectionResult.getMessage(), null);
        }
    }

    /**
     * 取消关注用户
     *
     * @param focusId 关注用户Id
     * @param fansId  粉丝Id
     * @return status:200,171
     * @since 2019年4月7日17:21:40
     */
    @DeleteMapping(value = "userCollection")
    @ResponseBody
    public String DeleteBlogCollection(int focusId, int fansId) {
        Result deleteUserCollectionResult = collectionService.removeUserCollection(focusId, fansId);
        if (deleteUserCollectionResult.getStatus().equals(Result.success)) {
            return Utility.ResultBody(200, null, null);
        } else {
            return Utility.ResultBody(171, deleteUserCollectionResult.getMessage(), null);
        }
    }

    /**
     * 获取是否关注用户
     *
     * @param focusId 关注用户Id
     * @param fansId  粉丝Id
     * @return status:200,181
     * @since 2019年4月7日17:22:40
     */
    @GetMapping(value = "user/information/others")
    @ResponseBody
    public String getInformation(int focusId, int fansId) {
        Result findUserInformationResult = userService.getInformation(focusId);
        if (findUserInformationResult.getStatus().equals(Result.success)) {
            UserInformation validUserInformation = (UserInformation) findUserInformationResult.getData();
            Map<String, Object> userMap = Utility.UserInformationBody(validUserInformation);
            userMap.put("isFocus", collectionService.findUserCollection(focusId, fansId));
            return Utility.ResultBody(200, null, userMap);
        } else {
            return Utility.ResultBody(181, findUserInformationResult.getMessage(), null);
        }
    }

    /**
     * 重置密码
     *
     * @param email 邮箱
     * @return status:191,192,193
     * @since 2019年4月13日13:30:39
     */
    @PutMapping(value = "user/password")
    @ResponseBody
    public String ResetPassword(@RequestParam String email) {
        if (email != null && email.length() != 0) {
            Result findUserInformationResult = userService.getInformationByEmail(email);
            if (findUserInformationResult.getStatus().equals(Result.success)) {
                UserInformation userInformation = (UserInformation) findUserInformationResult.getData();
                String newPassword = Utility.randomPassword();
                Result modifyPasswordResult = userService.modifyPassword(userInformation.getUserId(), newPassword);
                if (modifyPasswordResult.getStatus().equals(Result.success)) {
                    sendMailService.sendMailForResetPassWord(email, newPassword);
                    return Utility.ResultBody(200, null, null);
                } else {
                    return Utility.ResultBody(193, modifyPasswordResult.getMessage(), null);
                }
            } else {
                return Utility.ResultBody(192, findUserInformationResult.getMessage(), null);
            }
        } else {
            return Utility.ResultBody(191, "参数为空", null);
        }
    }

    /**
     * 修改密码
     *
     * @param userId      用户Id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return status:201,202,203,204
     * @since 2019年4月19日17:18:03
     */
    @PutMapping(value = "user/information/password")
    @ResponseBody
    public String modifyPassword(@RequestParam int userId, @RequestParam String oldPassword, @RequestParam String newPassword) {
        if (oldPassword != null && oldPassword.length() != 0 && newPassword != null && newPassword.length() != 0) {
            Result findUserAccount = userService.getAccount(userId);
            if (findUserAccount.getStatus().equals(Result.success)) {
                UserAccount validUserAccount = (UserAccount) findUserAccount.getData();
                if (validUserAccount.getPassword().equals(Utility.md5(oldPassword))) {
                    Result modifyPasswordResult = userService.modifyPassword(userId, newPassword);
                    if (modifyPasswordResult.getStatus().equals(Result.success)) {
                        return Utility.ResultBody(200, null, null);
                    } else {
                        return Utility.ResultBody(204, modifyPasswordResult.getMessage(), null);
                    }
                } else {
                    return Utility.ResultBody(203, "原密码错误", null);
                }
            } else {
                return Utility.ResultBody(202, findUserAccount.getMessage(), null);
            }
        } else {
            return Utility.ResultBody(201, "参数为空", null);
        }
    }

}
