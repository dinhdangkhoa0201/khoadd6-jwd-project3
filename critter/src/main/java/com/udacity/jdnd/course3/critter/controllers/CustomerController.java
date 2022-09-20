package com.udacity.jdnd.course3.critter.controllers;

import com.udacity.jdnd.course3.critter.dtos.CustomerDTO;
import com.udacity.jdnd.course3.critter.services.ICustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @PostMapping("")
    public CustomerDTO save(@RequestBody CustomerDTO dto) {
        return iCustomerService.add(dto);
    }

    @GetMapping
    public List<CustomerDTO> findAll() {
        return iCustomerService.findAll();
    }

    @GetMapping(path = "/pets/{petId}")
    public CustomerDTO findOwnerByPet(@PathVariable("petId") Long petId) {
        return iCustomerService.findByPetId(petId);
    }
}
