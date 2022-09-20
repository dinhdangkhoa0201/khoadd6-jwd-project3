package com.udacity.jdnd.course3.critter.converters;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DayOfWeekConverter implements AttributeConverter<Set<DayOfWeek>, String> {

    @Override
    public String convertToDatabaseColumn(Set<DayOfWeek> dayOfWeeks) {
        if (dayOfWeeks == null || dayOfWeeks.isEmpty()) {
            return "";
        }

        Set<String> asString = dayOfWeeks.stream().map(DayOfWeek::name).collect(Collectors.toSet());
        return String.join(", ", asString);
    }

    @Override
    public Set<DayOfWeek> convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        Set<String> asStrings = Arrays.stream(s.split("\\s*,\\s*")).collect(Collectors.toSet());
        Set<DayOfWeek> asEnum = new HashSet<>();
        for (String code : asStrings) {
            asEnum.add(DayOfWeek.valueOf(code));
        }
        return asEnum;
    }
}
