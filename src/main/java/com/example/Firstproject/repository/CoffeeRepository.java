package com.example.Firstproject.repository;

import com.example.Firstproject.entity.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee,Long> {
}
