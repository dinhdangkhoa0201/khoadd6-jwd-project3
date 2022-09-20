package com.udacity.jdnd.course3.critter.services.impls;

import com.udacity.jdnd.course3.critter.dtos.CustomerDTO;
import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.services.ICustomerService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    @Override
    public List<CustomerDTO> findAll() {
        List<CustomerDTO> listDTO = new ArrayList<>();
        List<CustomerEntity> listEntity = customerRepository.findAll();
        if (!listEntity.isEmpty()) {
            listEntity.forEach(e -> {
                e.setPets(petRepository.findByOwner(e.getId()));
                listDTO.add(new CustomerDTO(e));
            });
        }
        return listDTO;
    }

    @Override
    public CustomerDTO add(CustomerDTO customer) {
        CustomerEntity entity = new CustomerEntity(customer);
        entity = customerRepository.save(entity);
        return new CustomerDTO(entity);
    }

    @Override
    public CustomerDTO findByPetId(Long petId) {
        CustomerDTO dto = new CustomerDTO(customerRepository.findByPet(petId));
        List<PetEntity> listEntity = petRepository.findByOwner(dto.getId());
        if (!listEntity.isEmpty()) {
            dto.setPetIds(listEntity.stream().map(PetEntity::getId).collect(Collectors.toList()));
        }
        return dto;
    }
}
