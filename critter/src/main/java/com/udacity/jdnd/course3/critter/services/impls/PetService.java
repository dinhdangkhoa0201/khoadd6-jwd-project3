package com.udacity.jdnd.course3.critter.services.impls;

import com.udacity.jdnd.course3.critter.dtos.PetDTO;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.services.IPetService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService implements IPetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public PetDTO add(PetDTO pet) {
        PetEntity entity = new PetEntity(pet);
        entity = petRepository.save(entity);
        PetDTO dto = new PetDTO(entity);
        return dto;
    }

    @Override
    public PetDTO findByID(Long petId) {
        Optional<PetEntity> optional = petRepository.findById(petId);
        PetDTO dto = null;
        if (optional.isPresent()) {
            dto = new PetDTO(optional.get());
        }
        return dto;
    }

    @Override
    public List<PetDTO> findAll() {
        List<PetEntity> listEntity = petRepository.findAll();
        List<PetDTO> listDTO = new ArrayList<>();
        if (!listEntity.isEmpty()) {
            listEntity.forEach(e -> {
                listDTO.add(new PetDTO(e));
            });
        }
        return listDTO;
    }

    @Override
    public List<PetDTO> findByIds(List<Long> ids) {
        List<PetEntity> listEntity = petRepository.findByIdIn(ids);
        List<PetDTO> listDTO = new ArrayList<>();
        if (!listEntity.isEmpty()) {
            listEntity.forEach(e -> {
                listDTO.add(new PetDTO(e));
            });
        }
        return listDTO;
    }

    @Override
    public List<PetDTO> findByOwnerId(Long ownerId) {
        List<PetEntity> listEntity = petRepository.findByOwner(ownerId);
        List<PetDTO> listDTO = new ArrayList<>();
        if (!listEntity.isEmpty()) {
            listEntity.forEach(e -> {
                listDTO.add(new PetDTO(e));
            });
        }
        return listDTO;
    }
}
