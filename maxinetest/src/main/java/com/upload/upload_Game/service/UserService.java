package com.upload.upload_Game.service;

import com.upload.upload_Game.pojo.User;


public interface UserService {
    User getUserById(Integer id);
    Integer insertUser(User user);
    Integer getUserNumber();

}
