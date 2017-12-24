package com.hixyks.checkout.web_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hixyks.checkout.web_app.entity.User;

/**
 * 
 * @author xyks@yahoo.com
 *
 */
public interface UserRepository extends JpaRepository<User,Integer>{
    Optional<User> findByNameIgnoreCase(String name);
}
