package kr.co.wikibook.greengram2.config.exception;

import lombok.*;
import org.springframework.validation.FieldError;

//또는 builder를 쓰지 않고 allargsconstructor로 처리 해도 됨
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationError {
    private String field;
    private String message;


    public static ValidationError of(final FieldError fieldError) { // 외부에서 객체 생성하게 해주는 메소드
        return ValidationError.builder()
                .field(fieldError.getField())
                .message(fieldError.getDefaultMessage())
                .build();
    }

    @Override
    public String toString() {
        return String.format("field: %s, message: %s", field, message);
    }
}
