package com.example.CloseContactSearcher.controller;

import com.example.CloseContactSearcher.entity.*;
import com.example.CloseContactSearcher.jsr303.LoginOperation;
import com.example.CloseContactSearcher.service.LoginService;
import com.example.CloseContactSearcher.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Api
@Validated
@RestController
public class LoginController {
    @Resource
    private LoginService loginService;

    @Resource
    private RegisterService registerService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation("注册，并返回token")
    @PostMapping("/login")
    public ResponseResult login(@RequestBody @Valid @Validated(value = {LoginOperation.class}) User user){
        ResponseResult result = loginService.login(user);
        if(result.getCode() == 200)
            logger.info("账号 { 账号: " + user.getAccount() + "} 登录成功");
        return result;
    }


    @PreAuthorize("hasAuthority('sys:user')")
    @ApiOperation("完善用户信息")
    @PostMapping("/setAdd")
    public ResponseResult setAdd(@RequestBody @Valid @Validated(value = {LoginOperation.class}) User user){
        ResponseResult result = loginService.setAdd(user);

        if(result.getCode() == 200)
            logger.info("账号 { 账号: " + user.getAccount() + "} 信息完善成功");
        return result;
    }

    @ApiOperation("注册后登录，并返回token")
    @PostMapping("/register")
    public ResponseResult register(@RequestBody @Valid @Validated(value = {LoginOperation.class}) Until until){
        //检验验证码是否匹配
        Register trueRegister = registerService.findRegisterByAccount(until.getAccount());
        String trueCode = trueRegister.getCode();
        if(!trueCode.equals(until.getCode())){
            return new ResponseResult<Map>(401 ,"验证码错误");
        }

        //完成注册，插入账号和密码
        User user = new User();
        user.setAccount(until.getAccount());
        user.setPassword(until.getPassword());

        ResponseResult result = loginService.register(user);

        if(result.getCode() == 200)
            logger.info("账号 { 账号: " + user.getAccount() + "} 注册成功");
        return result;
    }

    @ApiOperation("保存验证码222")
    @PostMapping("/saveYzm")
    public ResponseResult saveYzm(@RequestBody @Valid @Validated(value = {LoginOperation.class}) Register register){
        logger.info("account=" + register.getAccount());
        logger.info("code=" + register.getCode());
        //删除原来发送过的验证码
        int res1 = registerService.delRegisterByAccount(register.getAccount());
        int res = registerService.saveYzm(register);
        return new ResponseResult<Map>(200 ,"操作成功");
    }

    @ApiOperation("注销")
    @GetMapping("/logOut")
    public ResponseResult logOut() {
        ResponseResult result = loginService.logout();
        if(result.getCode() == 200)
            logger.info("注销成功");
        return result;
    }
}