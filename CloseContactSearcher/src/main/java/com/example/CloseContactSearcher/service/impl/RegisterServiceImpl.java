package com.example.CloseContactSearcher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.CloseContactSearcher.controller.LoginController;
import com.example.CloseContactSearcher.entity.Register;
import com.example.CloseContactSearcher.mapper.RegisterMapper;
import com.example.CloseContactSearcher.service.RegisterService;
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
