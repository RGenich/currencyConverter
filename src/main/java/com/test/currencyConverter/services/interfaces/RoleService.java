package com.test.currencyConverter.services.interfaces;

import com.test.currencyConverter.models.Role;

public interface RoleService {
    Role save(Role role);

    Role findByName(String name);
}
