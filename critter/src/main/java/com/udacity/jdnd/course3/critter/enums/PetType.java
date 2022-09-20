package com.udacity.jdnd.course3.critter.enums;

public enum PetType {
    CAT("CAT"),
    DOG("DOG"),
    BIRD("BIRD"),
    FISH("FISH"),
    SNAKE("SNAKE"),
    LIZARD("LIZARD"),
    OTHER("OTHER"),
    ;

    private final String code;

    private PetType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
