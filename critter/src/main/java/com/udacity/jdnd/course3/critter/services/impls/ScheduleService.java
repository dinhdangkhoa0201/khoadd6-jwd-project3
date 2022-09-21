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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
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
    public ScheduleEntity add(ScheduleDTO schedule) {
        ScheduleEntity entity = new ScheduleEntity();
        List<PetEntity> listPet = petRepository.findAllById(schedule.getPetIds());
        List<EmployeeEntity> listEmployee = employeeRepository.findAllById(schedule.getEmployeeIds());
        entity.setDate(schedule.getDelivery());
        entity.setEmployees(listEmployee);
        entity.setPets(listPet);
        entity.setActivities(schedule.getActivities());
        entity = scheduleRepository.save(entity);
        return entity;
    }

    @Override
    public List<ScheduleEntity> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<ScheduleEntity> findByPet(Long petId) {
        return scheduleRepository.findByPetsContaining(
                petRepository.getOne(petId));
    }

    @Override
    public List<ScheduleEntity> findByCustomer(Long customerId) {
        List<PetEntity> listPet = petRepository.findByOwner(customerId);
        List<ScheduleEntity> listEntity = new ArrayList<>();
        for (PetEntity pet : listPet) {
            listEntity.addAll(scheduleRepository.findByPetsContaining(pet));
        }
        return listEntity;
    }

    @Override
    public List<ScheduleEntity> findByEmployee(Long employeeId) {
        List<ScheduleEntity> listEntity = scheduleRepository.findByEmployeesContaining(
                employeeRepository.getOne(employeeId));
        return listEntity;
    }
}
