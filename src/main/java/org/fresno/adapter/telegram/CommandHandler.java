package org.fresno.adapter.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


public interface CommandHandler {

    void handlerMessage(Update update, SendMessage response);
}
