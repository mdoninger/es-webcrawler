package de.mdoninger.webcrawler.data;

import lombok.Data;

/**
 * @author Manuel Doninger
 */
@Data
public class WebsiteResult {

    private String url;

    private String text;

    public String getText() {
        if (text.length() > 200) {
            return text.substring(0, 200);
        } else {
            return text;
        }
    }
}
