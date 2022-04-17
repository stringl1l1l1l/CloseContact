package com.example.CloseContactSearcher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.CloseContactSearcher.entity.Order;
import com.example.CloseContactSearcher.entity.Space;
import com.example.CloseContactSearcher.mapper.SpaceMapper;
import com.example.CloseContactSearcher.service.OrderService;
import com.example.CloseContactSearcher.service.SpaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service("spaceService")
public class SpaceServiceImpl implements SpaceService {

    @Resource
    private SpaceMapper spaceMapper;

    @Resource
    private OrderService orderService;

//    @Override
//    public List<Space> checkEmptySpaceDuringPeriod(LocalDateTime beginTime, LocalDateTime endTime) {
//        List<Order> orders = orderService.findAllOrderDuringPeriod(beginTime, endTime);
//        if(orders.isEmpty()) {
//            return spaceMapper.selectList(new LambdaQueryWrapper<Space>().groupBy(Space::getParkingLotId));
//        }
//        else {
//            List<Integer> spaceIds = new ArrayList<>();
//            for (Order order : orders) {
//                spaceIds.add(order.getSpaceId());
//            }
//            return spaceMapper.selectList(
//                    new LambdaQueryWrapper<Space>()
//                            .notIn(Space::getSpaceId, spaceIds)
//            );
//        }
//    }

    @Override
    public List<Space> checkEmptySpaceDuringPeriodById(Long parkingLotId, LocalDateTime begTime, LocalDateTime endTime) {
        List<Order> orders = orderService.findAllOrderOfOneParkingLotDuringPeriod(parkingLotId, begTime, endTime);
        if(orders.isEmpty()) {
            return spaceMapper.selectList(
                    new LambdaQueryWrapper<Space>()
                            .eq(Space::getParkingLotId, parkingLotId)
            );
        }
        else {
            Set<Integer> spaceIds = new HashSet<>();
            for (Order order : orders) {
                spaceIds.add(order.getSpaceId());
            }
            List<Space> spaces = spaceMapper.selectList(
                    new LambdaQueryWrapper<Space>()
                            .eq(Space::getParkingLotId, parkingLotId)
                            .notIn(Space::getSpaceId, spaceIds)
            );
            return spaces;
        }
    }
}
