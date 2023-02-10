package com.example.crud.web.service;


import com.example.crud.web.dao.UserRepository;
import com.example.crud.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        System.out.println("-----------------------qdwqd_-----");
        Optional<User> personOptional = Optional.ofNullable(userRepository.findByLogin(login));

        if (personOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        System.out.println(personOptional.get());
        return personOptional.get();
    }
}