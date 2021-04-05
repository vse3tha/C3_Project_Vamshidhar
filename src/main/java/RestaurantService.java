import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName)  throws restaurantNotFoundException{
        for(Restaurant restaurant: restaurants) {
            if(restaurant.getName().equals(restaurantName))
                return restaurant;
        }
        throw new restaurantNotFoundException(restaurantName);
    }

    public int findRestaurantItemsOrderPriceByNameAndItems(String restaurantName, List<String> items) {
        try {
            Restaurant restaurant = findRestaurantByName(restaurantName);
            if(restaurant.getName().equals(restaurantName))
                return restaurant.findItemsToalValueByOrderItems(items);
        }
        catch(restaurantNotFoundException rnte){}
        return 0;
    }

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = null;
        try {
            newRestaurant = findRestaurantByName(name);
            if(newRestaurant!=null)
                return newRestaurant;
        } catch (restaurantNotFoundException e)
        {
            // TODO: handle exception
        }

        newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
