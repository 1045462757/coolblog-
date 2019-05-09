package com.example.demo.repository;

import com.example.demo.entity.user.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Integer> {

    /**
     * 通过userId查找用户信息
     *
     * @param userId 用户Id
     * @return 用户信息
     */
    UserInformation findByUserId(int userId);

    /**
     * 通过用户名模糊查找用户信息
     *
     * @param name 用户名
     * @return 满足条件的userInformation
     */
    List<UserInformation> findByNameContaining(String name);

    /**
     * 通过邮箱查找用户
     *
     * @param email 邮箱
     * @return 用户信息
     */
    UserInformation findByEmail(String email);

}
