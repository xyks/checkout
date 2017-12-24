package com.hixyks.checkout.web_app.service;

import java.util.List;

import com.hixyks.checkout.web_app.vo.UserVO;
/**
 * 
 * @author xyks@yahoo.com
 *
 */
public interface UserService {
    List<UserVO> findAll();

    void add(UserVO vo);

    void deleteById(Integer id);

    void update(UserVO vo);

    void updateUserName(Integer id, String name);

    UserVO login(String username, String password);

    void lock(Integer id, boolean lock);
    
    
}
