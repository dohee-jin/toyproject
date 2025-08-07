package com.spring.toyproject.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * jwt 토근 생성, 검증, 파싱 제공 유틸클래스
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProvider {

    // jwt 설정 값 읽기 클래스를 주입받음
    private final JwtProperties jwtProperties;

    /**
     * Jwt 토큰을 발급하는 메소드
     * @param username - 발급대상의 사용자 이름 (유일하게 사용자를 식별할 값 - 유니크한 값)
     * @return - Jwt 토근 문자열 (암호화됨)
     */
    public String generateToken(String username) {

        Date now = new Date();
        Date expirayDate = new Date(now.getTime() + jwtProperties.getExpiration());

        return Jwts.builder()
                .subject(username) // 이 토큰을 유일하게 식별할 키
                .issuedAt(now) // 언제 발급했는지
                .expiration(expirayDate) // 언제 만료되는지
                .issuer("Toy Project") // 발급자 정보
                .signWith(getSigningKey()) // 전자 서명
                .compact();
    }

    /**
     * Jwt 토근 발급에 필요한 서명 만들기
     * @return - 서명 키 객체
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }
}
