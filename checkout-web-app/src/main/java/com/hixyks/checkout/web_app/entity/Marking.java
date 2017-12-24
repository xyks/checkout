package com.hixyks.checkout.web_app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="marking")
public class Marking {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Link link;
	@Column(columnDefinition="TINYINT")
	private int score;
	private String comment;
	@Column(name="create_date",columnDefinition="DATETIME")
	private LocalDateTime createDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
    public Link getLink() {
        return link;
    }
    public void setLink(Link link) {
        this.link = link;
    }
	
	
}
