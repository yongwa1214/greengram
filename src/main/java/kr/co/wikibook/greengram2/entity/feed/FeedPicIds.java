package kr.co.wikibook.greengram2.entity.feed;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
@EqualsAndHashCode
public class FeedPicIds implements Serializable {
    private Long feedId;

    @Column(length = 50)
    private String pic;
}
