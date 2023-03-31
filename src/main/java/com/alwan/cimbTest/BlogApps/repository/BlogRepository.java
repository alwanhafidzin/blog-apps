package com.alwan.cimbTest.BlogApps.repository;

import com.alwan.cimbTest.BlogApps.model.Blogs;
import com.alwan.cimbTest.BlogApps.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blogs, Integer> {

    Optional<Blogs> findByIdAndCreatedBy(Long id, Users createdBy);
    Page<Blogs> findAllByCreatedByOrderByIdDesc(Users createdBy, Pageable pageable);
}
