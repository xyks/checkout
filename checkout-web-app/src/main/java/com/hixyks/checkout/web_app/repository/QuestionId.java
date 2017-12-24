package com.hixyks.checkout.web_app.repository;

import org.springframework.beans.factory.annotation.Value;

/**
 * 
 * @author xyks@yahoo.com
 *
 */
public interface QuestionId {
    @Value("#{target.link.question.id}")
    Integer getId();
}
