import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    public void restaurant_adding_for_testing(){
        restaurant = service.addRestaurant("Aromas Veg Cafe Resto","Patna",LocalTime.parse("09:30:00"),LocalTime.parse("23:30:00"));
        service.addRestaurant("Express Dosa Wala","Patna",LocalTime.parse("09:30:00"),LocalTime.parse("23:30:00"));
        restaurant.addToMenu("Veg Fried Rice",85);
        restaurant.addToMenu("Veg Hakka Noodles", 60);
    }


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        restaurant_adding_for_testing();
        assertEquals("Aromas Veg Cafe Resto",service.findRestaurantByName("Aromas Veg Cafe").getName());
    }

    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        restaurant_adding_for_testing();
        assertThrows(restaurantNotFoundException.class,()->{
            service.findRestaurantByName("Aromas Veg Cafe");
    });
}
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("09:30:00");
        LocalTime closingTime = LocalTime.parse("23:30:00");
        restaurant = service.addRestaurant("Aromas Veg Cafe","Patna",openingTime,closingTime);
        restaurant.addToMenu("Veg Fried Rice",85);
        restaurant.addToMenu("Veg Hakka Noodles", 60);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Aromas Veg Cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("09:30:00");
        LocalTime closingTime = LocalTime.parse("23:30:00");
        restaurant = service.addRestaurant("Aromas Veg Cafe","Patna",openingTime,closingTime);
        restaurant.addToMenu("Veg Fried Rice",85);
        restaurant.addToMenu("Veg Hakka Noodles", 60);

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Express Dpsa Wala"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        LocalTime openingTime = LocalTime.parse("09:30:00");
        LocalTime closingTime = LocalTime.parse("23:30:00");
        restaurant = service.addRestaurant("Aromas Veg Cafe","Patna",openingTime,closingTime);
        restaurant.addToMenu("Veg Fried Rice",85);
        restaurant.addToMenu("Veg Hakka Noodles", 60);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Momos Stall","Patna",LocalTime.parse("10:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}