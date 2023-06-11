package xyz.le30r.lingualab.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import xyz.le30r.lingualab.auth.repository.AuthRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public
class CustomUserDetailsService implements UserDetailsService {
    private final  AuthRepository repository;
    public UserDetails loadUserByUsername(String username) {
        val auth = repository.findByLogin(username);
        final Set<SimpleGrantedAuthority> roles = Set.of(new SimpleGrantedAuthority(auth.getRole().toString()));
        return new User(auth.getLogin(), auth.getPassword(), roles);
    }
}