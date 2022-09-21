package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.dtos.CustomerDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "notes")
    private String notes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<PetEntity> pets;

    public CustomerDTO builder() {
        return CustomerDTO.builder()
                .id(this.getId())
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .petIds(!CollectionUtils.isEmpty(pets) ? this.pets.stream().map(PetEntity::getId)
                        .collect(
                                Collectors.toList()) : new ArrayList<>())
                .build();
    }
}
