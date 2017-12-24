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
import com.hixyks.checkout.web_app.service.FollowingService;
import com.hixyks.checkout.web_app.service.QuestionService;
import com.hixyks.checkout.web_app.service.TrainingService;
import com.hixyks.checkout.web_app.vo.QuestionVO;
import com.hixyks.checkout.web_app.vo.StatisticsVO;

/**
 * 
 * @author xyks@yahoo.com
 *
 */
@CrossOrigin
@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @Autowired
    TrainingService trainingService;
    
    @Autowired
    FollowingService followingService;

    @PreAuthorize("hasRole('ROLE_Admin')")
    @PostMapping
    public void add(@RequestBody QuestionVO vo, @CurrentLoginUser Account account) {
        vo.setCreateById(account.getId());
        questionService.add(vo);
    }

    @PreAuthorize("hasRole('ROLE_Admin')")
    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        questionService.deleteById(id);
    }

    //@PreAuthorize("hasRole('ROLE_Admin')")
    @PreAuthorize("hasRole('ROLE_Member')")
    @PutMapping
    public void update(@RequestBody QuestionVO vo, @CurrentLoginUser Account account) {
        vo.setModifyById(account.getId());
        questionService.update(vo);
    }

    @PreAuthorize("hasRole('ROLE_Member')")
    @GetMapping("{id}")
    public QuestionVO findById(@PathVariable Integer id) {
        return questionService.findById(id).orElseThrow(RuntimeException::new);
    }

    @PreAuthorize("hasRole('ROLE_Member')")
    @GetMapping("category/{id}/training")
    public List<QuestionVO> findPendingForTrainingQuestionsByCategory(@PathVariable Integer id,
            @CurrentLoginUser Account account) {
        return questionService.findPendingForTrainingQuestionsByCategory(id, account.getId());
    }
    
    /**
     * details
     */
    @PreAuthorize("hasRole('ROLE_Member')")
    @GetMapping("details/{id}")
    public QuestionVO findDetailsById(@PathVariable Integer id, @CurrentLoginUser Account account) {
        return questionService.getDetails(id, account.getId()).orElseThrow(RuntimeException::new);
    }
    
    /**
     * Search
     */
    @PreAuthorize("hasRole('ROLE_Member')")
    @GetMapping("search/{searchBy}/{searchValue}")
    public List<QuestionVO> search(@PathVariable String searchBy, @PathVariable String searchValue) {
        return questionService.search(searchBy, searchValue);
    }
    
    
    
    /**
     * Training
     */
    @PreAuthorize("hasRole('ROLE_Member')")
    @PutMapping("training")
    public void submitTrainingResult(@RequestBody List<QuestionVO> result, @CurrentLoginUser Account account) {
        trainingService.update(result, account.getId());
    }
    
    @PreAuthorize("hasRole('ROLE_Member')")
    @DeleteMapping("training/all")
    public void deleteTrainingHistory(@CurrentLoginUser Account account) {
        trainingService.deleteAll(account.getId());
    }
    

    @PreAuthorize("hasRole('ROLE_Member')")
    @GetMapping("training/all")
    public List<QuestionVO> findTrainingHistory(@CurrentLoginUser Account account) {
        return trainingService.findAll(account.getId());
    }
    
    @PreAuthorize("hasRole('ROLE_Member')")
    @GetMapping("training/statistics")
    public StatisticsVO getTrainingStatistics(@CurrentLoginUser Account account) {
        return trainingService.getStatistics(account.getId());
    }
    

    /*****
     * Following
     */

    @PreAuthorize("hasRole('ROLE_Member')")
    @PostMapping("following/{questionId}")
    public void addToFavoriteList(@CurrentLoginUser Account account,@PathVariable Integer questionId) {
        followingService.add(questionId, account.getId());
    }

    @PreAuthorize("hasRole('ROLE_Member')")
    @DeleteMapping("following")
    public void removeFromFavoriList(@CurrentLoginUser Account account, @RequestBody List<Integer> questionIds) {
        followingService.delete(questionIds, account.getId());
    }

    @PreAuthorize("hasRole('ROLE_Member')")
    @GetMapping("following/all")
    public List<QuestionVO> getFavoriteList(@CurrentLoginUser Account account) {
        return followingService.findAll(account.getId());
    }

}
