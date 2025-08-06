package kr.co.wikibook.greengram2.entity.user;


import jakarta.persistence.*;
import kr.co.wikibook.greengram2.entity.UpdatedAt;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table( // 이건 유니크 걸기 작업
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"uid"}
                )
        }
)
public class User extends UpdatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 30)
    private String nickName;

    @Column(length = 50, nullable = false)
    private String uid;

    @Column(length = 100)
    private String pic;

    @Column(length = 100, nullable = false)
    private String upw;

}
