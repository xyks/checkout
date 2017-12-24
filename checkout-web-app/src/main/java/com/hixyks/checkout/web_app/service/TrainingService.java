package com.hixyks.checkout.web_app.service;

import java.util.List;

import com.hixyks.checkout.web_app.vo.QuestionVO;
import com.hixyks.checkout.web_app.vo.StatisticsVO;

public interface TrainingService {

    List<QuestionVO> findAll(Integer userId);

    void update(List<QuestionVO> result, Integer userId);

    StatisticsVO getStatistics(Integer userId);

    void deleteAll(Integer userId);
}
