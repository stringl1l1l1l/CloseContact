package com.example.CloseContactSearcher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.CloseContactSearcher.controller.LoginController;
import com.example.CloseContactSearcher.entity.*;
import com.example.CloseContactSearcher.mapper.RoleMapper;
import com.example.CloseContactSearcher.mapper.UserMapper;
import com.example.CloseContactSearcher.mapper.UserRoleMapper;
import com.example.CloseContactSearcher.service.LoginService;
import com.example.CloseContactSearcher.util.JwtUtil;
import com.example.CloseContactSearcher.util.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@SuppressWarnings("rawtypes")
@Transactional
@Service("loginService")
public class LoginServiceImpl implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private AuthenticationManager authenticationManagerBean;

    @Resource
    private RedisCache redisCache;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public ResponseResult login(User user) {
        //数据库中查询用户是否存在
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getAccount() ,user.getPassword());
        Authentication authentication = authenticationManagerBean.authenticate(authenticationToken);
        if(Objects.isNull(authentication)){
            throw new RuntimeException("登录失败");
        }

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        //查询当前用户是否已经登录
//        Object cacheObject = redisCache.getCacheObject("login:" + loginUser.getUser().getUserId());
//        if(!Objects.isNull(cacheObject)){
//            return new ResponseResult(200,"您已在登录状态，无需重复登录");
//        }
        //认证通过，使用userid生成一个jwt jwt存入ResponseResult返回
        String userid = loginUser.getUser().getUserId().toString();
        String jwt = JwtUtil.createJWT(userid);
        Map<String,String> map = new HashMap<>();
        map.put("token", jwt);
        //把完整的用户信息存入redis  userid作为key
        redisCache.setCacheObject("login:" + userid, loginUser);

        return new ResponseResult<Map>(200,"登录成功",map);
    }

    @Override
    public ResponseResult register(User user) {
        User u = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getAccount, user.getAccount()));
        if(Objects.isNull(u)){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUsername(user.getAccount());
            user.setType("0");
            user.setSex("0");
            user.setAge(0) ;
            user.setMac("1");
            user.setAddress("1");
            userMapper.insert(user);

            String userId = user.getUserId().toString();
            String jwt = JwtUtil.createJWT(userId);
            Map<String,String> map = new HashMap<>();
            map.put("token", jwt);
            //授予该用户权限
            List<String> list = roleMapper.selectPermByRoleName("用户");
            redisCache.setCacheObject("login:" + userId, new LoginUser(user,list));
            //在user_role表中添加用户与角色的关联
            userRoleMapper.insert(new UserRole(user.getUserId(),
                    roleMapper.selectOne(
                            new LambdaQueryWrapper<Role>()
                                .eq(Role::getRoleName,"用户"))
                                .getRoleId())
            );
            return new ResponseResult<Map>(200,"注册成功", map);
        }
        return new ResponseResult<>(200, "用户已存在,请登录");
    }

    @Override
    public ResponseResult setAdd(User user) {
            LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(User::getAccount,user.getAccount());
            userMapper.update(user,wrapper);
            return new ResponseResult<Map>(200,"完善成功");
    }

    @Override
    public ResponseResult logout() {
        //从SecurityContext中获取token
        UsernamePasswordAuthenticationToken authenticationToken
                = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        Long userId = loginUser.getUser().getUserId();
        //删除redis中的值
        redisCache.deleteObject("login:" + userId);
        return new ResponseResult<>(200,"注销成功");
    }
}
