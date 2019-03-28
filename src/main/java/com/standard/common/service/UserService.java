package com.standard.common.service;

import com.standard.common.domain.User;
import com.standard.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findOne(Long id) {
        return userRepository.findUserById(id);
    }
}

