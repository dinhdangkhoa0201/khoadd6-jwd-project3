package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.dtos.CustomerDTO;
import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import java.util.List;

public interface ICustomerService {

    List<CustomerEntity> findAll();

    CustomerEntity add(CustomerDTO customer);

    CustomerEntity findByPetId(Long petId);

}
