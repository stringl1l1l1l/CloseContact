package com.example.CloseContactSearcher.service;

import com.example.CloseContactSearcher.entity.Space;

import java.time.LocalDateTime;
import java.util.List;

public interface SpaceService {

    List<Space> checkEmptySpaceDuringPeriodById(Long parkingLotId, LocalDateTime begTime, LocalDateTime endTime);
}
