package com.spring.toyproject.routes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 페이지 전환 랜더링용 컨트롤러
 * Thymeleaf 나 JSP 같은 뷰템플릿 페이지를 랜더링
 */
@Controller
@Slf4j
public class PageController {

    // 홈으로 이동
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // 로그인으로 이동
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 회원가입으로 이동
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    // 대시보드로 이동
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    // 여행목록으로 이동
    @GetMapping("/trips")
    public String trips() {
        return "trip-list";
    }

    // 여행목록으로 이동
    @GetMapping("/trips/new")
    public String tripsFrom() {
        return "trip-form";
    }

    // 여행일지 등록으로 이동
    @GetMapping("/travel-logs/new")
    public String travelFrom() {
        return "travel-log-form";
    }
}
