package com.udacity.jdnd.course3.critter.services.impls;

import com.udacity.jdnd.course3.critter.dtos.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.services.IEmployeeService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO add(EmployeeDTO dto) {
        EmployeeEntity entity = new EmployeeEntity(dto);
        entity = employeeRepository.save(entity);
        return new EmployeeDTO(entity);
    }

    @Override
    public EmployeeDTO findById(Long employeeId) {
        Optional<EmployeeEntity> optional = employeeRepository.findById(employeeId);
        EmployeeDTO dto = null;
        if (optional.isPresent()) {
            dto = new EmployeeDTO(optional.get());
        }
        return dto;
    }

    @Override
    public List<EmployeeDTO> findByIds(List<Long> ids) {
        List<EmployeeEntity> listEntity = employeeRepository.findByIdIn(ids);
        List<EmployeeDTO> listDTO = new ArrayList<>();
        if (listEntity.isEmpty()) {
            listEntity.forEach(e -> {
                listDTO.add(new EmployeeDTO(e));
            });
        }
        return listDTO;
    }

    @Override
    public List<EmployeeDTO> findByAvailability(Set<EmployeeSkill> skills, LocalDate date) {
        List<EmployeeEntity> listEntity = employeeRepository.findByDaysAvailableContaining(date.getDayOfWeek());
        List<EmployeeDTO> listDTO = new ArrayList<>();
        if (listEntity != null && !listEntity.isEmpty()) {
            listEntity.forEach(e -> {
                if (e.getSkills().containsAll(skills)) {
                    listDTO.add(new EmployeeDTO(e));
                }
            });
        }
        return listDTO;
    }

    private List<String> listOfString(Set<EmployeeSkill> skills) {
        if (skills == null || skills.isEmpty()) {
            return Collections.singletonList("");
        }
        return skills.stream()
                .map(EmployeeSkill::name)
                .collect(Collectors.toList());
    }
}
