package kr.co.wikibook.greengram2.entity.feed;


import jakarta.persistence.*;
import kr.co.wikibook.greengram2.entity.UpdatedAt;
import kr.co.wikibook.greengram2.entity.user.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class Feed extends UpdatedAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedId;

    @ManyToOne
    @JoinColumn(name = "wirter_user", nullable = false)
    private User writerUser;

    @Column(length = 30)
    private String location;

    @Column(length = 1_000)
    private String contents;

    @Builder.Default // builder 패턴 이용시 null이 되는데 이 애노테이션을 주면 주소값 생성됨
    @OneToMany(mappedBy ="feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedPic> feedPicList = new ArrayList<>(1);

    public void addFeedPics(List<String> picFileNames){
        for(String picFileName : picFileNames){
            FeedPicIds feedPicIds = FeedPicIds.builder()
                                              .feedId(this.feedId)
                                              .pic(picFileName)
                                              .build();

            FeedPic feedPic = FeedPic.builder()
                                     .feedPicIds(feedPicIds)
                                     .feed(this)
                                     .build();
            this.feedPicList.add(feedPic);
        }
    }


}
