package com.zwh.springboot;

public class Person {

    private Long id;

    private String name;

    private String email;

    Person(){}

    public Person(String name,String email){
        this.email = email;
        this.name = name;
    }

    public Long getId(){
        return this.id;

    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    @Override
    public String toString(){
        return  "Person{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email
                + '\'' + '}';
    }

}
