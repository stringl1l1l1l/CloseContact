package com.example.CloseContactSearcher.controller;

import com.example.CloseContactSearcher.entity.*;
import com.example.CloseContactSearcher.service.HealthService;
import io.swagger.annotations.Api;
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