package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.converters.EmployeeSkillConverter;
import com.udacity.jdnd.course3.critter.dtos.ScheduleDTO;
import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "schedules_pets",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id")
    )
    private List<PetEntity> pets;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "schedules_employees",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<EmployeeEntity> employees;

    @Convert(converter = EmployeeSkillConverter.class)
    private Set<EmployeeSkill> activities;

    public ScheduleDTO builder() {
        return ScheduleDTO.builder()
                .id(this.id)
                .delivery(this.date)
                .petIds(!CollectionUtils.isEmpty(this.pets) ? this.pets.stream()
                        .map(PetEntity::getId).collect(
                                Collectors.toList()) : new ArrayList<>())
                .employeeIds(!CollectionUtils.isEmpty(this.employees) ? this.employees.stream()
                        .map(EmployeeEntity::getId).collect(
                                Collectors.toList()) : new ArrayList<>())
                .activities(this.activities)
                .build();
    }
}
