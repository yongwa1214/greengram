package kr.co.wikibook.greengram2.entity.user;


import jakarta.persistence.*;
import kr.co.wikibook.greengram2.config.enumcode.model.EnumUserRole;
import kr.co.wikibook.greengram2.entity.UpdatedAt;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
//@Table( // 이건 유니크 걸기 작업
//        uniqueConstraints = {
//                @UniqueConstraint(
//                        columnNames = {"uid"}
//                )
//        }
//)
public class User extends UpdatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 30)
    private String nickName;

    @Column(length = 50, nullable = false, unique = true)
    private String uid;

    @Column(length = 100)
    private String pic;

    @Column(length = 100, nullable = false)
    private String upw;


    // 양방향 관계 설정을 할 때 원래 안해도 되는 애한테 mappedBy를 적어준다(user 한테 이 작업을 안해주면 userRole과 별개임)
    // cascade = CascadeType.ALL, - 자식과 나랑 모든 연결 내가 영속성되면 자식도 영속성되고, 내가 삭제되면 자식도 삭제 된다. 등등
    // orphanRemoval은 userRoles 에서 자식을 하나 제거함, 그러면 DB 에도 뺀 자식은 삭제처리가 된다.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles = new ArrayList<>(1);


    public void addUserRoles(List<EnumUserRole> enumUserRole){
        for(EnumUserRole e : enumUserRole){
            UserRoleIds ids = new UserRoleIds(this.userId, e);
            UserRole userRole = new UserRole(ids, this);

            this.userRoles.add(userRole);
        }
    }

}
