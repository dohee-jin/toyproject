package com.spring.toyproject.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 범용적인 에러가 아닌 우리 앱에서만 발생하는 독특한 에러들을 저장하는 예외클래스
 * extends - Exception or RuntimeException 상속받아서 에러 타입으로 지정
 */
@Getter
@NoArgsConstructor
public class BusinessException extends RuntimeException{

    private ErrorCode errorCode;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage()); // 부모 runtimeexception한테 보내야 e.getMessage 사용이 가능
        this.errorCode = errorCode;
    }
}
