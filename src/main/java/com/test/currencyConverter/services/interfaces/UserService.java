package com.test.currencyConverter.services.interfaces;

import com.test.currencyConverter.models.User;

public interface UserService {
    User save(User user);

    User getUserByLogin(String login);
}
