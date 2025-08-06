package kr.co.wikibook.greengram2.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable //포함될 수 있는
// @AllArgsConstructor
// @NoArgsConstructor
public class UserFollowIds implements Serializable {// 복합키가 pk면 반드시 serializable을 주도록
    private Long fromUserId;
    private Long toUserId;

}
