package com.alwan.cimbTest.BlogApps.controller;

import com.alwan.cimbTest.BlogApps.dto.*;
import com.alwan.cimbTest.BlogApps.model.Users;
import com.alwan.cimbTest.BlogApps.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
@Slf4j
public class BlogController {

    @Autowired
    BlogService blogService;


    @PostMapping("/get")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public GetListBlogResponse getListBlog (@RequestBody GetListBlogRequest request){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return blogService.getListBlog(request,username);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public AddBlogResponse addBlog (@RequestBody AddBlogRequest request){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return blogService.addBlog(request,username);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public UpdateBlogResponse updateBlog (@RequestBody UpdateBlogRequest request){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return blogService.updateBlog(request,username);
    }

    @GetMapping("/detail/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public GetDetailBlogResponse getDetailBlog (@PathVariable Long id){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return blogService.getDetailBlog(id,username);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public DeleteBlogResponse deleteBlog (@PathVariable Long id){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return blogService.deleteBlog(id,username);
    }
}
