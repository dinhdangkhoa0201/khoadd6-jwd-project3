package com.udacity.jdnd.course3.critter.services.impls;

import com.udacity.jdnd.course3.critter.dtos.PetDTO;
import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.services.IPetService;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PetService implements IPetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public PetEntity add(PetDTO pet) {
        PetEntity entity = new PetEntity();
        CustomerEntity customer = customerRepository.getOne(pet.getOwnerId());
        entity.setName(pet.getName());
        entity.setBirthDate(pet.getBirthDate());
        entity.setType(pet.getType());
        entity.setNotes(pet.getNotes());
        entity.setCustomer(customer);
        entity = petRepository.save(entity);
        customer.getPets().add(entity);
        customerRepository.save(customer);
        return entity;
    }

    @Override
    public PetEntity findByID(Long petId) {
        return petRepository.getOne(petId);
    }

    @Override
    public List<PetEntity> findAll() {
        return petRepository.findAll();
    }

    @Override
    public List<PetEntity> findByIds(List<Long> ids) {
        return petRepository.findByIdIn(ids);
    }

    @Override
    public List<PetEntity> findByOwnerId(Long ownerId) {
        return petRepository.findByOwner(ownerId);
    }
}
