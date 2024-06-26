package com.example.Firstproject.api;

import com.example.Firstproject.DTO.CoffeeDto;
import com.example.Firstproject.entity.Coffee;
import com.example.Firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CoffeeApiController {
    @Autowired
    private CoffeeRepository coffeeRepository;

    // GET
    @GetMapping("/api/coffees")
    public ResponseEntity<List<Coffee>> index(){
        List<Coffee> coffeeList = (List<Coffee>)coffeeRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(coffeeList);
    }
    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id){
        return coffeeRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/coffees")
    public Coffee create(@RequestBody CoffeeDto dto){
        Coffee coffee = dto.toEntity();
        return coffeeRepository.save(coffee);
    }

    // PATCH
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id , @RequestBody CoffeeDto dto){
        // dto -> 엔티티
        Coffee coffee = dto.toEntity();
        // target : 진짜 값이 있는 지 , coffee(id) id 비교
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if (target == null || id != coffee.getId()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // 실제 저장
        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELTE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        // id 값 찾기
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if (target == null || target.getId() != id){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        coffeeRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
