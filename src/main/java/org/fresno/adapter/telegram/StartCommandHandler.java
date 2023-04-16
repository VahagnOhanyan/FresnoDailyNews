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
    public void handlerMesaage(Update update, SendMessage response) {
     /*   if (user.isPresent()) {
            response.setText("Вы уже зарегистрированы, вы можете обновить пароль:");
        } else {
            response.setText("Добро пожаловать! Введите логин:");
        }*/
    }
}
