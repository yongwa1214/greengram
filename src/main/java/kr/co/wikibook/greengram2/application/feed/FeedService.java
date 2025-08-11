package kr.co.wikibook.greengram2.application.feed;


import kr.co.wikibook.greengram2.application.feed.model.FeedPostReq;
import kr.co.wikibook.greengram2.config.util.ImgUploadManager;
import kr.co.wikibook.greengram2.entity.feed.Feed;
import kr.co.wikibook.greengram2.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;
    private final ImgUploadManager imgUploadManager;

    @Transactional
    public void postFeed(long signedUserId, FeedPostReq req, List<MultipartFile> pics){
        User writerUser = new User();
        writerUser.setUserId(signedUserId);

        Feed feed = Feed.builder()
                .writerUser(writerUser)
                .location(req.getLocation())
                .contents(req.getContents())
                .build();

        feedRepository.save(feed); // feed 객체는 영속성을 갖는다.

        List<String> fileNames = imgUploadManager.saveFeedPics(feed.getFeedId(),pics);

        feed.addFeedPics(fileNames);
    }
}
