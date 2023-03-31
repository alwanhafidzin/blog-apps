package com.alwan.cimbTest.BlogApps.service;

import com.alwan.cimbTest.BlogApps.dto.*;
import com.alwan.cimbTest.BlogApps.model.Blogs;
import com.alwan.cimbTest.BlogApps.model.Users;
import com.alwan.cimbTest.BlogApps.repository.BlogRepository;
import com.alwan.cimbTest.BlogApps.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BlogService {

    @Autowired
    private RestService restService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BlogRepository blogRepository;
    public GetListBlogResponse getListBlog(GetListBlogRequest request, String username) {
        log.info("Start get list blog");
        try{
            Optional<Users> optionalUsers =usersRepository.findByUsername(username);
            Pageable pageable = null;
            if(request.getPage() != null && request.getSize() != null){
                pageable = PageRequest.of(request.getPage()-1, request.getSize());
            }
            var pageBlogs =blogRepository.findAllByCreatedByOrderByIdDesc(optionalUsers.get(),pageable);
            List<GetBlogResponseDto> getListBlog = pageBlogs.stream().map(c -> {
                GetBlogResponseDto getDetailBlogResponse = GetBlogResponseDto
                        .builder()
                        .id(c.getId())
                        .category(c.getCategory())
                        .content(c.getContent())
                        .title(c.getTitle())
                        .createdDate(convertToDateString(c.getCreatedDate()))
                        .updatedDate(convertToDateString(c.getUpdatedDate()))
                        .build();
                return getDetailBlogResponse;
            }).collect(Collectors.toList());
            return GetListBlogResponse
                    .builder()
                    .isSuccess(true)
                    .data(getListBlog)
                    .totalElements(pageBlogs.getTotalElements())
                    .numberOfElements((long) pageBlogs.getNumberOfElements())
                    .totalPages((long) pageBlogs.getTotalPages())
                    .build();
        }catch (Exception e){
            throw e;
        }finally {
            log.info("Success get list job");
        }
    }

//    public GetDetailJobResponse getDetailJob(GetDetailJobRequest request) {
//        log.info("Start get detail job");
//        try{
//            var getResponse = restService.get(UrlConstant.URL_DETAIL+request.getId(), ApiResponseDto.class);
//            GetBlogResponseDto getBlogResponseDto = GetBlogResponseDto
//                    .builder()
//                    .id(getResponse.getId())
//                    .type(getResponse.getType())
//                    .company(getResponse.getCompany())
//                    .companyUrl(getResponse.getCompany_url())
//                    .url(getResponse.getUrl())
//                    .createdAt(getResponse.getCreated_at())
//                    .title(getResponse.getTitle())
//                    .location(getResponse.getLocation())
//                    .description(getResponse.getDescription())
//                    .howToApply(getResponse.getHow_to_apply())
//                    .companyLogo(getResponse.getCompany_logo())
//                    .build();
//            return GetDetailJobResponse
//                    .builder()
//                    .status(200L)
//                    .message("Success")
//                    .data(getBlogResponseDto)
//                    .build();
//        }catch (Exception e){
//            throw e;
//        }finally {
//            log.info("Success get detail job");
//        }
//    }

    public AddBlogResponse addBlog(AddBlogRequest request,String username) {
        log.info("start add new blog");
        try{
            Optional<Users> optionalUsers =usersRepository.findByUsername(username);
            Blogs blogs = Blogs
                    .builder()
                    .title(request.getTitle())
                    .content(request.getContent())
                    .category(request.getCategory())
                    .createdDate(new Date())
                    .createdBy(optionalUsers.get())
                    .build();
            blogRepository.save(blogs);
            return AddBlogResponse
                    .builder()
                    .isSuccess(true)
                    .build();
        }catch (Exception e){
            throw  e;
        }finally {
            log.info("Success add new blog");
        }
    }

    public UpdateBlogResponse updateBlog(UpdateBlogRequest request, String username) {
        log.info("start update blog");
        try{
            Optional<Users> optionalUsers =usersRepository.findByUsername(username);
            log.info(optionalUsers.get().toString());
            Optional<Blogs> optionalBlogs = blogRepository.findByIdAndCreatedBy(request.getId(),optionalUsers.get());
            var getDataBlogs = optionalBlogs.get();
            if(request.getCategory() != null || !request.getCategory().isEmpty()){
                getDataBlogs.setCategory(request.getCategory());
            }
            if(request.getTitle() != null || !request.getTitle().isEmpty()){
                getDataBlogs.setTitle(request.getTitle());
            }
            if(request.getContent() != null || !request.getContent().isEmpty()){
                getDataBlogs.setContent(request.getContent());
            }
            getDataBlogs.setUpdatedDate(new Date());
            blogRepository.save(getDataBlogs);
            return UpdateBlogResponse
                    .builder()
                    .isSuccess(true)
                    .build();
        }catch (Exception e){
            throw  e;
        }finally {
            log.info("Success update blog");
        }
    }

    public GetDetailBlogResponse getDetailBlog(Long id, String username) {
        log.info("start get detail blog");
        try{
            Optional<Users> optionalUsers =usersRepository.findByUsername(username);
            Optional<Blogs> optionalBlogs = blogRepository.findByIdAndCreatedBy(id,optionalUsers.get());
            var getData = optionalBlogs.get();
            return GetDetailBlogResponse
                    .builder()
                    .id(getData.getId())
                    .category(getData.getCategory())
                    .content(getData.getContent())
                    .title(getData.getTitle())
                    .createdDate(convertToDateString(getData.getCreatedDate()))
                    .updatedDate(convertToDateString(getData.getUpdatedDate()))
                    .build();
        }catch (Exception e){
            throw  e;
        }finally {
            log.info("Success get detail blog");
        }
    }

    public DeleteBlogResponse deleteBlog(Long id, String username) {
        log.info("start delete data blog");
        try{
            Optional<Users> optionalUsers =usersRepository.findByUsername(username);
            Optional<Blogs> optionalBlogs = blogRepository.findByIdAndCreatedBy(id,optionalUsers.get());
            var getData = optionalBlogs.get();
            blogRepository.delete(getData);
            return DeleteBlogResponse
                    .builder()
                    .isSuccess(true)
                    .build();
        }catch (Exception e){
            throw  e;
        }finally {
            log.info("Success delete blog");
        }
    }

    public static String convertToDateString(Date date){
        if(date == null){
            return null;
        }else{
            var formatDate = new SimpleDateFormat("dd MMM YYY HH:mm:ss");
            return formatDate.format(date);
        }
    }
}
