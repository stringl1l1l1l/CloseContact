package com.example.CloseContactSearcher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.CloseContactSearcher.mapper.UserInfoMapper;
import com.example.CloseContactSearcher.mapper.UserMapper;
import com.example.CloseContactSearcher.entity.User;
import com.example.CloseContactSearcher.mapper.UserRoleMapper;
import com.example.CloseContactSearcher.service.UserService;
import com.example.CloseContactSearcher.util.JwtUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.Claims;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public PageInfo<User> showAllUsersByPages(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.showAllUsers());
        return pageInfo;
    }

    @Override
    public List<User> showAllUsers() {
        return userMapper.showAllUsers();
    }

    @Override
    public List<User> findAllUsers()  {
        return userMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public User findUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public User findUserByPhoneNum(String phoneNum)  {
        return userMapper.selectOne(
                new LambdaQueryWrapper<User>().eq(User::getAccount,phoneNum)
        );
    }

    @Override
    public User findUserByUsername(String username)  {
        return userMapper.selectOne(
                new LambdaQueryWrapper<User>().eq(User::getUsername,username)
        );
    }

    @Override
    public List<User> findUsersByMap(Map<String, Object> map) {
        return userMapper.selectByMap(map);
    }

    @Override
    public int deleteUserById(Long id)  {
        return userMapper.deleteById(id);
    }

    @Override
    public int deleteUserByMap(Map<String, Object> map) {
        return userMapper.deleteByMap(map);
    }

    @Override
    public int updateUserById(User user)  {
        if(StringUtils.hasText(user.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getUserId,user.getUserId());
        return userMapper.update(user,wrapper);
    }

    @Override
    public int setUserById(User user) {
        if(StringUtils.hasText(user.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        LambdaUpdateWrapper<User> wrapper =  new LambdaUpdateWrapper<>();
        wrapper.eq(User::getUserId,user.getUserId())
                .set(User::getUsername,user.getUsername())
                .set(User::getAccount,user.getAccount())
                .set(User::getPassword,user.getPassword())
                .set(User::getSex,user.getSex())
                .set(User::getAddress,user.getAddress());

        return userMapper.update(user,wrapper);
    }

    @Override
    public int insertUser(User user)  {
        return userMapper.insert(user);
    }

    @Override
    public List<String> getUserRoles(String token) {
        try {
            Claims claims = JwtUtil.parseJWT(token);
            long id = Long.parseLong(claims.getSubject());
            return userInfoMapper.getUserRoles(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int restoreUserById(Long userId) {
        return userMapper.restoreUserById(userId);
    }

    @Override
    public User showUserById(Long userId) {
        return userMapper.showUserById(userId);
    }

    @Override
    public User getUserInfo(String token) {
        try {
            Claims claims = JwtUtil.parseJWT(token);
            long id = Long.parseLong(claims.getSubject());
            return userMapper.selectById(id);
        } catch (Exception e) {
                e.printStackTrace();
        }
        return null;
    }
}
