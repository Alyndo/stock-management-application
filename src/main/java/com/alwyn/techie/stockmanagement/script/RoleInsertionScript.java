package com.alwyn.techie.stockmanagement.script;

import com.alwyn.techie.stockmanagement.enums.ERole;
import com.alwyn.techie.stockmanagement.model.Role;
import com.alwyn.techie.stockmanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class RoleInsertionScript implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        // Insert roles if they do not already exist
        if (roleRepository.findByName(ERole.ROLE_USER).isEmpty()){
            roleRepository.save(new Role(ERole.ROLE_USER));
        }

        if(roleRepository.findByName(ERole.ROLE_MODERATOR).isEmpty()){
            roleRepository.save(new Role(ERole.ROLE_MODERATOR));
        }

        if (roleRepository.findByName(ERole.ROLE_ADMIN).isEmpty()){
            roleRepository.save(new Role(ERole.ROLE_ADMIN));
        }
    }
}
