package com.example.crudapplication.Repository;

import com.example.crudapplication.Entities.Details;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudRepository extends JpaRepository<Details, Integer> {
}
