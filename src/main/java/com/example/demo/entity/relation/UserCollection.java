package com.example.demo.entity.relation;

import com.example.demo.entity.user.UserInformation;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户关注表,保存用户之间的关注信息
 * 2019.3.7
 *
 * @author tiga
 * @version 1.0
 */
@Entity
@Table(name = "user_collection")
public class UserCollection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userCollectionId;

    //关注用户信息懒加载
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserInformation.class)
    @JoinColumn(name = "focusId", referencedColumnName = "userId")
    private UserInformation focus;

    //粉丝信息懒加载
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserInformation.class)
    @JoinColumn(name = "fansId", referencedColumnName = "userId")
    private UserInformation fans;

    public Integer getUserCollectionId() {
        return userCollectionId;
    }

    public void setUserCollectionId(Integer userCollectionId) {
        this.userCollectionId = userCollectionId;
    }

    public UserInformation getFocus() {
        return focus;
    }

    public void setFocus(UserInformation focus) {
        this.focus = focus;
    }

    public UserInformation getFans() {
        return fans;
    }

    public void setFans(UserInformation fans) {
        this.fans = fans;
    }

    @Override
    public String toString() {
        return "[用户关注表: Id:" + userCollectionId + ", 关注用户Id:" + focus.getUserId() + ", 粉丝用户Id:" + fans.getUserId() + "]";
    }
}
