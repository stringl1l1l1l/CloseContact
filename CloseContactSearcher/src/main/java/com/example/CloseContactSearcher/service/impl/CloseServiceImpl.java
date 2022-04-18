package com.example.CloseContactSearcher.service.impl;

import com.example.CloseContactSearcher.entity.Close;
import com.example.CloseContactSearcher.mapper.CloseMapper;
import com.example.CloseContactSearcher.service.CloseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Transactional
@Service("closeService")
public class CloseServiceImpl implements CloseService {

    @Resource
    private CloseMapper closeMapper;

    @Override
    public List<Close> findCloseByMap(Map<String, Object> map) {
        return closeMapper.selectByMap(map);
    }
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
