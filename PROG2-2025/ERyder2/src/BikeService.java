import java.time.LocalDateTime;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeque;

public class BikeService {
    public static Stack<ERyderLog> logStack = new Stack<>();
    public static Queue<BikeRequest> bikeRequestQueue = new ArrayDeque<>();
    public String validateLocation(String location) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equalsIgnoreCase(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        return null;
    }

    public boolean reserveBike(String bikeID) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(false);
                bike.setLastUsedTime(LocalDateTime.now());
                ERyderLog log = new ERyderLog(
                        bikeID,
                        "Bike rented successfully",
                        LocalDateTime.now()
                );
                logStack.push(log);
                return true;
            }
        }
        return false;
    }
    public void addToRequestQueue(String userEmail, String location) {
        BikeRequest request = new BikeRequest(userEmail, location, LocalDateTime.now());
        bikeRequestQueue.add(request);
        System.out.println("You have been added to the waiting queue.");
    }

    public void releaseBike(String bikeID) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                ERyderLog log = new ERyderLog(
                        bikeID,
                        "Trip ended, bike released",
                        LocalDateTime.now()
                );
                logStack.push(log);
                break;
            }
        }
    }
    public void viewSystemLogs() {
        System.out.println("\n===== SYSTEM LOGS (STACK) =====");
        for (ERyderLog log : logStack) {
            System.out.println(log);
        }
    }

    public List<Bike> getAllBikes() {
        return BikeDatabase.bikes;
    }
}