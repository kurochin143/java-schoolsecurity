package com.lambdaschool.school;

import com.lambdaschool.school.model.Role;
import com.lambdaschool.school.model.User;
import com.lambdaschool.school.model.UserRoles;
import com.lambdaschool.school.repository.RoleRepository;
import com.lambdaschool.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

//@Component
@Transactional
public class SeedData implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        Role roleAdmin = new Role("admin");
        Role roleUser = new Role("user");

        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        ArrayList<UserRoles> adminUserRoles = new ArrayList<>();
        adminUserRoles.add(new UserRoles(new User(), roleAdmin));
        adminUserRoles.add(new UserRoles(new User(), roleUser));

        User admin = new User("admin", "password", adminUserRoles);

        userRepository.save(admin);

    }
}
