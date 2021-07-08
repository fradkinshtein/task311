package com.example.task311.service;

import com.example.task311.model.Role;
import com.example.task311.model.User;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();
    Role getRoleById(Long id);
    void setRolesToUser(User user, Long[] roles);
}
