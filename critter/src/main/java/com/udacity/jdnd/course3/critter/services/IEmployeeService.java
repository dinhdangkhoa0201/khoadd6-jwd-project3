package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.dtos.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IEmployeeService {

    EmployeeDTO add(EmployeeDTO employee);

    EmployeeDTO findById(Long employeeId);

    List<EmployeeDTO> findByIds(List<Long> ids);

    List<EmployeeDTO> findByAvailability(Set<EmployeeSkill> skills, LocalDate date);
}
