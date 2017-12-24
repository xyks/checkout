package com.hixyks.checkout.web_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hixyks.checkout.web_app.entity.Category;
/**
 * 
 * @author xyks@yahoo.com
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
    
    Optional<Category> findByNameIgnoreCase(String name);
    
    List<Category> findByParentIdIsNull();
    
}
