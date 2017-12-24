package com.hixyks.checkout.web_app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hixyks.checkout.web_app.entity.User;
import com.hixyks.checkout.web_app.enums.Role;
import com.hixyks.checkout.web_app.repository.UserRepository;
import com.hixyks.checkout.web_app.service.UserService;
import com.hixyks.checkout.web_app.vo.UserVO;

/**
 * 
 * @author xyks@yahoo.com
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserVO> findAll() {
        return userRepository.findAll().stream()
                .map(user -> UserVO.builder().id(user.getId()).name(user.getName()).role(user.getRole().name()).active(user.isActive()).build())
                .collect(Collectors.toList());
    }

    @Override
    public void add(UserVO vo) {
        Optional<User> old = userRepository.findByNameIgnoreCase(vo.getName());
        if (old.isPresent()) {
            throw new RuntimeException("User alreay exists.");
        }else {
            User user = User.builder().name(vo.getName()).password(vo.getPassword()).active(true).role(Role.valueOf(vo.getRole())).build();
            userRepository.save(user);
        }
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public void update(UserVO vo) {
        /*Optional<User> old = Optional.ofNullable(userRepository.findOne(vo.getId()));
        if (old.isPresent()) {
            old.get().setName(vo.getName());
            old.get().setPassword(vo);
        }else {
            throw new RuntimeException(String.format("No user found with the id '%s'", vo.getId()));
        }*/
        
    }

    @Override
    public void updateUserName(Integer id, String name) {
        User old = Optional.ofNullable(userRepository.findOne(id)).orElseThrow(RuntimeException::new);
        Optional<User> other = userRepository.findByNameIgnoreCase(name);
        if (other.isPresent()) {
            throw new RuntimeException(String.format("User name has been used: '%s'", name));
        }else {
            old.setName(name);
            userRepository.save(old);
        }
        
    }

    @Override
    public UserVO login(String username, String password) {
        User user = userRepository.findByNameIgnoreCase(username).orElseThrow(RuntimeException::new);
        if (password.equals(user.getPassword())) {
            return UserVO.builder().id(user.getId()).role(user.getRole().name()).build();
        }else {
            throw new RuntimeException("Failed to login.");
        }
    }

    @Override
    public void lock(Integer id, boolean lock) {
        User user = Optional.ofNullable(userRepository.findOne(id)).orElseThrow(RuntimeException::new);
        user.setActive(lock);
        userRepository.save(user);
        
    }

}
