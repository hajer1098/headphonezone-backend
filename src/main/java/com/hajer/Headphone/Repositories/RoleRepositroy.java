package com.hajer.Headphone.Repositories;


import com.hajer.Headphone.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepositroy extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String roleName);
}
