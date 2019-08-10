package com.oppalove.rsocket.server;

import org.reactivestreams.Publisher;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Controller
public class NewsFeedController {

    Map<Integer, NewsFeed> newsStore = new HashMap<>();

    @PostConstruct
    public void init() {
        NewsFeed nf1 = NewsFeed.builder()
                .id(1)
                .title("Big news!")
                .content("We can do it.")
                .build();

        NewsFeed nf2 = NewsFeed.builder()
                .id(2)
                .title("We are hiring")
                .content("Join our team!")
                .build();

        newsStore.put(1, nf1);
        newsStore.put(2, nf2);
    }

    @MessageMapping("News.Feed")
    public Publisher<NewsFeed> publishNewFeed(Integer id) {
        return Mono.justOrEmpty(newsStore.get(id));
    }
}
