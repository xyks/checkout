package com.hixyks.checkout.web_app.service;

import java.util.List;

import com.hixyks.checkout.web_app.vo.QuestionVO;

public interface FollowingService {

    List<QuestionVO> findAll(Integer userId);

    void delete(List<Integer> questionIds, Integer userId);

    void add(Integer questionId, Integer userId);
}
