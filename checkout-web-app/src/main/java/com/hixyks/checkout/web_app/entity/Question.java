package com.hixyks.checkout.web_app.entity;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author xyks@yahoo.com
 *
 */
@Entity
@Table(name = "question")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String answer;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="create_by")
    private User createBy;
    @Column(name="create_date", columnDefinition="DATETIME")
    private LocalDateTime createDate;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="modify_by")
    private User modifyBy;
    @Column(name="modify_date", columnDefinition="DATETIME")
    private LocalDateTime modifyDate;
    
    public Optional<Category> getCategory() {
        return Optional.ofNullable(category);
    }
    
    public Optional<User> getModifyBy() {
        return Optional.ofNullable(modifyBy);
    }
    
}
