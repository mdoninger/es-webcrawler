package de.mdoninger.webcrawler;

import de.mdoninger.webcrawler.data.Website;
import de.mdoninger.webcrawler.data.WebsiteRepository;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Manuel Doninger
 */
@RestController
@Slf4j
public class ImportController {

    private final WebsiteRepository websiteRepository;

    public ImportController(WebsiteRepository websiteRepository) {
        this.websiteRepository = websiteRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "import")
    public void crawlUrl(@RequestParam(name = "url") String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        String text = document.text();

        Elements titleElement = document.getElementsByClass("post-title");

        Website website = new Website();
        website.setUrl(url);
        website.setText(text);
        website.setTitle(titleElement.text());
        websiteRepository.index(website);
        log.debug("Finished analyzing of url {}", url);
    }


}

