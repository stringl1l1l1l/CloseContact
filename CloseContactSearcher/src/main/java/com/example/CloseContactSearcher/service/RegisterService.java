package com.example.CloseContactSearcher.service;

import com.example.CloseContactSearcher.entity.Register;

public interface RegisterService {

    int saveYzm(Register register);

    Register findRegisterByAccount(String account);

    int delRegisterByAccount(String account);
}
