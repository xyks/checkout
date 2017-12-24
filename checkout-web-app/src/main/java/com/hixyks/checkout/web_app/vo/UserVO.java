package com.hixyks.checkout.web_app.vo;

import com.hixyks.checkout.web_app.enums.Role;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author xyks@yahoo.com
 *
 */
@Data
@Builder
public class UserVO {
    private Integer id;
    private String name;
    private String password;
    private boolean active;
    private String role; 
    
    public boolean isAdmin() {
        return Role.Admin.name().equalsIgnoreCase(role);
    }
}
