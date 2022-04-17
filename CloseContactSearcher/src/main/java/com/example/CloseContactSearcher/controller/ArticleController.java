package com.example.CloseContactSearcher.controller;

import com.example.CloseContactSearcher.entity.ResponseResult;
import com.example.CloseContactSearcher.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


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
