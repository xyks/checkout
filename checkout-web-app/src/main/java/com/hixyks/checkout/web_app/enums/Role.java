package com.hixyks.checkout.web_app.enums;
/**
 * 
 * @author xyks@yahoo.com
 *
 */
public enum Role {
	Admin("ROLE_Admin"), Member("ROLE_Member");
	
    private String details;
    
    private Role(String details) {
        this.details = details;
    }
    
	public boolean isAdmin() {
	    return Admin.equals(this);
	}
	
	public boolean isMember() {
        return Member.equals(this);
    }
	
	public String details() {
	    return details;
	}
}
