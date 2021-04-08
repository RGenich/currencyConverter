package com.test.currencyConverter.repositories;

import com.test.currencyConverter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByLogin(String login);

    User save(User user);
}
