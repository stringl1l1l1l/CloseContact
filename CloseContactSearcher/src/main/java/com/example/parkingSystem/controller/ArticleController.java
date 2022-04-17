package com.example.parkingSystem.controller;

import com.example.parkingSystem.entity.ParkingLot;
import com.example.parkingSystem.entity.Period;
import com.example.parkingSystem.entity.ResponseResult;
import com.example.parkingSystem.jsr303.InsertOperation;
import com.example.parkingSystem.service.ArticleService;
import com.example.parkingSystem.service.ParkingLotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@SuppressWarnings("rawtypes")
@Api
@Validated
@RestController()
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);


    @PreAuthorize(value = "hasAuthority('sys:get')")
    @ApiOperation("返回所有文章信息")
    @GetMapping("/findAllArticle")
    public ResponseResult findAllArticle(){
        return new ResponseResult<List>(200,"操作成功", articleService.findAllArticle());
    }

}
