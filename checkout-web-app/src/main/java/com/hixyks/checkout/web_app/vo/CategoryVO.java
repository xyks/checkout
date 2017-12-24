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
public class CategoryVO {
    private Integer id;
    private String name;
    private Integer parentId;
    private String parentName;
    private int level;
    private int[] levelArray;
}
