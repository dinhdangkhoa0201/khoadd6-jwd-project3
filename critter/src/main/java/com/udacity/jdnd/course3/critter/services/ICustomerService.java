package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.dtos.CustomerDTO;
import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import java.util.List;

public interface ICustomerService {

    List<CustomerDTO> findAll();

    CustomerDTO add(CustomerDTO customer);

    CustomerDTO findByPetId(Long petId);

}
