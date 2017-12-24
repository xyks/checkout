package com.hixyks.checkout.web_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hixyks.checkout.web_app.entity.Training;

public interface TrainingRepository extends JpaRepository<Training, Integer>{
        
    Optional<Training> findByLinkQuestionIdAndLinkUserId(Integer questionId, Integer userId);
    
    List<Training> findByLinkUserId(Integer userId);
    
    long countByLinkUserId(Integer userId);
    long countByLinkUserIdAndPass(Integer userId, boolean pass);
    
    void deleteByLinkUserId(Integer userId);
}
