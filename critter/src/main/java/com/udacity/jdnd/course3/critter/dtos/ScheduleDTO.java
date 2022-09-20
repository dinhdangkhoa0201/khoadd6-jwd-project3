package com.udacity.jdnd.course3.critter.dtos;

import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.entities.ScheduleEntity;
import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ScheduleDTO {

    private Long id;

    private LocalDate delivery;

    private List<Long> petIds;

    private List<Long> employeeIds;

    private Set<EmployeeSkill> activities;

    public ScheduleDTO(ScheduleEntity entity) {
        this.id = entity.getId();
        this.delivery = entity.getDeliveryDate();
        this.petIds = entity.getPets().stream().map(PetEntity::getId).collect(Collectors.toList());
        this.employeeIds = entity.getEmployees().stream().map(EmployeeEntity::getId).collect(
                Collectors.toList());
        this.activities = entity.getActivities();
    }

    public ScheduleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDelivery() {
        return delivery;
    }

    public void setDelivery(LocalDate delivery) {
        this.delivery = delivery;
    }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(
            List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
