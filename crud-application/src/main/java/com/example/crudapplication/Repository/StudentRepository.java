package com.example.crudapplication.Repository;

import com.example.crudapplication.Entities.Students;
import com.example.crudapplication.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Students,Integer> {
    List<Students> findByUserUsername(String username);
    List<Students> findByUserId(Long userId);
    List<Students> findByUser(Users user);

}

