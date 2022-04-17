package com.example.parkingSystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.parkingSystem.entity.*;
import com.example.parkingSystem.mapper.*;
import com.example.parkingSystem.service.HealthService;
import com.example.parkingSystem.service.InfoService;
import com.example.parkingSystem.service.UserService;
import com.example.parkingSystem.util.JwtUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.Claims;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Transactional
@Service("healthService")
public class HealthServiceImpl implements HealthService {


    @Resource
    private HealthMapper healthMapper;

    @Override
    public int insertHealth(Health health) {
            healthMapper.insert(health);
        return 1;
    }
}
