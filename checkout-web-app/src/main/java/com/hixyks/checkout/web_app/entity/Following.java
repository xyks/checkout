package com.hixyks.checkout.web_app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="following")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Following {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Link link;
    @Column(name="modify_date",columnDefinition="DATETIME")
	private LocalDateTime modifyDate;
		
}
