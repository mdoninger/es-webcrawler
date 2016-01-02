package de.mdoninger.webcrawler.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Manuel Doninger
 */
@Data
@Document(indexName = "websites", shards = 1)
public class Website {

    @Id
    private String url;

    @Field(type = FieldType.String)
    private String text;
}
