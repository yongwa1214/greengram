package kr.co.wikibook.greengram2.config.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.wikibook.greengram2.config.constants.ConstJwt;
import kr.co.wikibook.greengram2.config.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {
    private final ObjectMapper objectMapper; //Jackson 라이브러리 (JSON to Object, Object to JSON)
    private final ConstJwt constJwt;
    private final SecretKey secretKey;

    public JwtTokenProvider(ObjectMapper objectMapper, ConstJwt constJwt) {
        this.objectMapper = objectMapper;
        this.constJwt = constJwt;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(constJwt.getSecretKey()));
    }

    //JWT 토큰 생성
    public String generateToken(JwtUser jwtUser, long tokenValidityMilliSeconds) {
        Date now = new Date(); //Data객체를 기본생성자로 만들면 현재일시 정보로 객체화
        return Jwts.builder()
                //header
                .header().type(constJwt.getBearerFormat())
                .and()

                //payload
                .issuer(constJwt.getIssuer())
                .issuedAt(now) //발행일시(토큰 생성일시)
                .expiration(new Date(now.getTime() + tokenValidityMilliSeconds)) //만료일시(토큰 만료일시)
                .claim(constJwt.getClaimKey(), makeClaimByUserToJson(jwtUser))

                //signature
                .signWith(secretKey)
                .compact();
    }

    public String makeClaimByUserToJson(JwtUser jwtUser) {
        try {
            return objectMapper.writeValueAsString(jwtUser);
        } catch (JsonProcessingException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "토큰 생성 에러 발생");
        }
    }

    public JwtUser getJwtUserFromToken(String token) {
        try {
            Claims claims = getClaims(token);
            String json = claims.get(constJwt.getClaimKey(), String.class);
            return objectMapper.readValue(json, JwtUser.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "토큰 문제 발생");
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                   .verifyWith(secretKey)
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
    }
}
