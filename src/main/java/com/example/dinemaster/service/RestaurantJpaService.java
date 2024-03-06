/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * 
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.dinemaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

import com.example.dinemaster.model.Restaurant;

import com.example.dinemaster.repository.RestaurantJpaRepository;
import com.example.dinemaster.repository.RestaurantRepository;

@Service
public class RestaurantJpaService implements RestaurantRepository {
    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @Override
    public ArrayList<Restaurant> getRestaurants() {
        List<Restaurant> restaurantsList = restaurantJpaRepository.findAll();
        ArrayList<Restaurant> restaurants = new ArrayList<>(restaurantsList);
        return restaurants;
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        try {
            Restaurant Restaurant = restaurantJpaRepository.findById(restaurantId).get();
            return Restaurant;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        restaurantJpaRepository.save(restaurant);
        return restaurant;
    }

    @Override
    public Restaurant updateRestaurant(int restaurantId, Restaurant restaurant) {
        try {
            Restaurant newRestaurant = restaurantJpaRepository.findById(restaurantId).get();
            if (restaurant.getName() != null) {
                newRestaurant.setName(restaurant.getName());
            }
            if (restaurant.getAddress() != null) {
                newRestaurant.setAddress(restaurant.getAddress());
            }
            if (restaurant.getCuisineType() != null) {
                newRestaurant.setCuisineType(restaurant.getCuisineType());
            }
            if (restaurant.getRating() != 0) {
                newRestaurant.setRating(restaurant.getRating());
            }
            restaurantJpaRepository.save(newRestaurant);
            return newRestaurant;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteRestaurant(int restaurantId) {
        try {
            restaurantJpaRepository.deleteById(restaurantId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}