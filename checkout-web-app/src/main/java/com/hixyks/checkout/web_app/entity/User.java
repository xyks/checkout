package com.hixyks.checkout.web_app.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hixyks.checkout.web_app.enums.Role;

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
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String password;
	private boolean active;
	@Enumerated(EnumType.STRING)
	private Role role;
/*	
	@OneToMany(mappedBy="createBy")
	private List<Question> createQuestions;
	@OneToMany(mappedBy="modifyBy")
	private List<Question> modifyQuestions;*/
	
}
