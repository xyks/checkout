package com.hixyks.checkout.web_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hixyks.checkout.web_app.service.CategoryService;
import com.hixyks.checkout.web_app.vo.CategoryVO;

/**
 * 
 * @author xyks@yahoo.com
 *
 */
@CrossOrigin
@RestController
@RequestMapping("category")
public class CategoryController {
    
    @Autowired
    CategoryService categoryService;
    
    @PreAuthorize("hasRole('ROLE_Admin')")
    @PostMapping
    public void add(@RequestBody CategoryVO vo) {
        categoryService.add(vo);
    }
    
    //TODO delete
    
/*    @PreAuthorize("hasRole('ROLE_Admin')")
    @PostMapping
    public void changeName(CategoryVO vo) {
        categoryService.add(vo);
    }*/
    
    
    
    @PreAuthorize("hasRole('ROLE_Member')")
    @GetMapping
    public List<CategoryVO> findAllWithTreeLevel() {
         return categoryService.findAllWithTreeLevel();
    }
    

}
