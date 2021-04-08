package com.test.currencyConverter.utils;

import com.test.currencyConverter.models.Role;
import com.test.currencyConverter.models.User;
import com.test.currencyConverter.services.interfaces.RoleService;
import com.test.currencyConverter.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserInitializer {

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;
    @Autowired
    RoleService roleService;

    public void initUsers() {
        initRoles();

        User admin =
                new User("admin", "admin", true, Collections.singleton (roleService.findByName("ROLE_ADMIN")));
        userService.save(admin);

        User user =
                new User("user", "user", true, Collections.singleton(roleService.findByName("ROLE_USER")));
        userService.save(user);
    }

    public void initRoles() {
        roleService.save(new Role("ROLE_ADMIN"));
        roleService.save(new Role("ROLE_USER"));
    }
}
