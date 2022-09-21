package com.udacity.jdnd.course3.critter.services.impls;

import com.udacity.jdnd.course3.critter.dtos.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.services.IEmployeeService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeService implements IEmployeeService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeEntity add(EmployeeDTO dto) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSkills(dto.getSkills());
        entity.setDaysAvailable(dto.getDaysAvailable());
        return employeeRepository.save(entity);
    }

    @Override
    public EmployeeEntity findById(Long employeeId) {
        return employeeRepository.getOne(employeeId);
    }

    @Override
    public List<EmployeeEntity> findByIds(List<Long> ids) {
        return employeeRepository.findByIdIn(ids);
    }

    @Override
    public List<EmployeeEntity> findByAvailability(Set<EmployeeSkill> skills, LocalDate date) {
        List<EmployeeEntity> listEntity = employeeRepository.findByDaysAvailableContaining(
                date.getDayOfWeek());
        List<EmployeeEntity> listAvailable = new ArrayList<>();
        listEntity.forEach(e -> {
            if (e.getSkills().containsAll(skills)) {
                listAvailable.add(e);
            }
        });
        return listAvailable;
    }
}
