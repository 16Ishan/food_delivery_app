package com.delivery.user.services.impl;

import com.delivery.user.model.entity.Restaurant;
import com.delivery.user.repositories.RestaurantRepository;
import com.delivery.user.services.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Override
    public List<Restaurant> getAllRestaurants()
    {
        List<Restaurant> restaurantList = new ArrayList<>();
        try {
            restaurantList = restaurantRepository.findAll();
            restaurantList.sort(Comparator.comparing(Restaurant::getRestaurantName));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return restaurantList;
    }

    @Override
    public List<Restaurant> getRestaurant(String keyword) {
        return null;
    }
}
