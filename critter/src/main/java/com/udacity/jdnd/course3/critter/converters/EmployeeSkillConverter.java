package com.udacity.jdnd.course3.critter.converters;

import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EmployeeSkillConverter implements AttributeConverter<Set<EmployeeSkill>, String> {

    @Override
    public String convertToDatabaseColumn(Set<EmployeeSkill> employeeSkills) {
        if (employeeSkills == null || employeeSkills.isEmpty()) {
            return null;
        }
        Set<String> asString = employeeSkills.stream().map(EmployeeSkill::getCode).collect(
                Collectors.toSet());
        return String.join(", ", asString);
    }

    @Override
    public Set<EmployeeSkill> convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        Set<String> asString = Arrays.stream(s.split("\\s*, \\s*")).collect(Collectors.toSet());
        Set<EmployeeSkill> asEnum = new HashSet<>();
        for (String code : asString) {
            asEnum.add(EmployeeSkill.valueOf(code.trim()));
        }
        return asEnum;
    }
}
