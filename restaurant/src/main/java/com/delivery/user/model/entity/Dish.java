package com.delivery.user.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "menu", schema = "delivery")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dish
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private Integer dishId;

    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Column(name = "category")
    private String category;

    @Column(name = "veg_non_veg")
    private String vegNonVeg;

    @Column(name = "address")
    private String address;

    @Column(name = "price")
    private Double price;
}
