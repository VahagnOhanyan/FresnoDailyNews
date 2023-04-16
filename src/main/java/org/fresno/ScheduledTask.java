package org.fresno;

import lombok.RequiredArgsConstructor;
import org.asynchttpclient.Response;
import org.fresno.adapter.telegram.TelegramAdapter;
import org.fresno.domain.News;
import org.fresno.repo.NewsRepository;
import org.fresno.repo.NewsService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.Calendar;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduledTask {
    private final NewsService newsService;
    private final NewsRepository newsRepository;
    private final TelegramAdapter telegramAdapter;
    @Value("${channel.id}")
    String id;

    @Scheduled(fixedRate = 60000)
    public void checkUpdates() {
        try {
            Response response = newsService.get();
            var data = new JSONObject(response.getResponseBody());
            var articles = data.getJSONArray("articles");
            for (int i = 0; i < articles.length(); i++) {
                JSONObject article = articles.getJSONObject(i);
                News news = new News();
                news.setLanguage(String.valueOf(article.get("language")));
                news.setCountry(String.valueOf(article.get("country")));
                news.setUrl(URI.create(String.valueOf(article.get("url"))).toURL());
                String imagePath = String.valueOf(article.get("imageUrl"));
                if (imagePath.startsWith("http")) {
                    news.setImageUrl(URI.create(String.valueOf(article.get("imageUrl"))).toURL());
                }
                news.setDomain(String.valueOf(article.getJSONObject("source").get("domain")));
                news.setTitle(String.valueOf(article.get("title")));
                news.setDescription(String.valueOf(article.get("description")));
                news.setContent(String.valueOf(article.get("content")));
                news.setSummary(String.valueOf(article.get("summary")));
                JSONArray keywords = article.getJSONArray("keywords");
                List<News> allNews = newsRepository.findAll();
                boolean exists = false;
                for (News n :
                        allNews) {
                    if (n.equals(news)) {
                        exists = true;
                        break;
                    }

                }
          /*      String currentNewsKeyWords = "";
                for (int j = 0; j < keywords.length(); j++) {
                    JSONObject keyword = keywords.getJSONObject(j);
                    String name = String.valueOf(keyword.get("name"));
                    String[] wordsInName = name.split(" ");
                    for (String w :
                            wordsInName) {
                        currentNewsKeyWords = w + ";" + currentNewsKeyWords;
                    }
                }
                String[] currentNewsKeyWordsArray = currentNewsKeyWords.split(";");
                List<News> allNews = newsRepository.findAll();

                for (News n :
                        allNews) {
                    String[] keyWords = n.getKeywords().split(";");

                    for (int j = 0; j < keyWords.length; j++) {
                        for (int k = 0; k < currentNewsKeyWordsArray.length; k++) {
                            if (keyWords[j].equalsIgnoreCase(currentNewsKeyWordsArray[k])) {
                                exists = true;
                                break;
                            }
                        }
                    }

                }*/
                if (!exists) {
                    newsRepository.save(news);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            List<News> allNotPublishedNews = newsRepository.findByIsPublished(false);
            for (News n :
                    allNotPublishedNews) {
                System.out.println("n.getId(): " + n.getId());
                try {
                    Response res = telegramAdapter.updateNews(id, n.getTitle(), String.valueOf(n.getUrl()), String.valueOf(n.getImageUrl()));
                    if (res.getStatusCode() == 200) {
                        System.out.println(res.getStatusCode() + ": " + res.getStatusText() + ": " + res.getResponseBody());
                        newsRepository.updateIsPublishedById(true, n.getId());
                    } else {
                        System.out.println(res.getStatusCode() + ": " + res.getStatusText() + ": " + res.getResponseBody());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }

    }
}