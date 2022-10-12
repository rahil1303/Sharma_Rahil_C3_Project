import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    List<Item> spoof = new ArrayList<Item>();
    public void restaurantCreation(){
        LocalTime openingTime = LocalTime.parse("09:30:00");
        LocalTime closingTime = LocalTime.parse("23:30:00");
        restaurant = new Restaurant("Aromas Veg Cafe","Patna",openingTime,closingTime);
        restaurant.addToMenu("Veg Fried Rice",85);
        restaurant.addToMenu("Veg Hakka Noodles", 60);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        restaurantCreation();
        restaurant.setClosingTime(LocalTime.now().plusMinutes(10));
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        restaurantCreation();
        restaurant.setClosingTime(LocalTime.now().minusMinutes(10));
        assertFalse(restaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("09:30:00");
        LocalTime closingTime = LocalTime.parse("23:30:00");
        restaurant =new Restaurant("Aromas Veg Cafe","Patna",openingTime,closingTime);
        restaurant.addToMenu("Veg Fried Rice",85);
        restaurant.addToMenu("Veg Hakka Noodles", 60);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Chocolate Ice Cream",220);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("09:30:00");
        LocalTime closingTime = LocalTime.parse("23:30:00");
        restaurant =new Restaurant("Aromas Veg Cafe","Patna",openingTime,closingTime);
        restaurant.addToMenu("Veg Fried Rice",85);
        restaurant.addToMenu("Veg Hakka Noodles", 60);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Veg Hakka Noodles");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("09:30:00");
        LocalTime closingTime = LocalTime.parse("23:30:00");
        restaurant =new Restaurant("Aromas Veg Cafe","Patna",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",85);
        restaurant.addToMenu("Vegetable lasagne", 60);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("Butter Chicken"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}