//package com.example.demo.listener;
//
//import com.example.demo.repository.UserAccountRepository;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//@WebListener
//public class UserListener implements ServletContextListener {
//
//    @Resource
//    private RedisTemplate redisTemplate;
//
//    @Resource
//    private UserAccountRepository userAccountRepository;
//
//    private static final String ALL_USER = "ALL_USER_LIST";
//
//    private Logger logger = LogManager.getLogger(this.getClass());
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
////        List<UserAccount> userAccounts = userAccountRepository.findAll();
////        redisTemplate.delete(ALL_USER);
////        redisTemplate.opsForList().leftPushAll(ALL_USER, userAccounts);
////        List<UserAccount> userAccountList = redisTemplate.opsForList().range(ALL_USER, 0, -1);
////        System.out.println("缓存的用户数为:" + userAccountList.size());
//        logger.info("上下文初始化");
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        logger.info("上下文销毁");
//    }
//}
