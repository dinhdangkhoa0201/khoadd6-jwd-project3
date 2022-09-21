package com.udacity.jdnd.course3.critter.controllers;

import com.udacity.jdnd.course3.critter.dtos.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dtos.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.services.IEmployeeService;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class EmployeeController {

    @Autowired
    private IEmployeeService iEmployeeService;

    @PostMapping("/employee")
    public EmployeeDTO save(@RequestBody EmployeeDTO dto) {
        return iEmployeeService.add(dto).builder();
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO findById(@PathVariable("employeeId") Long employeeId) {
        return iEmployeeService.findById(employeeId).builder();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable,
            @PathVariable("employeeId") Long employeeId) {
        EmployeeDTO employeeDTO = iEmployeeService.findById(employeeId).builder();
        employeeDTO.setDaysAvailable(daysAvailable);
        iEmployeeService.add(employeeDTO);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForSkill(@RequestBody EmployeeRequestDTO request) {
        return iEmployeeService.findByAvailability(request.getSkills(), request.getDate()).stream().map(
                EmployeeEntity::builder).collect(Collectors.toList());
    }
}
