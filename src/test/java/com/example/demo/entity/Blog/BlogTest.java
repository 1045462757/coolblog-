package com.example.demo.entity.Blog;

import com.example.demo.entity.blog.Blog;
import com.example.demo.repository.BlogRepository;
import com.example.demo.service.BlogService;
import com.example.demo.utility.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogTest {

    @Resource
    private BlogService blogService;

    @Resource
    private BlogRepository blogRepository;

    @Test
    public void saveBlogTest() {
//        Blog blog = new Blog("测试", "ces", "ces");
//        Result result = blogService.saveBlog(blog);
//        blogRepository.save(blog);
    }

    @Test
    public void findBlogByTitle() {
//        List<Blog> blogs = blogService.findBlogLikeTitle("x");
//        if (blogs != null) {
//            for (Blog blog : blogs) {
//                System.out.println(blog);
//            }
//        }
    }

    @Test
    public void modifyBlog() {
//        Blog blog = new Blog();
//        blog.setBlogId(5);
//        blog.setTitle("修改");
//        blog.setContent("测试修改");
//        blog.setLastModifyTime(new Date());
//        Result result = blogService.modifyBlog(blog);
    }

    @Test
    public void removeBlog() {
        //有问题
//        Result result = blogService.removeBlog(1);
//        System.out.println(result.getMessage());

//        blogRepository.deleteByBlogId(1);
    }

    @Test
    public void findBlog() {
//        Result result = blogService.getBlog(1);
//        Blog blog = (Blog) result.getData();
//        System.out.println(blog);
    }

    @Test
    public void getRemark() {
//        Set<Remark> remarks = blogService.getRemark(1);
//        for (Remark remark : remarks) {
//            System.out.println(remark);
//        }
    }
}
