package com.example.reacts.repository;

import com.example.reacts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhuwh
 * @date 2019/3/8 16:48
 * @desc
 */
public interface UserRepository extends JpaRepository<User, String> {
}
