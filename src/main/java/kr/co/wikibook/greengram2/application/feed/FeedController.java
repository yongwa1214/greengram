package kr.co.wikibook.greengram2.application.feed;


import jakarta.validation.Valid;
import kr.co.wikibook.greengram2.application.feed.model.FeedPostReq;
import kr.co.wikibook.greengram2.config.model.ResultResponse;
import kr.co.wikibook.greengram2.config.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;

    @PostMapping
    public ResultResponse<?> postFeed(@AuthenticationPrincipal UserPrincipal userPrincipal
                                     ,@Valid @RequestPart FeedPostReq req
                                     ,@RequestPart(name = "pic") List<MultipartFile> pics){
        log.info("signedUserId:{}", userPrincipal.getSignedUserId());
        log.info("req:{}", req);
        log.info("pics:{}", pics.size());

        feedService.postFeed(userPrincipal.getSignedUserId(),req,pics);
        return new ResultResponse<>("피드 등록 완료", null);
    }
}
