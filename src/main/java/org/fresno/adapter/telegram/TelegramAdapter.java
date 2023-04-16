package org.fresno.adapter.telegram;

import org.asynchttpclient.Response;

import java.io.FileNotFoundException;

public interface TelegramAdapter {

    Response updateNews(String telegramId, String message, String url, String imageUrl) throws FileNotFoundException;
}
