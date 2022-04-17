package com.example.parkingSystem.controller;

import com.example.parkingSystem.entity.*;
import com.example.parkingSystem.jsr303.InsertOperation;
import com.example.parkingSystem.jsr303.SetOperation;
import com.example.parkingSystem.service.HealthService;
import com.example.parkingSystem.service.InfoService;
import com.example.parkingSystem.service.LoginService;
import com.example.parkingSystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("rawtypes")
@Api
@Validated
@RestController
@RequestMapping("/health")
public class HealthController {

    @Resource
    private HealthService healthService;

    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    @PreAuthorize(value = "hasAuthority('sys:put')")
    @PutMapping("/insertHealth")
    public ResponseResult insertHealth(@RequestBody @Valid Health health) {
        healthService.insertHealth(health);
        return new ResponseResult<Map>(200,"操作成功");
    }
}