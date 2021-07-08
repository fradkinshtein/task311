package com.example.task311.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.task311.model.Role;
import com.example.task311.model.User;
import com.example.task311.repositories.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements com.example.task311.service.RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;

    }

    @Override
    @Transactional
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public Role getRoleById(Long id) {
        return roleRepository.getOne(id);
    }

    @Override
    @Transactional
    public void setRolesToUser(User user, Long[] roles) {
        Set<Role> roleList = new HashSet<>();
        for (Long id : roles) {
            roleList.add(roleRepository.findById(id).orElse(null));
        }
        user.setRoles(roleList);
    }
}
