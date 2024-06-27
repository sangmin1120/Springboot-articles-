package com.example.Firstproject.service;

import com.example.Firstproject.DTO.CoffeeDto;
import com.example.Firstproject.entity.Coffee;
import com.example.Firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;


    // REST의 GET
    public List<Coffee> index() {
        return (List<Coffee>)coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    // REST의 POST
    public Coffee create(CoffeeDto dto) {
        Coffee created = dto.toEntity();
        return coffeeRepository.save(created);
    }

    // REST의 PATCH
    public Coffee update(Long id, CoffeeDto dto) {
        // dto->엔티티
        Coffee coffee = dto.toEntity();

        // target
        Coffee target = coffeeRepository.findById(id).orElse(null);

        // 문제 확인
        if (target == null || id != coffee.getId()){
            log.info("문제가 생김");
            return null;
        }
        // 반환
        Coffee updated = coffeeRepository.save(coffee);
        return updated;
    }

    // REST의 DELETE
    public Coffee delete(Long id) {
        // 일단 찾아
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if (target == null){
            log.info("못 찾음");
            return null;
        }
        coffeeRepository.delete(target);
        return target;
    }
}
