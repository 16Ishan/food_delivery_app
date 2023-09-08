package com.delivery.user.services;

import com.delivery.user.model.entity.Dish;

import java.util.List;

public interface MenuService
{
    List<Dish> getMenu(Integer restaurantId);
    void addDish(Dish dish);
}
