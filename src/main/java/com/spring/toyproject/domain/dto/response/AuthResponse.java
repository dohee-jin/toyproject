package com.spring.toyproject.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 인증 완료 후 클라이언트에게 전송할 내용
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String token; // jwt 토큰

    @Builder.Default // 객체 생성할 때 tokenType은 신경쓰지않고 디폴트 값으로 생성해줌
    private String tokenType = "Bearer"; // 타입이 여러개지만 요즘은 보통 Bearer로 고정
    private UserResponse user; // 로그인한 유저의 정보

    // 정적 팩토리 메소드
    public static AuthResponse of(String token, UserResponse user) {
        return AuthResponse.builder()
                .token(token)
                .user(user)
                .build();
    }
}
