package com.example.demo.repository;

import com.example.demo.entity.user.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    /**
     * 通过账号查找账号信息
     *
     * @param account 账号
     * @return 该账号的信息
     */
    UserAccount findByAccount(String account);

    /**
     * 通过userId查找用户信息
     *
     * @param userId 用户Id
     * @return 用户信息
     */
    UserAccount findByUserId(int userId);
}
