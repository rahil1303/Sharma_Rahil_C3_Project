package com.RestaurantFinder;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args){

        //these are for testing purpose only...

        LocalTime openingTime = LocalTime.parse("9:00:00");
        LocalTime closingTime = LocalTime.parse("23:30:00");
        Restaurant restaurant = new Restaurant("Aromas Veg Cafe Resto","Patna",openingTime,closingTime);
        restaurant.addToMenu("Veg Burger",99);
        restaurant.addToMenu("Veg Sandwich",150);
    }
}