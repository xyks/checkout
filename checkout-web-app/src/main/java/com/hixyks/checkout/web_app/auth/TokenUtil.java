package com.hixyks.checkout.web_app.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hixyks.checkout.web_app.vo.UserVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class TokenUtil {

    private static String key;
    
    @Value("${token.key}")
    public void setKey(String key) {
        TokenUtil.key = key;
    }

    public static String createCompactJws(UserVO user) {
        return Jwts.builder().setId(String.valueOf(user.getId())).claim("role", user.getRole()).signWith(SignatureAlgorithm.HS512, key).compact();
    }

    public static Optional<Claims> getClams(String compactJws) {
        try {
            Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws);
            return Optional.of(parseClaimsJws.getBody());
        } catch (SignatureException e) {
            return Optional.empty();
        }
    }

}
