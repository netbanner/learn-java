package com.zwh.springboot;

public class PersonNotFoundException extends  RuntimeException {

    private Long personId;

    public Long getPersonId(){
        return personId;
    }

    public PersonNotFoundException(Long personId){
        this.personId = personId;
    }
}
