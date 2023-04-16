package org.fresno.adapter.telegram;

import lombok.RequiredArgsConstructor;
import org.fresno.exceptions.DublicateEntityException;
import org.fresno.repo.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * todo Document type CreateCommandHandler
 */
@Component("createCommandHandler")
@RequiredArgsConstructor
public class CreateCommandHandler implements CommandHandler {
    private final UserService userService;

    @Override
    public void handlerMesaage(Update update, SendMessage response) {
        String telegramId = String.valueOf(update.getMessage().getChatId());
        String firstName = update.getMessage().getFrom().getFirstName();
        String lastName = update.getMessage().getFrom().getLastName();
        String username = update.getMessage().getFrom().getUserName();
        try {
            userService.register(telegramId, firstName, lastName, username);
        } catch (DublicateEntityException e) {
            throw new RuntimeException(e);
        }

    }
}

