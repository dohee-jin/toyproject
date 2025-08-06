package com.spring.toyproject.api;

import com.spring.toyproject.domain.dto.common.ApiResponse;
import com.spring.toyproject.domain.dto.request.SignUpRequest;
import com.spring.toyproject.domain.dto.response.UserResponse;
import com.spring.toyproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
    API 응답은 언제나 일관적인게 중요함
    객체 응답, 배열 응답, 문자열형태 --> X
 */
@Slf4j
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    /**
     * 회원가입 API
     * POST: /api/auth/signup
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequest requestDto) {
        log.info("회원가입 요청: {}", requestDto.getUsername());

        UserResponse response = userService.signup(requestDto);
        return ResponseEntity
                .ok()
                .body(ApiResponse
                        .success("회원가입이 성공적으로 완료되었습니다."
                        ,response
                        )
                );
    }
}
