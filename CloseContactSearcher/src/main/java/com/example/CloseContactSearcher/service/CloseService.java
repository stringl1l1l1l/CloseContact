package com.example.CloseContactSearcher.service;

import com.example.CloseContactSearcher.entity.Close;

import java.util.List;
import java.util.Map;

public interface CloseService {

    List<Close> findCloseByMap(Map<String, Object> map);
}
