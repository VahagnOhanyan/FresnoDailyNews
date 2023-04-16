package org.fresno.repo;

import org.fresno.exceptions.DublicateEntityException;
import org.springframework.dao.DataAccessException;

import java.util.Set;

public interface UserService {

    void register(String telegramId, String firstName, String lastName, String username) throws DublicateEntityException;


}