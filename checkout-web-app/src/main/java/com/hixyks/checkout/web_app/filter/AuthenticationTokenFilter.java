package com.hixyks.checkout.web_app.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hixyks.checkout.web_app.auth.Account;
import com.hixyks.checkout.web_app.auth.AccountFactory;
import com.hixyks.checkout.web_app.auth.TokenUtil;

import io.jsonwebtoken.Claims;

@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
            Cookie[] cookies = Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]);
            Optional<Cookie> cookie = Arrays.stream(cookies).filter(x -> x.getName().equals("jwt")).findFirst();
            Optional<Cookie> helperCookie = Arrays.stream(cookies).filter(x -> x.getName().equals("helper")).findFirst();
            
            Optional<String> token = Optional.empty();
            if (helperCookie.isPresent() && !StringUtils.isEmpty(helperCookie.get().getValue()) &&cookie.isPresent() ) {
                token = Optional.ofNullable(cookie.get().getValue());
            } 
            
            token.ifPresent(x -> {
                Optional<Claims> clams = TokenUtil.getClams(x);
                clams.ifPresent(y -> {
                    Account account = AccountFactory.create(Integer.valueOf(y.getId()), y.get("role", String.class));
                    Authentication authentication = new UsernamePasswordAuthenticationToken(account, null,
                            account.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });

            });
       
        filterChain.doFilter(request, response);
    }
}
