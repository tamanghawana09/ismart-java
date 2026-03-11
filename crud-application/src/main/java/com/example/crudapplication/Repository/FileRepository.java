package com.example.crudapplication.Repository;

import com.example.crudapplication.Entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Students,Integer> {
}
