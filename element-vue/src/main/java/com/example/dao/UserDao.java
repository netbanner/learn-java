package com.example.dao;

import com.example.beans.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User,Long> {

    User findByName(String name);
}
