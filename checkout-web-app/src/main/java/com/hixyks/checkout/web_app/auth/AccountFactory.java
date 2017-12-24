package com.hixyks.checkout.web_app.auth;
/**
 * 
 * @author xyks@yahoo.com
 *
 */

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.hixyks.checkout.web_app.enums.Role;

public final class AccountFactory {
    
    private final static GrantedAuthority admin;
    private final static GrantedAuthority member;
    static{
        admin = new SimpleGrantedAuthority(Role.Admin.details()); 
        member = new SimpleGrantedAuthority(Role.Member.details());
    }

    private AccountFactory() {
        throw new UnsupportedOperationException();
    }
    
    public static Account create(Integer id, String role) {
        
        return Account.builder().id(id).authorities(convertToGrantedAuthorities(Role.valueOf(role))).build();
    }
    
    private static List<GrantedAuthority> convertToGrantedAuthorities(Role role) {
        List<GrantedAuthority> result = new ArrayList<>();
        if (role.isAdmin()) {
            result.add(admin);
            result.add(member);
        }else if (role.isMember()) {
            result.add(member);
        }
        return result;
        
    }
}
