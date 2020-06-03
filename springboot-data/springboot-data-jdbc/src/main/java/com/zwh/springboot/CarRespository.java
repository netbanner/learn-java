package com.zwh.springboot;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRespository {

    private JdbcTemplate jdbcTemplate;

    public CarRespository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Car> findByMake(String make){

        return this.jdbcTemplate.
                query("select * from car where make = ? order by id",(rs,i)->new Car(rs.getString("make"),rs.getString("model"),rs.getInt("id")),make);
    }
}
