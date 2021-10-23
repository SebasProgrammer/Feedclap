package com.example.easi641.services;

import com.example.easi641.dto.UserDto;
import com.example.easi641.entities.User;
import com.example.easi641.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public User createUser(UserDto userDto) {
        User newUser = initUser(userDto);
        return userRepository.save(newUser);
    }

    private User initUser(UserDto userDto) {
        User user = new User();
        user.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setToken(userDto.getToken());
        user.setBirthdate(userDto.getBirthdate());
        user.setType(0);
        return user;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }
}
