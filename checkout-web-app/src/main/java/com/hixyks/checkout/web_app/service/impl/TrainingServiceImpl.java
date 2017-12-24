package com.hixyks.checkout.web_app.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hixyks.checkout.web_app.entity.Category;
import com.hixyks.checkout.web_app.entity.Link;
import com.hixyks.checkout.web_app.entity.Question;
import com.hixyks.checkout.web_app.entity.Training;
import com.hixyks.checkout.web_app.entity.User;
import com.hixyks.checkout.web_app.repository.QuestionRepository;
import com.hixyks.checkout.web_app.repository.TrainingRepository;
import com.hixyks.checkout.web_app.service.TrainingService;
import com.hixyks.checkout.web_app.vo.QuestionVO;
import com.hixyks.checkout.web_app.vo.StatisticsVO;
/**
 * 
 * @author xyks@yahoo.com
 *
 */
@Service
@Transactional
public class TrainingServiceImpl implements TrainingService{

    @Autowired
    TrainingRepository trainingRepository;
    
    @Autowired
    QuestionRepository questionRepository;
    
    @Override
    public List<QuestionVO> findAll(Integer userId) {
        List<Training> all = trainingRepository.findByLinkUserId(userId);
        return all.stream().map(x->{
            Question question = x.getLink().getQuestion();
            return QuestionVO.builder().id(question.getId()).title(question.getTitle()).categoryName(question.getCategory().orElseGet(Category::new).getName()).pass(x.isPass()).build();
        }).collect(Collectors.toList());
    }

    @Override
    public void update(List<QuestionVO> result, Integer userId) {
        result.stream().forEach(x -> {
            Training old = trainingRepository.findByLinkQuestionIdAndLinkUserId(x.getId(), userId).orElseGet(() -> {
                Link link = Link.builder().question(Question.builder().id(x.getId()).build())
                        .user(User.builder().id(userId).build()).build();
                return Training.builder().link(link).build();
            });
            old.setModifyDate(LocalDateTime.now());
            old.setPass(x.isPass());
            trainingRepository.save(old);
        });
        
    }

    @Override
    public StatisticsVO getStatistics(Integer userId) {
        long all = questionRepository.count();
        long checked = trainingRepository.countByLinkUserId(userId);
        long clear = trainingRepository.countByLinkUserIdAndPass(userId, true);
        
        long unchecked = all - checked;
        long unclear = checked - clear;
        
        return StatisticsVO.builder().all(all).checked(checked).unchecked(unchecked).clear(clear).unclear(unclear).build();
    }

    @Override
    public void deleteAll(Integer userId) {
        trainingRepository.deleteByLinkUserId(userId);
        
    }

}
