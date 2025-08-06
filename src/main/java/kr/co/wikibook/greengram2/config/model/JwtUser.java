package kr.co.wikibook.greengram2.config.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class JwtUser {
    private long signedUserId;
    private List<String> roles; //인가 처리 때 사용
    /*
    role 이름은 ROLE_아무거나

    ROLE_USER, ROLE_ADMIN, ROLE_MANAGER, ROLE_LEVEL_1 ...
     */
}
