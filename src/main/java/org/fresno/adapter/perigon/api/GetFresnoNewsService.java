package org.fresno.adapter.perigon.api;


import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;
import org.fresno.repo.NewsService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class GetFresnoNewsService implements NewsService {
    private final SimpleDateFormat dashDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Response get() throws IOException {
        Date date = new Date();
        String dateInDashFormat = dashDateFormat.format(date);
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        try (client) {
            ListenableFuture<Response> res = client.prepare("GET", "https://api.goperigon.com/v1/all?topic=Fresno&apiKey=6996701b-9404-41b6-a638-e9e93b5d2af5&from=" + dateInDashFormat + "&to=" + dateInDashFormat + "&language=en&sortBy=relevance&showReprints=true")
                    .setHeader("accept", "application/json")
                    .setHeader("Accept", "application/json")
                    .execute();
            return res.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }


    }
}

