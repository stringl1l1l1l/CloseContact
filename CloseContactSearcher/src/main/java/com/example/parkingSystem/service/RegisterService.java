package com.example.parkingSystem.service;

import com.example.parkingSystem.entity.Register;

public interface RegisterService {

    int saveYzm(Register register);

    Register findRegisterByAccount(String account);

    int delRegisterByAccount(String account);
}
