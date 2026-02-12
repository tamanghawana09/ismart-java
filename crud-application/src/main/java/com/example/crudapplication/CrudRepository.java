package com.example.crudapplication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudRepository extends JpaRepository<Details, Integer> {
}
