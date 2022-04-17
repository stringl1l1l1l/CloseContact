package com.example.CloseContactSearcher.service;

import com.example.CloseContactSearcher.entity.ResponseResult;
import com.example.CloseContactSearcher.entity.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult register(User user);

    ResponseResult logout();

    ResponseResult setAdd(User user);
}
