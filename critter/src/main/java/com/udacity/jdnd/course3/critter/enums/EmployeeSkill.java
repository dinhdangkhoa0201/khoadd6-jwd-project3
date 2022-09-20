package com.udacity.jdnd.course3.critter.enums;

public enum EmployeeSkill {
    PETTING("PETTING"),
    WALKING("WALKING"),
    FEEDING("FEEDING"),
    MEDICATING("MEDICATING"),
    SHAVING("SHAVING"),
    ;

    private final String code;

    private EmployeeSkill(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
