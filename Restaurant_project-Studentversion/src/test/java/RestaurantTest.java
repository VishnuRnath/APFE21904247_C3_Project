import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    private void createRestaurantAndMenu() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        LocalTime openingTime = LocalTime.now().minus(1, ChronoUnit.HOURS);
        LocalTime closingTime = LocalTime.now().plus(1, ChronoUnit.HOURS);

        Restaurant r = new Restaurant("Resto Cafe", "Pune", openingTime, closingTime);
        assertTrue(r.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        LocalTime openingTime = LocalTime.now().plus(1, ChronoUnit.HOURS);
        LocalTime closingTime = LocalTime.now().plus(3, ChronoUnit.HOURS);

        Restaurant r = new Restaurant("Resto Cafe", "Pune", openingTime, closingTime);
        assertFalse(r.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<CALCULATE MENU PRICE>>>>>>>>>>>>>>>>>>>>
    @Test
    public void calculate_menu_price_with_menu_list_with_one_item_in_menu_selected_must_return_amount(){
        createRestaurantAndMenu();

        List<String> selectedItems = new ArrayList<String>();
        selectedItems.add("Vegetable lasagne");

        int price = 269;
        assertEquals(restaurant.getOrderValue(selectedItems),price);
    }



    @Test
    public void calculate_menu_price_with_menu_list_with_no_item_selected_must_return_zero(){

        createRestaurantAndMenu();

        List<String> selectedItems = new ArrayList<String>();

        int price = 0;
        assertEquals(restaurant.getOrderValue(selectedItems),price);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        createRestaurantAndMenu();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }



    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        createRestaurantAndMenu();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        createRestaurantAndMenu();

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}