package com.spring.toyproject.repository;

import com.spring.toyproject.domain.entity.User;
import com.spring.toyproject.repository.base.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // DB 테스트를 위한 간소설정 (속도가 @SpringBootTest에 비해 월등히 빠름)
@ActiveProfiles("test") // application-test.yml을 읽어라
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .username("testUser")
                .email("testUser@example.com")
                .password("test1234!")
                .build();

        userRepository.save(user);
    }

    @Test
    @DisplayName("사용자 명으로 조회테스트")
    void findByUsernameTest() {
        // given
        String name = "testUser";
        // when
        User foundUser = userRepository.findByUsername(name).orElseThrow();
        // then
        // assertNotNull(foundUser); // Junit5
        // assertThat - Junit4
        assertEquals("testUse111r@example.com", foundUser.getEmail());

    }

    @Test
    @DisplayName("사용자명 중복확인 테스트")
    void existUsernameTest() {
        // given
        String username = "testUser";
        // when
        boolean flag = userRepository.existsByUsername(username);
        // then
        assertTrue(flag);
    }
}