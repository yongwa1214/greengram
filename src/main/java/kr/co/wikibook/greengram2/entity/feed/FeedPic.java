package kr.co.wikibook.greengram2.entity.feed;

import jakarta.persistence.*;
import kr.co.wikibook.greengram2.entity.CreatedAt;
import kr.co.wikibook.greengram2.entity.UpdatedAt;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class FeedPic extends CreatedAt {
    @EmbeddedId
    private FeedPicIds feedPicIds;


    // 관계 설정
    @ManyToOne
    @MapsId("feedId")
    @JoinColumn(name = "feed_id")
    private Feed feed;
}
