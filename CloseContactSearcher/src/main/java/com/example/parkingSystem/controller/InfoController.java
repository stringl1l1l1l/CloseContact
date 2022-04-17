package com.example.parkingSystem.controller;

import com.example.parkingSystem.entity.*;
import com.example.parkingSystem.jsr303.InsertOperation;
import com.example.parkingSystem.jsr303.SetOperation;
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
@RequestMapping("/info")
public class InfoController {

    @Resource
    private InfoService infoService;

    private static final Logger logger = LoggerFactory.getLogger(InfoController.class);

    @PreAuthorize(value = "hasAuthority('sys:put')")
    @ApiOperation("根据提供用户的ID更新对应的用户, 增量更新, null值不会更新" )
    @PutMapping("/setMac")
    public ResponseResult setMac(@RequestBody @Valid Until until) {
        String[] arr = until.getInfo().split(",");
        Info info = new Info();
        for(int i=0;i<arr.length;i++) {
            if (arr[i].charAt(2) == '{'){        //获取mac
                String mac = arr[i].substring(17,34);
                info.setAccount(until.getAccount());
                info.setMac(mac);
                infoService.insertInfo(info);
            }
            else if(arr[i].charAt(0) == '{'){
                String mac = arr[i].substring(15,32);
                info.setAccount(until.getAccount());
                info.setMac(mac);
                infoService.insertInfo(info);
            }
        }
        return new ResponseResult<Map>(200,"操作成功");
    }
}