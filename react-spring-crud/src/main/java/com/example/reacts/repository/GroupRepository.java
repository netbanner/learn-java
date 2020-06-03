package com.example.reacts.repository;

import com.example.reacts.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhuwh
 * @date 2019/3/8 15:54
 * @desc
 */
public interface GroupRepository extends JpaRepository<Group, Long> {

   Group findByName(String name);
    List<Group> findAllByUserId(String id);
}
