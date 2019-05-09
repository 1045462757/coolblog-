package com.example.demo.service;

import com.example.demo.utility.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectionServiceTest {

    @Resource
    private CollectionService collectionService;

    @Test
    @Transactional
    public void addBlogCollectionTest() {
        Result result = collectionService.addBlogCollection(2, 33);
    }

    @Test
    public void removeBlogCollectionTest() {
//        Result result = collectionService.removeBlogCollection(2, 33);
//        System.out.println(result.getMessage());
    }

    @Test
    public void addUserCollectionTest() {
//        Result result = collectionService.addUserCollection(1, 5);
    }

    @Test
    public void removeUserCollectionTest() {
//        Result result = collectionService.removeUserCollection(1, 5);
//        System.out.println(result.getMessage());
    }

    @Test
    public void findBlogCollectionTest() {
//        System.out.println(collectionService.findBlogCollection(1, 32));
    }
}
