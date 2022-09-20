package com.udacity.jdnd.course3.critter.dtos;

import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

public class CustomerDTO {

    private Long id;

    private String name;

    private String phoneNumber;

    private String notes;

    private List<Long> petIds;

    public CustomerDTO(CustomerEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.phoneNumber = entity.getPhoneNumber();
        this.notes = entity.getNotes();
        this.petIds = !CollectionUtils.isEmpty(entity.getPets()) ? entity.getPets().stream()
                .map(PetEntity::getId)
                .collect(Collectors.toList())
                : new ArrayList<>();
    }

    public CustomerDTO(Long id) {
        this.id = id;
    }

    public CustomerDTO() {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }
}
