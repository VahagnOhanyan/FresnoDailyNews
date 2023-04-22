package org.fresno.adapter.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("newsHandler")
public class NewsHandlerImpl implements CommandHandler {
    @Override
    public void handlerMessage(Update update, SendMessage response) {
        response.setChatId(String.valueOf(update.getMessage().getChatId()));
        response.setText("Благодарим за новость!");
    }
}
