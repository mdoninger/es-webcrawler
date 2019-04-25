package de.mdoninger.webcrawler.data;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String text;

    @Field(type = FieldType.Object)
    List<URI> pageLinks = new ArrayList<>();
}
