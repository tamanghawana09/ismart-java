package com.example.crudapplication.Repository;

import com.example.crudapplication.Entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Students,Integer> {
    List<Students> findByUserUsername(String username);
}
