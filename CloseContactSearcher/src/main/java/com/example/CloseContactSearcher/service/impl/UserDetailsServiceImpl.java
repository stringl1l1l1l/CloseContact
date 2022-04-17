package com.example.CloseContactSearcher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.CloseContactSearcher.entity.LoginUser;
import com.example.CloseContactSearcher.entity.User;
import com.example.CloseContactSearcher.mapper.MenuMapper;
import com.example.CloseContactSearcher.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("用户不存在");
        }
        //设置权限信息
        List<String> list = menuMapper.selectPermByUserId(user.getUserId());
        return new LoginUser(user,list);
    }
}
