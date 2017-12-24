package com.hixyks.checkout.web_app.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hixyks.checkout.web_app.helper.CustomDateSerializer;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author xyks@yahoo.com
 *
 */
@Data
@Builder
public class QuestionVO {
    private Integer id;
    private String title;
    private String answer;
    private Integer categoryId;
    private String categoryName;
    private boolean pass;
    @JsonSerialize (using = CustomDateSerializer.class)
    private LocalDateTime createDate;
    private Integer createById;
    private String createByName;
    @JsonSerialize (using = CustomDateSerializer.class)
    private LocalDateTime modifyDate;
    private Integer modifyById;
    private String modifyByName;
    
    private boolean following;
    
    private Long followingNumber;
    
    private Long avgRatingScore;
    
    private Long mineRatingScore;
    
    
}
