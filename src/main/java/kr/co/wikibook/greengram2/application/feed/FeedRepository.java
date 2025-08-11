package kr.co.wikibook.greengram2.application.feed;

import kr.co.wikibook.greengram2.entity.feed.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
