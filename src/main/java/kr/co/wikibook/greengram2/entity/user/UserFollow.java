package kr.co.wikibook.greengram2.entity.user;

import jakarta.persistence.*;
import kr.co.wikibook.greengram2.entity.CreatedAt;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class UserFollow extends CreatedAt {
    @EmbeddedId // 복합키 설정
    private UserFollowIds userFollowIds;

    // 관계 설정
    @ManyToOne
    @MapsId("fromUserId")
    @JoinColumn(name = "from_user_id")
    private User fromUser;


    @ManyToOne
    @MapsId("toUserId")
    @JoinColumn(name = "to_user_id")
    private User toUser;

}
