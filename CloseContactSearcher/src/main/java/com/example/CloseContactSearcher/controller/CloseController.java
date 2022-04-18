package com.example.CloseContactSearcher.controller;

import com.example.CloseContactSearcher.entity.Close;
import com.example.CloseContactSearcher.entity.ResponseResult;
import com.example.CloseContactSearcher.entity.User;
import com.example.CloseContactSearcher.service.CloseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Api
@Validated
@RestController
@RequestMapping("/close")
public class CloseController {
    @Resource
    private CloseService closeService;

    @PreAuthorize(value = "hasAuthority('sys:user')")
    @ApiOperation("根据请求体中的键值对查询满足条件的所有用户, 例如：{ 'car_num'(列名) : *******(值) }" )
    @PostMapping("/findCloseByMap")
    public ResponseResult findUsersByMap(@RequestBody Map<String, Object> map){
        List<Close> closeList = closeService.findCloseByMap(map);
        if(closeList.isEmpty()){
            return new ResponseResult<>(200,"未查询到结果");
        }
        return new ResponseResult<>(200, "操作成功", closeList);
    }
}
