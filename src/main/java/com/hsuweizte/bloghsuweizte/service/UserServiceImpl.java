package com.hsuweizte.bloghsuweizte.service;

import com.hsuweizte.bloghsuweizte.dao.UserRepository;
import com.hsuweizte.bloghsuweizte.po.User;
import com.hsuweizte.bloghsuweizte.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
