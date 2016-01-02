package de.mdoninger.webcrawler.data;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Manuel Doninger
 */
public interface WebsiteRepository extends ElasticsearchRepository<Website, String> {
}
