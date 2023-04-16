package org.fresno.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.net.URL;
import java.time.Instant;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "news")
public class News {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private URL url;
    private URL imageUrl;
    private String domain;
    private String country;
    private String language;
    private Instant pubDate;
    private String title;
    private String description;
    private String content;
    private String summary;
    private String keywords;
    private boolean isPublished;

    public News(Long id, URL url, URL imageUrl, String domain, String country, String language, String title, String description, String content, String summary, String keywords) {
        this.id = id;
        this.url = url;
        this.imageUrl = imageUrl;
        this.domain = domain;
        this.country = country;
        this.language = language;
        this.pubDate = Instant.now();
        this.title = title;
        this.description = description;
        this.content = content;
        this.summary = summary;
        this.keywords = keywords;
        this.isPublished = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(title.toLowerCase().trim(), news.title.toLowerCase().trim()) && Objects.equals(description.toLowerCase().trim(), news.description.toLowerCase().trim()) && Objects.equals(summary.toLowerCase().trim(), news.summary.toLowerCase().trim());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, domain, country, language, pubDate, title, description, content, summary);
    }
}
