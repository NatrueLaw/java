import java.util.Scanner;

public class BikeRental {
    private final BikeService bikeService = new BikeService();
    private final RentalService rentalService = new RentalService();
    private final UserRegistration userRegistration = new UserRegistration();
    private final UserService userService = new UserService();

    public void simulateApplicationInput() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("This is the simulation of the e-bike rental process.");
            System.out.print("Are you a registered user? (true/false): ");
            while (!sc.hasNextBoolean()) {
                System.out.println("Invalid input! Enter true/false.");
                sc.next();
            }
            boolean isRegisteredUser = sc.nextBoolean();
            sc.nextLine();
            
            System.out.print("Enter your email address: ");
            String emailAddress = sc.nextLine();
            System.out.print("Enter your location: ");
            String location = sc.nextLine();
            
            System.out.println("Simulating the analysis of the rental request.");
            if (!isRegisteredUser) {
                System.out.println("You’re not our registered user. Please consider registering.");
                userRegistration.registration();
                return;
            } else {
                System.out.println("Welcome back, " + emailAddress + "!");
            }
            RegisteredUsers currentUser = userService.findUserByEmail(emailAddress);
            if (currentUser == null) {
                System.out.println("User not found! Please register first.");
                return;
            }
            String bikeID = bikeService.validateLocation(location);
            if (bikeID == null) {
                bikeService.addToRequestQueue(emailAddress, location);
                return;
            }
            System.out.println("Simulating e-bike reservation…");
            bikeService.reserveBike(bikeID);
            rentalService.startRental(bikeID, emailAddress);
            
            System.out.println("Displaying the active rentals…");
            rentalService.viewActiveRentals();
            
            System.out.println("Simulating the end of the trip…");
            rentalService.endRental(bikeID, currentUser);
            bikeService.releaseBike(bikeID);
            if (!BikeService.bikeRequestQueue.isEmpty()) {
                BikeRequest req = BikeService.bikeRequestQueue.poll();
                System.out.println("\nAuto-assigned to waiting user: " + req.getUserEmail());
                
                RegisteredUsers waitingUser = userService.findUserByEmail(req.getUserEmail());
                bikeService.reserveBike(bikeID);
                rentalService.startRental(bikeID, req.getUserEmail());
                if (waitingUser != null) {
                    rentalService.endRental(bikeID, waitingUser);
                } else {
                    rentalService.endRental(bikeID, null);
                }
                bikeService.releaseBike(bikeID);
            }
            
            System.out.println("Displaying the active rentals after trip end…");
            rentalService.viewActiveRentals();
        } finally {
            sc.close();
        }
    }
}