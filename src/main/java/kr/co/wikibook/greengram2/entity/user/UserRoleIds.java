package kr.co.wikibook.greengram2.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import kr.co.wikibook.greengram2.config.enumcode.model.EnumUserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class UserRoleIds implements Serializable {
    private Long userId;

    @Column(length = 2)
    private EnumUserRole roleCode;
}
