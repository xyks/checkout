package com.hixyks.checkout.web_app.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	
	@Column(name="parent_id")
	private Integer parentId;
	
	@OneToMany(mappedBy="parentId")
	private Set<Category> children;
	
/*	@OneToMany(mappedBy="category", targetEntity=Question.class)
	private Set<Question> questions;*/
	
}
