package com.example.CloseContactSearcher.service;

import com.example.CloseContactSearcher.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


public interface UserService {
    List<User> findAllUsers();

    User findUserById(Long id);

    User findUserByUsername(String username);

    User findUserByPhoneNum(String phoneNum);

    List<User> findUsersByMap(Map<String, Object> map);

    int deleteUserById(Long id);

    int deleteUserByMap(Map<String, Object> map);

    int updateUserById(User user);

    int setUserById(User user);

    int insertUser(User user);

    User getUserInfo(String token);

    List<String> getUserRoles(String token);

    List<User> showAllUsers();

    User showUserById(Long userId);

    int restoreUserById(Long userId);

    PageInfo<User> showAllUsersByPages(int pageNum, int pageSize);
}
