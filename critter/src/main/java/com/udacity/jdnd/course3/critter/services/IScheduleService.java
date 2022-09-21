package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.dtos.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entities.ScheduleEntity;
import java.util.List;

public interface IScheduleService {

    ScheduleEntity add(ScheduleDTO schedule);

    List<ScheduleEntity> findAll();

    List<ScheduleEntity> findByPet(Long petId);

    List<ScheduleEntity> findByCustomer(Long petId);

    List<ScheduleEntity> findByEmployee(Long employeeId);
}
