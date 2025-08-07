package kr.co.wikibook.greengram2.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import kr.co.wikibook.greengram2.config.enumcode.model.EnumUserRole;
import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class UserRoleIds implements Serializable {
    private Long userId;

    @Column(length = 2)
    private EnumUserRole roleCode;
}
