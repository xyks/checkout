package com.hixyks.checkout.web_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hixyks.checkout.web_app.annotation.CurrentLoginUser;
import com.hixyks.checkout.web_app.auth.Account;
import com.hixyks.checkout.web_app.service.UserService;
import com.hixyks.checkout.web_app.vo.UserVO;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @PreAuthorize("hasRole('ROLE_Admin')")
    @PostMapping
    public void add(@RequestBody UserVO vo) {
        userService.add(vo);
    }
    @PreAuthorize("hasRole('ROLE_Admin')")
    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteById(id);
    }
    
    @PreAuthorize("hasRole('ROLE_Member')")
    @PutMapping("name/{name}")
    public void changeUserName(@CurrentLoginUser Account account, @PathVariable String name) {
        userService.updateUserName(account.getId(), name);
    }
    
    @PreAuthorize("hasRole('ROLE_Admin')")
    @GetMapping("all")
    public List<UserVO> findAll(){
        return userService.findAll();
    }
    
    @PreAuthorize("hasRole('ROLE_Admin')")
    @GetMapping("{id}/lock/{lock}")
    public void lock(@PathVariable Integer id, @PathVariable boolean lock){
         userService.lock(id,lock);
    }
    
    
    
    
    
    
}
