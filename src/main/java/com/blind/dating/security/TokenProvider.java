package com.blind.dating.security;

import com.blind.dating.domain.UserAccount;
import com.blind.dating.dto.user.UserRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenProvider {

    private static final String SECRET_KEY = "c2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQtc2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQK";
    private Key key;

    public String create(UserAccount userAccount){

        Date now = new Date();
        // 유효시간 1시간으로 처리
        Date expiredAt = Date.from(LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant());
        key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setSubject(userAccount.getUserId())
                .setIssuer("blind dating web")
                .setIssuedAt(now)
                .setExpiration(expiredAt)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String refreshToken(UserAccount userAccount){

        Date now = new Date();
        Date expiredAt = Date.from(LocalDateTime.now().plusDays(7).atZone(ZoneId.systemDefault()).toInstant());
        key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setSubject(String.valueOf(userAccount.getId()))
                .setIssuer("blind dating web")
                .setIssuedAt(now)
                .setExpiration(expiredAt)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateAndGetUserId(String token) {
        key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        Jwt jwt = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parse(token);


        Claims claims = (Claims)jwt.getBody();

        return claims.get("sub", String.class);
    }

    public Boolean validateToken(String token){
        try {
            key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            // JWT 유효성 검사 중 에러가 발생하면 false 반환
            return false;
        }


    }



}
