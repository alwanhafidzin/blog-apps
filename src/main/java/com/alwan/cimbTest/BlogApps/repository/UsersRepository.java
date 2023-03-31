package com.alwan.cimbTest.BlogApps.repository;

import com.alwan.cimbTest.BlogApps.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByUsernameAndRoles(String username,String roles);
    Optional<Users> findByEmailAndRoles(String email, String roles);
    Optional<Users> findByEmail(String email);

}
