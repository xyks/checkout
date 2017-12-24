package com.hixyks.checkout.web_app.entity;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author xyks@yahoo.com
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Link {
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="u_id")
    private User user;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="q_id")
    private Question question;
    
}
