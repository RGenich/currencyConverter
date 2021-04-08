package com.test.currencyConverter.services;

import com.test.currencyConverter.models.Role;
import com.test.currencyConverter.repositories.RoleRepository;
import com.test.currencyConverter.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        Role returnedRol = findByName(role.getName());

        return returnedRol == null ? roleRepository.save(role): returnedRol;
    }

    @Override
    public Role findByName(String name) {
        return null;
    }
}
