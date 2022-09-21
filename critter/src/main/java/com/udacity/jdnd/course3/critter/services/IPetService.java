package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.dtos.PetDTO;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import java.util.List;

public interface IPetService {

    PetEntity add(PetDTO pet);

    PetEntity findByID(Long petId);

    List<PetEntity> findAll();

    List<PetEntity> findByIds(List<Long> ids);

    List<PetEntity> findByOwnerId(Long ownerId);
}
