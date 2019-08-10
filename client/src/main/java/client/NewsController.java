package client;

import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class NewsController {

    private final RSocketRequester rSocketRequester;

    @RequestMapping("/newsfeed/{id}")
    public Publisher<NewsFeed> getNewsFeed(@PathVariable Integer id) {

        return rSocketRequester
                .route("News.Feed")
                .data(id)
                .retrieveMono(NewsFeed.class);
    }
}
