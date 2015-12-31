package de.mdoninger.webcrawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Manuel Doninger
 */
@Component
public class InitialSearchIndexer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitialSearchIndexer.class);

    private final ImportController importController;

    @Autowired
    public InitialSearchIndexer(ImportController importController) {
        this.importController = importController;
    }

    @Override
    public void run(String... args) throws Exception {
        importController.crawlUrl("http://www.mdoninger.de/2015/05/16/completion-for-custom-properties-in-spring-boot.html");
        LOGGER.info("Indexed url {}", "http://www.mdoninger.de/2015/05/16/completion-for-custom-properties-in-spring-boot.html");
    }
}
