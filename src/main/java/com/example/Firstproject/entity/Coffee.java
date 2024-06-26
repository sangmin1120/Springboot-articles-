package com.example.Firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String price;

    public void patch(Coffee coffee) {
        if (coffee.getName() != null){
            this.name = coffee.getName();
        }
        if (coffee.getPrice() != null){
            this.price = coffee.getPrice();
        }
    }
}
