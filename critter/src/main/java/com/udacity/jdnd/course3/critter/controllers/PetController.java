package com.udacity.jdnd.course3.critter.controllers;

import com.udacity.jdnd.course3.critter.dtos.PetDTO;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.services.IPetService;
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
@RequestMapping(path = "/pets")
public class PetController {

    @Autowired
    private IPetService iPetService;

    @PostMapping
    public PetDTO save(@RequestBody PetDTO dto) {
        return iPetService.add(dto).builder();
    }

    @GetMapping("/{petId}")
    public PetDTO findById(@PathVariable Long petId) {
        return iPetService.findByID(petId).builder();
    }

    @GetMapping
    public List<PetDTO> findAll() {
        return iPetService.findAll().stream().map(PetEntity::builder).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> findPetsByOwner(@PathVariable Long ownerId) {
        return iPetService.findByOwnerId(ownerId).stream().map(PetEntity::builder).collect(
                Collectors.toList());
    }
}
