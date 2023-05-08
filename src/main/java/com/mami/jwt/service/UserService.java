package com.mami.jwt.service;

import com.mami.jwt.entity.AppUser;
import com.mami.jwt.entity.Role;
import com.mami.jwt.repository.RoleRepository;
import com.mami.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleRepository.save(userRole);

        AppUser adminAppUser = new AppUser();
        adminAppUser.setUserName("admin77");
        adminAppUser.setUserPassword(getEncodedPassword("admin@password77"));
        adminAppUser.setUserFirstName("admin");
        adminAppUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminAppUser.setRole(adminRoles);
        userRepository.save(adminAppUser);

        /*AppUser user = new AppUser();
        user.setUserName("lalik77");
        user.setUserPassword(getEncodedPassword("user@password77"));
        user.setUserFirstName("Alex");
        user.setUserLastName("Mami");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);*/
    }

    public AppUser registerNewUser(AppUser appUser) {
        Role role = roleRepository.findById("User").get();
        System.out.println(role);
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        appUser.setRole(userRoles);
        appUser.setUserPassword(getEncodedPassword(appUser.getUserPassword()));

        return userRepository.save(appUser);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
