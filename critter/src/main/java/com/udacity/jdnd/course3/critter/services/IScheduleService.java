package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.dtos.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entities.ScheduleEntity;
import java.util.List;

public interface IScheduleService {

    ScheduleDTO add(ScheduleDTO schedule);

    List<ScheduleDTO> findAll();

    List<ScheduleDTO> findByPet(Long petId);

    List<ScheduleDTO> findByCustomer(Long petId);

    List<ScheduleDTO> findByEmployee(Long employeeId);
}
