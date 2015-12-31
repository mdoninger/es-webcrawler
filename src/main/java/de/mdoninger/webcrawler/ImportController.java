package de.mdoninger.webcrawler;

import de.mdoninger.webcrawler.data.Website;
import de.mdoninger.webcrawler.data.WebsiteRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.FuzzyLikeThisQueryBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Manuel Doninger
 */
@RestController
@Slf4j
public class ImportController {

    private final WebsiteRepository websiteRepository;

    @Autowired
    public ImportController(WebsiteRepository websiteRepository) {
        this.websiteRepository = websiteRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "import")
    public void crawlUrl(@RequestParam(required = true, name = "url") String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        String text = document.text();

        Website website = new Website();
        website.setUrl(url);
        website.setText(text);
        websiteRepository.index(website);
    }

    @RequestMapping(method = RequestMethod.GET, path = "search")
    public Iterable<Website> search(@RequestParam(required = true, name = "q") String q) throws IOException {
        log.info("Search with query " + q);
        FuzzyLikeThisQueryBuilder fuzzyQuery = new FuzzyLikeThisQueryBuilder();
        fuzzyQuery.likeText(q);

        return websiteRepository.search(fuzzyQuery);
    }
}

