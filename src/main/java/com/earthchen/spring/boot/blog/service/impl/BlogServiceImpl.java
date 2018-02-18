package com.earthchen.spring.boot.blog.service.impl;

import com.earthchen.spring.boot.blog.dao.BlogDao;
import com.earthchen.spring.boot.blog.domain.Blog;
import com.earthchen.spring.boot.blog.domain.User;
import com.earthchen.spring.boot.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author: EarthChen
 * @date: 2018/02/18
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogRepository;


    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }


    @Transactional
    @Override
    public void removeBlog(Long id) {
        blogRepository.delete(id);
    }


    @Transactional
    @Override
    public Blog updateBlog(Blog blog) {
        return blogRepository.save(blog);
    }


    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public Page<Blog> listBlogsByTitleLike(User user, String title, Pageable pageable) {
        // 模糊查询
        title = "%" + title + "%";
        Page<Blog> blogs = blogRepository.findByUserAndTitleLikeOrderByCreateTimeDesc(user, title, pageable);
        return blogs;
    }

    @Override
    public Page<Blog> listBlogsByTitleLikeAndSort(User user, String title, Pageable pageable) {
        // 模糊查询
        title = "%" + title + "%";
        Page<Blog> blogs = blogRepository.findByUserAndTitleLike(user, title, pageable);
        return blogs;
    }

    @Override
    public void readingIncrease(Long id) {
        Blog blog = blogRepository.findOne(id);
        blog.setReading(blog.getReading()+1);
        blogRepository.save(blog);
    }

}