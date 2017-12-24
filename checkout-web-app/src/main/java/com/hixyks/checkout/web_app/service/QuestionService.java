package com.hixyks.checkout.web_app.service;

import java.util.List;
import java.util.Optional;

import com.hixyks.checkout.web_app.vo.QuestionVO;

public interface QuestionService {
    Optional<QuestionVO> findById(Integer id);

    void add(QuestionVO vo);

    void deleteById(Integer id);

    void update(QuestionVO vo);

    List<QuestionVO> findPendingForTrainingQuestionsByCategory(Integer categoryId, Integer userId);

    List<QuestionVO> search(String searchBy, String searchValue);

    Optional<QuestionVO> getDetails(Integer id, Integer userId);


}
