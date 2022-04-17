package com.example.parkingSystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.parkingSystem.controller.LoginController;
import com.example.parkingSystem.entity.Register;
import com.example.parkingSystem.entity.User;
import com.example.parkingSystem.mapper.RegisterMapper;
import com.example.parkingSystem.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service("registerService")
public class RegisterServiceImpl  implements RegisterService {

    @Resource
    private RegisterMapper registerMapper;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Override
    public int saveYzm(Register register) {
         return  registerMapper.insert(register);
    }

    @Override
    public Register findRegisterByAccount(String account)  {
        return registerMapper.selectOne(
                new LambdaQueryWrapper<Register>().eq(Register::getAccount,account)
        );
    }

    @Override
    public int delRegisterByAccount(String account)  {
        LambdaUpdateWrapper<Register> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Register::getAccount,account);
        return registerMapper.delete(wrapper);
    }
}
