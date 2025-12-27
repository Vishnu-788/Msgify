package org.project.msgify.services;

import org.project.msgify.models.UserPrincipal;
import org.project.msgify.models.Users;
import org.project.msgify.repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo repo;
    public CustomUserDetailsService(UserRepo repo){
        this.repo = repo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);
        if (user == null){
            throw UsernameNotFoundException.fromUsername(username);
        }
        return new UserPrincipal(user);
    }
}
