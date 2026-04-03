import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

public class RentalService {
    private final LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();

    public void startRental(String bikeID, String userEmail) {
        LocalDateTime startTime = LocalDateTime.now();
        ActiveRental activeRental = new ActiveRental(bikeID, userEmail, startTime);
        activeRentalsList.add(activeRental);
        ERyderLog log = new ERyderLog(
                bikeID,
                "Trip started by user: " + userEmail,
                LocalDateTime.now()
        );
        BikeService.logStack.push(log);

        System.out.println("Reserving the bike with the " + bikeID + ". Please follow the on-screen instructions to locate the bike.");
    }

    public void endRental(String bikeID) {
        Iterator<ActiveRental> it = activeRentalsList.iterator();
        while (it.hasNext()) {
            ActiveRental ar = it.next();
            if (ar.getBikeID().equals(bikeID)) {
                it.remove();
                break;
            }
        }
        System.out.println("Your trip has ended. Thank you for riding with us.");
    }

    public void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals at the moment.");
        } else {
            for (ActiveRental ar : activeRentalsList) {
                System.out.println(ar);
            }
        }
    }
}