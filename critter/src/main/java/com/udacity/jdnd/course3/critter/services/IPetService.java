package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.dtos.PetDTO;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import java.util.List;

public interface IPetService {

    PetDTO add(PetDTO pet);

    PetDTO findByID(Long petId);

    List<PetDTO> findAll();

    List<PetDTO> findByIds(List<Long> ids);

    List<PetDTO> findByOwnerId(Long ownerId);
}
