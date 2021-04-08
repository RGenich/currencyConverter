package com.test.currencyConverter.repositories;

import com.test.currencyConverter.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role save(Role role);
}
