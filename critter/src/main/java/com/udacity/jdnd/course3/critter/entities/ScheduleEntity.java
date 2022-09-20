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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.util.CollectionUtils;

@Entity
@Table(name = "schedules")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "schedules_pets",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id")
    )
    private List<PetEntity> pets;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "schedules_employees",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<EmployeeEntity> employees;

    @Column(name = "activities")
    @Convert(converter = EmployeeSkillConverter.class)
    private Set<EmployeeSkill> activities;

    public ScheduleEntity(ScheduleDTO dto) {
        this.id = dto.getId();
        this.deliveryDate = dto.getDelivery();
        this.pets = !CollectionUtils.isEmpty(dto.getPetIds()) ? dto.getPetIds().stream()
                .map(PetEntity::new).collect(Collectors.toList()) : new ArrayList<>();
        this.employees =
                !CollectionUtils.isEmpty(dto.getEmployeeIds()) ? dto.getEmployeeIds().stream()
                        .map(EmployeeEntity::new).collect(Collectors.toList())
                        : new ArrayList<>();
        this.activities = dto.getActivities();
    }

    public ScheduleEntity(Long id) {
        this.id = id;
    }

    public ScheduleEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<PetEntity> getPets() {
        return pets;
    }

    public void setPets(List<PetEntity> pets) {
        this.pets = pets;
    }

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(
            List<EmployeeEntity> employees) {
        this.employees = employees;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
