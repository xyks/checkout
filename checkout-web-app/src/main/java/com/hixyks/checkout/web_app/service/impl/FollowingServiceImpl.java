package com.hixyks.checkout.web_app.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hixyks.checkout.web_app.entity.Category;
import com.hixyks.checkout.web_app.entity.Following;
import com.hixyks.checkout.web_app.entity.Link;
import com.hixyks.checkout.web_app.entity.Question;
import com.hixyks.checkout.web_app.entity.User;
import com.hixyks.checkout.web_app.repository.FollowingRepository;
import com.hixyks.checkout.web_app.service.FollowingService;
import com.hixyks.checkout.web_app.vo.QuestionVO;
/**
 * 
 * @author xyks@yahoo.com
 *
 */
@Service
public class FollowingServiceImpl implements FollowingService{

    @Autowired
    FollowingRepository followingRepository;
    
    @Override
    public List<QuestionVO> findAll(Integer userId) {
        List<Following> all = followingRepository.findByLinkUserId(userId);
        return all.stream().map(x->{
            Question question = x.getLink().getQuestion();
            return QuestionVO.builder().id(question.getId()).title(question.getTitle()).categoryName(question.getCategory().orElseGet(Category::new).getName()).build();
            }).collect(Collectors.toList());
    }

    @Override
    public void delete(List<Integer> questionIds, Integer userId) {
        questionIds.stream().forEach(x->{
            followingRepository.findByLinkQuestionIdAndLinkUserId(x, userId).ifPresent(y->{
                followingRepository.delete(y);
            });
        });
    }

    @Override
    public void add(Integer questionId, Integer userId) {
        Optional<Following> old = followingRepository.findByLinkQuestionIdAndLinkUserId(questionId, userId);
        if (old.isPresent()) {
            throw new RuntimeException("Already exists in favorite list.");
        }else {
            Link link = Link.builder().question(Question.builder().id(questionId).build()).user(User.builder().id(userId).build()).build();
            Following one = Following.builder().link(link).modifyDate(LocalDateTime.now()).build();
            followingRepository.save(one);
        }
    }

}
