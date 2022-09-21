package com.udacity.jdnd.course3.critter.controllers;

import com.udacity.jdnd.course3.critter.dtos.CustomerDTO;
import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.services.ICustomerService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @PostMapping("/customer")
    public CustomerDTO save(@RequestBody CustomerDTO dto) {
        return iCustomerService.add(dto).builder();
    }

    @GetMapping("/customer")
    public List<CustomerDTO> findAll() {
        return iCustomerService.findAll().stream().map(CustomerEntity::builder).collect(Collectors.toList());
    }

    @GetMapping(path = "/customer/pet/{petId}")
    public CustomerDTO findOwnerByPet(@PathVariable("petId") Long petId) {
        return iCustomerService.findByPetId(petId).builder();
    }
}
