package org.fresno;

import lombok.RequiredArgsConstructor;
import org.asynchttpclient.Response;
import org.fresno.adapter.telegram.TelegramAdapter;
import org.fresno.domain.News;
import org.fresno.domain.Verbs;
import org.fresno.repo.NewsRepository;
import org.fresno.repo.NewsService;
import org.fresno.repo.VerbsRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ScheduledTask {
    private final NewsService newsService;
    private final NewsRepository newsRepository;
    private final VerbsRepository verbsRepository;
    private final TelegramAdapter telegramAdapter;
    private final ExtractKeyWords findNewsDuplicate;
    private final GetVerbForms getVerbForms;
    private int score = 0;
    private int result = 0;
    private final static String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    @Value("${channel.id}")
    String id;

    @Scheduled(fixedRate = 1000000)
    public void updateVerbs() {
        /*for (String l :
                letters) {

            System.out.println("start");
            String verbs = getVerbForms.getAllVerbsStartWith(l);
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("verbs: " + verbs);
            String[] verbsArray = verbs.split(",");
            for (String v :
                    verbsArray) {
                Optional<Verbs> verb = verbsRepository.findById(v.trim());
                if (verb.isEmpty()) {
                    System.out.println("new verb: " + v);
                    verbsRepository.save(new Verbs(v.trim(), ""));
                    System.out.println("verb saved: " + v);
                }
            }

        }*/

        List<Verbs> allVerbs = verbsRepository.findAll();
        for (Verbs v :
                allVerbs) {
            System.out.println("verb received from db: " + v.getVerb());
            if (v.getForms() == null || v.getForms().equals("")) {
                String forms = getVerbForms.getVerbForms(v.getVerb());
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("forms: " + forms);
                v.setForms(forms.trim());
                verbsRepository.save(v);
            }
        }
    }

    @Scheduled(fixedRate = 60000)
    public void checkUpdates() {
        try {
            Response response = newsService.get();
            System.out.println("checkUpdates...................................................");
            var data = new JSONObject(response.getResponseBody());
            var articles = data.getJSONArray("articles");
            for (int i = 0; i < articles.length(); i++) {
                System.out.println("New article...............................................");
                boolean isDuplicate = false;
                JSONObject article = articles.getJSONObject(i);
                String url = String.valueOf(article.get("url"));
                String title = String.valueOf(article.get("title"));
                String description = String.valueOf(article.get("description"));
                String keywords = findNewsDuplicate.extractKeyWords(title);
                String keywords2 = "";
                List<News> allNews = newsRepository.findAll();
                for (News n :
                        allNews) {
                    result = 0;
                    score = 0;
                    if (url.equals(String.valueOf(n.getUrl()))) {
                        System.out.println("url: " + url);
                        System.out.println("n.getUrl(): " + n.getUrl());
                        isDuplicate = true;
                        System.out.println("isDuplicate: " + isDuplicate);
                        System.out.println("Break........................................");
                        break;
                    }
                    if (n.getKeywords() == null || n.getKeywords().equals("")) {
                        keywords2 = findNewsDuplicate.extractKeyWords(n.getTitle());
                        System.out.println("extracted keywords: " + keywords);
                        System.out.println("extracted keywords2: " + keywords2);
                        n.setKeywords(keywords2);
                        newsRepository.save(n);
                    } else {
                        keywords2 = n.getKeywords();

                    }
                    System.out.println("title: " + title);
                    System.out.println("n.getTitle(): " + n.getTitle());
                    System.out.println("compare keywords: " + keywords);
                    System.out.println("compare keywords2: " + keywords2);
                    result = compareKeyWords(keywords, keywords2);
                    System.out.println("result: " + result);
                    int textLength = n.getTitle().split(" ").length;
                    int textLengthHalf = textLength / 2;
                    System.out.println("textLength: " + textLength);
                    System.out.println("textLengthHalf: " + textLengthHalf);
                    if (result > textLengthHalf) {
                        isDuplicate = true;
                    }
                    System.out.println("isDuplicate: " + isDuplicate);
                    if (isDuplicate) {
                        System.out.println(title + " is duplicate of");
                        System.out.println(n.getTitle());
                        System.out.println("Break........................................");
                        break;
                    }

                }
                if (isDuplicate) {
                    System.out.println("Continue........................................");
                    continue;
                }
                System.out.println("Start filling news object................................................");
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
                news.setKeywords(keywords);
                System.out.println("End filling news object................................................");
                newsRepository.save(news);
                System.out.println("News saved................................................");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            List<News> allNotPublishedNews = newsRepository.findByIsPublished(false);
            for (News n :
                    allNotPublishedNews) {
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

    private int compareKeyWords(String keywords, String keywords2) {
        String[] keywordsArray = keywords.split(",");
        String[] keywordsArray2 = keywords2.split(",");
        Map<String, List<String>> wordSynMap = new HashMap<>();
        Map<String, List<String>> wordSynMap2 = new HashMap<>();
        for (String kw :
                keywordsArray) {
            if (wordSynMap.containsKey(kw.substring(0, kw.indexOf(":")))) {
                ArrayList<String> wordSynList = (ArrayList<String>) wordSynMap.get(kw.substring(0, kw.indexOf(":")));
                wordSynList.add(kw.substring(kw.indexOf(":") + 1));
            } else {
                wordSynMap.put(kw.substring(0, kw.indexOf(":")), new ArrayList<>(List.of(kw.substring(kw.indexOf(":") + 1))));
            }

        }
        for (String kw2 :
                keywordsArray2) {
            if (wordSynMap2.containsKey(kw2.substring(0, kw2.indexOf(":")))) {
                ArrayList<String> wordSynList2 = (ArrayList<String>) wordSynMap2.get(kw2.substring(0, kw2.indexOf(":")));
                wordSynList2.add(kw2.substring(kw2.indexOf(":") + 1));
            } else {
                wordSynMap2.put(kw2.substring(0, kw2.indexOf(":")), new ArrayList<>(List.of(kw2.substring(kw2.indexOf(":") + 1))));
            }

        }
        Set<String> wordSynKeysSet = wordSynMap.keySet();
        for (String k :
                wordSynKeysSet) {

            boolean exists = findIn(k, wordSynMap2);
            if (!exists) {
                for (String v :
                        wordSynMap.get(k)) {
                    exists = findIn(v, wordSynMap2);
                    if (exists) {
                        break;
                    }
                }
            }
        }
        return score;
    }

    private boolean findIn(String k, Map<String, List<String>> wordSynMap2) {
        boolean exists = false;
        outer:
        for (String k2 :
                wordSynMap2.keySet()) {
            if (k.equals(k2)) {
                exists = true;
                score = score + 1;
                break;
            } else {
                for (String v2 :
                        wordSynMap2.get(k2)) {
                    if (k.equals(v2)) {
                        exists = true;
                        score = score + 1;
                        break outer;
                    }
                }
            }

        }
        return exists;
    }
}