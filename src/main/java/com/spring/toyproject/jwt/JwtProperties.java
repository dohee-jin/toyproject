package com.spring.toyproject.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * jwt 설정 읽기 파일 - application.yml의 jwt.xxx 값을 읽어오는 클래스
 */
@Setter @Getter
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    // jwt.secret 을 읽어옴
    private String secret;

    // jwt.expiration 을 읽어옴
    private long expiration;
}
