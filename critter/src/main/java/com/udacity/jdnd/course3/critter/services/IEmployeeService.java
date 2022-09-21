package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.dtos.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IEmployeeService {

    EmployeeEntity add(EmployeeDTO employee);

    EmployeeEntity findById(Long employeeId);

    List<EmployeeEntity> findByIds(List<Long> ids);

    List<EmployeeEntity> findByAvailability(Set<EmployeeSkill> skills, LocalDate date);
}
