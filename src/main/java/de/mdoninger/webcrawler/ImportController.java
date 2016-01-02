package de.mdoninger.webcrawler;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.mdoninger.webcrawler.data.Website;
import de.mdoninger.webcrawler.data.WebsiteRepository;
import de.mdoninger.webcrawler.data.WebsiteResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.FuzzyLikeThisQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Manuel Doninger
 */
@RestController
@Slf4j
public class ImportController {

    private final WebsiteRepository websiteRepository;

    private final Client client;

    private final ObjectMapper objectMapper;

    @Autowired
    public ImportController(WebsiteRepository websiteRepository, Client client, ObjectMapper objectMapper) {
        this.websiteRepository = websiteRepository;
        this.client = client;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(method = RequestMethod.GET, path = "import")
    public void crawlUrl(@RequestParam(name = "url") String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        String text = document.text();

        Website website = new Website();
        website.setUrl(url);
        website.setText(text);
        websiteRepository.index(website);
    }

    @RequestMapping(method = RequestMethod.GET, path = "search")
    public Iterable<WebsiteResult> search(@RequestParam(name = "q") String q) throws IOException {
        log.info("Search with query " + q);
        FuzzyLikeThisQueryBuilder fuzzyQuery = new FuzzyLikeThisQueryBuilder();
        fuzzyQuery.likeText(q);

        SearchRequest searchRequest = new SearchRequestBuilder(client).setIndices("websites").setQuery(fuzzyQuery).request();

        ActionFuture<SearchResponse> search = client.search(searchRequest);
        SearchHit[] hits = search.actionGet().getHits().getHits();
        List<WebsiteResult> resultList = new ArrayList<>();
        for (SearchHit hit : hits) {
            WebsiteResult result = objectMapper.readValue(hit.sourceAsString(), WebsiteResult.class);
            resultList.add(result);
        }

        return new PageImpl<>(resultList);
    }
}

