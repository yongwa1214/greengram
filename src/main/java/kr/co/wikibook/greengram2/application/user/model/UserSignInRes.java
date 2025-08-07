package kr.co.wikibook.greengram2.application.user.model;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSignInRes {
    private long userId;
    private String nickName;
    private String pic;
}
