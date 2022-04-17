package com.example.parkingSystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.parkingSystem.entity.*;
import com.example.parkingSystem.mapper.*;
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
@Service("infoService")
public class InfoServiceImpl implements InfoService {

    @Resource
    private InfoMapper infoMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private CloseMapper closeMapper;

    @Override
    public int insertInfo(Info info) {
        //查询数据库中是否已经存储该配对
        Info info1 = infoMapper.selectOne(
                new LambdaQueryWrapper<Info>()
                        .eq(Info::getAccount,info.getAccount())
                        .eq(Info::getMac, info.getMac())
        );
        if(Objects.isNull(info1)){
            //如果没有该匹配，则插入
        }
        else{
            //如果之前已经有该匹配，则删除，再重新插入，表示更新时间
            infoMapper.delete(
                    new LambdaUpdateWrapper<Info>()
                            .eq(Info::getAccount,info.getAccount())
                            .eq(Info::getMac, info.getMac())
            );
        }
        infoMapper.insert(info);
        //同步更新close表,查找当前的mac是否有对应已注册的账号
        User user = userMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getMac, info.getMac())
        );
        //如果有，则插入close表
        if(!Objects.isNull(user)){
            //判断close表中是否之前已经有记录，如果已经有，则更新时间
            Close close1 = closeMapper.selectOne(
                    new LambdaQueryWrapper<Close>()
                            .eq(Close::getAccountA,info.getAccount())
                            .eq(Close::getAccountB, user.getAccount())
            );
            if(!Objects.isNull(close1)){
                //如果之前已经有该匹配，则删除，再重新插入，表示更新时间
                closeMapper.delete(
                        new LambdaUpdateWrapper<Close>()
                                .eq(Close::getAccountA,info.getAccount())
                                .eq(Close::getAccountB, user.getAccount())
                );
            }
            Close close2 = closeMapper.selectOne(
                    new LambdaQueryWrapper<Close>()
                            .eq(Close::getAccountA,user.getAccount())
                            .eq(Close::getAccountB, info.getAccount())
            );
            if(!Objects.isNull(close2)){
                //如果之前已经有该匹配，则删除，再重新插入，表示更新时间
                closeMapper.delete(
                        new LambdaUpdateWrapper<Close>()
                                .eq(Close::getAccountA,user.getAccount())
                                .eq(Close::getAccountB, info.getAccount())
                );
            }
            Close close3 = new Close();
            close3.setAccountA(info.getAccount());
            close3.setAccountB(user.getAccount());
            closeMapper.insert(close3);
        }
        return 1;
    }
}
