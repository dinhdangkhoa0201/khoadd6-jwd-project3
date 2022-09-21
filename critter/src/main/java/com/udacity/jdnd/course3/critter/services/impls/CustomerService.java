package com.udacity.jdnd.course3.critter.services.impls;

import com.udacity.jdnd.course3.critter.dtos.CustomerDTO;
import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.services.ICustomerService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    @Override
    public List<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity add(CustomerDTO customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setName(customer.getName());
        entity.setPhoneNumber(customer.getPhoneNumber());
        entity.setNotes(customer.getNotes());
        List<PetEntity> listPet = new ArrayList<>();
        List<Long> listPetId = customer.getPetIds();
        if (!CollectionUtils.isEmpty(listPetId)) {
            listPetId.forEach(e -> {
                Optional<PetEntity> petEntity = petRepository.findById(e);
                petEntity.ifPresent(listPet::add);
            });
        }
        entity.setPets(listPet);
        return customerRepository.save(entity);
    }

    @Override
    public CustomerEntity findByPetId(Long petId) {
        CustomerEntity entity = petRepository.getOne(petId).getCustomer();
        entity.getPets();
        return entity;
    }
}
