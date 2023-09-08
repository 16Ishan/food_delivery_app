package com.delivery.user.services;

import com.delivery.user.model.entity.Restaurant;

import java.util.List;

public interface RestaurantService
{
    List<Restaurant> getAllRestaurants();
    List<Restaurant> getRestaurant(String keyword);
}
