package com.example.website.service;

import com.example.website.model.UserModel;
import com.example.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new CustomUserDetails(user);

    }

    public UserModel findById(Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        return user.orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public void add(UserModel user) {
        userRepository.save(user);
    }
    public void save(UserModel user) {
        userRepository.save(user);
    }
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
