package com.hsuweizte.bloghsuweizte.service;

import com.hsuweizte.bloghsuweizte.po.User;

public interface UserService {

    User checkUser(String username, String password);
}
