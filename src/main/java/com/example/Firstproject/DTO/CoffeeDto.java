package com.example.Firstproject.DTO;

import com.example.Firstproject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class CoffeeDto {
    private Long id;
        private String name;
    private String price;

    public Coffee toEntity(){
        return new Coffee(id,name,price);
    }
}
