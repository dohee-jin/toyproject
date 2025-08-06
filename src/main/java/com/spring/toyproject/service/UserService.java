package com.spring.toyproject.service;

import com.spring.toyproject.domain.dto.request.SignUpRequest;
import com.spring.toyproject.domain.dto.response.UserResponse;
import com.spring.toyproject.domain.entity.User;
import com.spring.toyproject.exception.BusinessException;
import com.spring.toyproject.exception.ErrorCode;
import com.spring.toyproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

/**
 * 사용자 모듈 서비스 클래스
 * 인증, 회원관련 비즈니스 로직 처리
 * 트랜잭션 처리
 */
@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    // 비밀번호 암호화를 위한 객체
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 가입 로직
     * 엔티티를 클라이언트로부터 받아오는 건 위험함
     */
    public UserResponse signup(SignUpRequest requestDto) {

        // 사용자명 중복 체크
        if(userRepository.existsByUsername(requestDto.getUsername())) {
            throw new BusinessException(ErrorCode.DUPLICATE_USERNAME);
        }
        // 이메일 중복 체크
        if(userRepository.existsByEmail(requestDto.getEmail())) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }

        // 패스워드를 해시로 암호화
        String encodePassword = passwordEncoder.encode(requestDto.getPassword());

        // dto를 entity 로 변경
        User user = User.builder()
                .email(requestDto.getEmail())
                .username(requestDto.getUsername())
                .password(encodePassword)
                .build();

        // db insert 명령
        User saved = userRepository.save(user);
        log.info("새로운 사용자 가입: {}", user);

        return UserResponse.from(saved);
    }
}
