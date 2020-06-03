package com.zwh.springboot;

import org.springframework.data.repository.CrudRepository;

public interface CarRepository  extends CrudRepository<Car,Long>{


    Iterable<Car> findByMake(String make);
}
