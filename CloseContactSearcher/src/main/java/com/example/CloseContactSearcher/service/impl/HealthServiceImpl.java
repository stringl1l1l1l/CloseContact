package com.example.CloseContactSearcher.service.impl;

import com.example.CloseContactSearcher.entity.*;
import com.example.CloseContactSearcher.mapper.*;
import com.example.CloseContactSearcher.service.HealthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
