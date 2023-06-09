package org.fresno.adapter.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * todo Document type StartCommandHandler
 */
@Component("startCommandHandler")
public class StartCommandHandler implements CommandHandler {
    @Override
    public void handlerMessage(Update update, SendMessage response) {
        response.setChatId(String.valueOf(update.getMessage().getChatId()));
        response.setText("Здравствуйте, " + update.getMessage().getFrom().getFirstName() + ", здесь вы можете предложить новость");

    }
}
