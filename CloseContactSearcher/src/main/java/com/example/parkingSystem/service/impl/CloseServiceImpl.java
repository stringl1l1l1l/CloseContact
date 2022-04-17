package com.example.parkingSystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.parkingSystem.entity.Info;
import com.example.parkingSystem.entity.ParkingLot;
import com.example.parkingSystem.entity.UserInfo;
import com.example.parkingSystem.mapper.InfoMapper;
import com.example.parkingSystem.mapper.UserInfoMapper;
import com.example.parkingSystem.mapper.UserMapper;
import com.example.parkingSystem.entity.User;
import com.example.parkingSystem.mapper.UserRoleMapper;
import com.example.parkingSystem.service.CloseService;
import com.example.parkingSystem.service.InfoService;
import com.example.parkingSystem.service.UserService;
import com.example.parkingSystem.util.JwtUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.Claims;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Transactional
@Service("closeService")
public class CloseServiceImpl implements CloseService {

//    @Resource
//    private InfoMapper infoMapper;
//
//    @Override
//    public int insertInfo(Info info) {
//        //查询数据库中是否已经存储该配对
//        Info info1 = infoMapper.selectOne(
//                new LambdaQueryWrapper<Info>()
//                        .eq(Info::getAccount,info.getAccount())
//                        .eq(Info::getMac, info.getMac())
//        );
//        if(Objects.isNull(info1)){
//            //如果没有该匹配，则插入
//            infoMapper.insert(info);
//        }
//        else{
//            //如果之前已经有该匹配，则删除，再重新插入，表示更新时间
//            infoMapper.delete(
//                    new LambdaUpdateWrapper<Info>()
//                            .eq(Info::getAccount,info.getAccount())
//                            .eq(Info::getMac, info.getMac())
//            );
//            infoMapper.insert(info);
//        }
//        return 1;
//    }
}
