package com.udacity.jdnd.course3.critter.services.impls;

import com.udacity.jdnd.course3.critter.dtos.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.entities.ScheduleEntity;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import com.udacity.jdnd.course3.critter.services.IScheduleService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ScheduleDTO add(ScheduleDTO schedule) {
        ScheduleEntity entity = new ScheduleEntity(schedule);
        entity.setEmployees(employeeRepository.findByIdIn(schedule.getEmployeeIds()));
        entity.setPets(petRepository.findByIdIn(schedule.getPetIds()));
        entity = scheduleRepository.save(entity);
        ScheduleDTO dto = new ScheduleDTO(entity);
        return dto;
    }

    @Override
    public List<ScheduleDTO> findAll() {
        List<ScheduleEntity> listEntity = scheduleRepository.findAll();
        List<ScheduleDTO> listDTO = new ArrayList<>();
        if (!listEntity.isEmpty()) {
            listEntity.forEach(e -> {
                e.setEmployees(employeeRepository.findByIdIn(e.getEmployees().stream().map(
                        EmployeeEntity::getId).collect(Collectors.toList())));
                listDTO.add(new ScheduleDTO(e));
            });
        }
        return listDTO;
    }

    @Override
    public List<ScheduleDTO> findByPet(Long petId) {
        List<ScheduleEntity> listEntity = scheduleRepository.findByPetsContaining(
                petRepository.getOne(petId));
        List<ScheduleDTO> listDTO = new ArrayList<>();
        if (listEntity != null && !listEntity.isEmpty()) {
            listEntity.forEach(e -> {
                listDTO.add(new ScheduleDTO(e));
            });
        }
        return listDTO;
    }

    @Override
    public List<ScheduleDTO> findByCustomer(Long customerId) {
        List<ScheduleDTO> listDTO = new ArrayList<>();
        List<PetEntity> listPet = petRepository.findByOwner(customerId);
        List<ScheduleEntity> listEntity = new ArrayList<>();
        for (PetEntity pet : listPet) {
            listEntity.addAll(scheduleRepository.findByPetsContaining(pet));
        }
        if (!listEntity.isEmpty()) {
            listDTO.addAll(listEntity.stream().map(e -> new ScheduleDTO(e)).collect(Collectors.toList()));
        }
        return listDTO;
    }

    @Override
    public List<ScheduleDTO> findByEmployee(Long employeeId) {
        List<ScheduleEntity> listEntity = scheduleRepository.findByEmployeesContaining(employeeRepository.getOne(employeeId));
        List<ScheduleDTO> listDTO = new ArrayList<>();
        if (listEntity != null && !listEntity.isEmpty()) {
            listEntity.forEach(e -> {
                listDTO.add(new ScheduleDTO(e));
            });
        }
        return listDTO;
    }
}
