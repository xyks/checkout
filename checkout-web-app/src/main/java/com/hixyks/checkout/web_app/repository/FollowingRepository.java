package com.hixyks.checkout.web_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hixyks.checkout.web_app.entity.Following;

public interface FollowingRepository extends JpaRepository<Following, Integer>{
        
    Optional<Following> findByLinkQuestionIdAndLinkUserId(Integer questionId, Integer userId);
    
    List<Following> findByLinkUserId(Integer userId);
    
    List<QuestionId> findByLinkQuestionIdIn(List<Integer> questionIds);
    
}
