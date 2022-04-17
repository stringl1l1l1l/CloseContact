package com.example.CloseContactSearcher;

import com.example.CloseContactSearcher.service.ParkingLotService;
import com.example.CloseContactSearcher.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootDemoApplicationTests {

    @Test
    void contextLoads() {
        UserService userService = BeanUtil.getBean(UserService.class);
        ParkingLotService parkingLotService = BeanUtil.getBean(ParkingLotService.class);
        System.out.println(parkingLotService.findAllCommentByParkingLotId(1L));
    }





}
