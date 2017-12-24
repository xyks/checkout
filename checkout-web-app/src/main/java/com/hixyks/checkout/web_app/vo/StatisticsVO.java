package com.hixyks.checkout.web_app.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author xyks@yahoo.com
 *
 */
@Data
@Builder
public class StatisticsVO {
    private long all;
    private long checked;
    private long unchecked;
    private long clear;
    private long unclear;
    /**
     * clearPercentage and checkedPercentage are for UI using
     */
    @SuppressWarnings("unused")
    private double clearPercentage;
    @SuppressWarnings("unused")
    private double checkedPercentage;
    
    public double getClearPercentage() {
        return checked == 0 ? 0: 100.0* clear / checked;
    }
    
    public double getCheckedPercentage() {
        return all == 0 ? 0: 100.0* checked / all ;
        
    }
}
