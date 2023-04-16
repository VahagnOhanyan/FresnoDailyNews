package org.fresno.adapter.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;
import org.fresno.repo.NewsRepository;
import org.fresno.repo.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Component
@RequiredArgsConstructor
@Slf4j
public class TelegramBotAdapter extends TelegramLongPollingBot implements TelegramAdapter {
    private final NewsRepository newsRepository;

    private final UserService userService;
    private final Map<String, CommandHandler> messageHandlers;
    private static final String CMD_START = "/start";
    @Value("${bot.username}")
    String username;
    @Value("${bot.token}")
    String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Message received: {}", update);
        if (update.hasMessage()) {
            //Optional<User> user = userService.getByTelegramId(String.valueOf(update.getMessage().getChatId()));
            var response = new SendMessage();
            if (update.getMessage().getText().equals(CMD_START)) {
                response.setChatId(String.valueOf(update.getMessage().getChatId()));
                response.setText("Здраствуйте, " + update.getMessage().getFrom().getFirstName() + ", здесь вы можете предложить новость");
                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else {

            }
        }

    }

    @Override
    public Response updateNews(String telegramId, String message, String url, String imageUrl) {
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        try (client) {
            //if (imageUrl.equals("null") || imageUrl.equals("")) {
                System.out.println("message: " + message);
                System.out.println("url: " + url);
                String markdownText = "_" + message + "_" + "\n\n[Follow the link\n](" + url + ")";
                ListenableFuture<Response> res = client.prepare("POST", "https://api.telegram.org/bot" + token + "/sendMessage")
                        .setHeader("accept", "application/json")
                        .setHeader("content-type", "application/json")
                        .setBody("{\"text\":\"" + markdownText + "\"," +
                                "\"parse_mode\":\"Markdown\"," +
                                "\"disable_web_page_preview\":false," +
                                "\"disable_notification\":false," +
                                "\"reply_to_message_id\":null," +
                                "\"chat_id\":\"" + telegramId + "\"}")

                        .execute();


                return res.get();
            /*} else {

                ListenableFuture<Response> res = client.prepare("POST", "https://api.telegram.org/bot" + token + "/sendPhoto")
                        .setHeader("accept", "application/json")
                        .setHeader("content-type", "application/json")
                        .setBody("{\"photo\":\"" + imageUrl + "\"," +
                                "\"caption\":\"" + message + "\"," +
                                "\"disable_notification\":false," +
                                "\"reply_to_message_id\":null," +
                                "\"chat_id\":\"" + telegramId + "\"}")
                        .execute();

                return res.get();
            }*/

        } catch (IOException | ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
