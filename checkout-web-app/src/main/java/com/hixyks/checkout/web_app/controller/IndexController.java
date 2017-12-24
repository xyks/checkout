package com.hixyks.checkout.web_app.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hixyks.checkout.web_app.auth.TokenUtil;
import com.hixyks.checkout.web_app.service.UserService;
import com.hixyks.checkout.web_app.vo.UserVO;
/**
 * 
 * @author xyks@yahoo.com
 *
 */
@Controller
public class IndexController {
    
    @Autowired
    UserService userService;
    
    @RequestMapping("/")
    public String hello1() {
        return "index";
    }
    
    @ResponseBody
    @GetMapping("/test")
    public String hello() {
        return "App is running.";
    }
    
    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody UserVO vo, HttpServletResponse response) {
    
        UserVO user = userService.login(vo.getName(), vo.getPassword());
        String token = TokenUtil.createCompactJws(user);
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);
        
        Cookie helperCookie = new Cookie("helper", "helper");
        helperCookie.setHttpOnly(false);
        helperCookie.setMaxAge(60*60);
        response.addCookie(helperCookie);
        
        return user.isAdmin() ? "admin": "member";
        
        
        
    }
    
}
