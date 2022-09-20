package com.udacity.jdnd.course3.critter.dtos;

import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import java.time.DayOfWeek;
import java.util.Set;

public class EmployeeDTO {

    private Long id;

    private String name;

    private Set<EmployeeSkill> skills;

    private Set<DayOfWeek> daysAvailable;

    public EmployeeDTO(EmployeeEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.skills = entity.getSkills();
        this.daysAvailable = entity.getDaysAvailable();
    }

    public EmployeeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
