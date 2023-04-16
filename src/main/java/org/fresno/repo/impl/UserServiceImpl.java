package org.fresno.repo.impl;

import lombok.RequiredArgsConstructor;
import org.fresno.domain.User;
import org.fresno.exceptions.DublicateEntityException;
import org.fresno.repo.UserRepository;
import org.fresno.repo.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void register(String telegramId, String firstName, String lastName, String username) throws DublicateEntityException {
        if (userRepository.existsById(telegramId)) {
            throw new DublicateEntityException("Пользователь с таким логином уже существует");
        } else {

            userRepository.save(new User(telegramId, firstName, lastName, username));


        }
    }

}