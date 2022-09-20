package com.udacity.jdnd.course3.critter.controllers;

import com.udacity.jdnd.course3.critter.dtos.ScheduleDTO;
import com.udacity.jdnd.course3.critter.services.IScheduleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/schedules")
public class ScheduleController {

    @Autowired
    private IScheduleService iScheduleService;

    @GetMapping
    public List<ScheduleDTO> findAll() {
        return iScheduleService.findAll();
    }

    @PostMapping
    public ScheduleDTO save(@RequestBody ScheduleDTO dto) {
        return iScheduleService.add(dto);
    }

    @GetMapping(path = "/pets/{petId}")
    public List<ScheduleDTO> findScheduleForPet(@PathVariable Long petId) {
        return iScheduleService.findByPet(petId);
    }

    @GetMapping(path = "/employees/{employeeId}")
    public List<ScheduleDTO> findScheduleForEmployee(@PathVariable Long employeeId) {
        return iScheduleService.findByEmployee(employeeId);
    }

    @GetMapping(path = "/customer/{customerId}")
    public List<ScheduleDTO> findScheduleForCustomer(@PathVariable Long customerId) {
        return iScheduleService.findByCustomer(customerId);
    }
}