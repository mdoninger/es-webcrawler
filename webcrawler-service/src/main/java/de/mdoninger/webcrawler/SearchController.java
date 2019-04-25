package de.mdoninger.webcrawler;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.mdoninger.webcrawler.data.WebsiteResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SearchController {

    private final Client client;

    private final ObjectMapper objectMapper;

    public SearchController(Client client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(method = RequestMethod.GET, path = "search")
    public Iterable<WebsiteResult> search(@RequestParam(name = "q") String q) throws IOException {
        log.info("Search with query " + q);

        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.queryStringQuery(q));

        searchRequest.source(searchSourceBuilder);

        ActionFuture<SearchResponse> search = client.search(searchRequest);
        SearchHit[] hits = search.actionGet().getHits().getHits();
        List<WebsiteResult> resultList = new ArrayList<>();
        for (SearchHit hit : hits) {
            WebsiteResult result = objectMapper.readValue(hit.getSourceAsString(), WebsiteResult.class);
            resultList.add(result);
        }

        return new PageImpl<>(resultList);
    }
}
