import java.util.List;
import java.util.Scanner;

public class AdminPanel {
    private final UserService userService = new UserService();
    private final BikeService bikeService = new BikeService();
    private final Scanner sc = new Scanner(System.in);

    public void userManagementOptions() {
        while (true) {
            System.out.println("\nWelcome to E-Ryder Administrator Panel.");
            System.out.println("What do you want to do?");
            System.out.println("1. Add New Users");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered Users");
            System.out.println("4. Update Registered Users");
            System.out.println("5. Demo the Bike Rental System");
            System.out.println("6. View System Logs");
            System.out.println("7. Manage Pending Bike Requests");
            System.out.println("8. EXIT");
            System.out.print("Please enter your choice: ");

            int choice;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                sc.nextLine();
                System.out.println("Invalid choice. Please try again");
                continue;
            }

            switch (choice) {
                case 1 -> addNewUsers();
                case 2 -> viewRegisteredUsers();
                case 3 -> removeRegisteredUsers();
                case 4 -> updateRegisteredUsers();
                case 5 -> {
                    BikeRental bikeRental = new BikeRental();
                    bikeRental.simulateApplicationInput();
                }
                case 6 -> bikeService.viewSystemLogs(); // 查看日志
                case 7 -> manageRequestQueue(); // 管理预约队列
                case 8 -> {
                    System.out.println("Exiting Admin Panel... Thank you!");
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again");
            }
        }
    }
    private void manageRequestQueue() {
        while (true) {
            System.out.println("\n===== MANAGE PENDING REQUESTS =====");
            System.out.println("1. View Queue");
            System.out.println("2. Update Queue (Remove First)");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int c = sc.nextInt();
            sc.nextLine();

            if (c == 1) {
                System.out.println("\n===== PENDING REQUESTS =====");
                for (BikeRequest req : BikeService.bikeRequestQueue) {
                    System.out.println(req);
                }
            } else if (c == 2) {
                if (!BikeService.bikeRequestQueue.isEmpty()) {
                    BikeService.bikeRequestQueue.poll();
                    System.out.println("First request removed successfully.");
                } else {
                    System.out.println("Queue is empty.");
                }
            } else if (c == 3) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
    private void addNewUsers() {
        System.out.print("\nHow many users would you like to add? ");
        int numUsers = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numUsers; i++) {
            System.out.println("\n=== Adding User " + (i+1) + " ===");
            System.out.print("Full Name: ");
            String fullName = sc.nextLine();
            System.out.print("Email Address: ");
            String emailAddress = sc.nextLine();
            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dateOfBirth = sc.nextLine();
            System.out.print("Card Number: ");
            long cardNumber = sc.nextLong();
            sc.nextLine();
            System.out.print("Card Expiry Date (MM/YY): ");
            String cardExpiryDate = sc.nextLine();
            System.out.print("Card Provider (VISA/MasterCard/American Express): ");
            String cardProvider = sc.nextLine();
            System.out.print("CVV: ");
            int cvv = sc.nextInt();
            sc.nextLine();
            System.out.print("User Type (Regular User/VIP User): ");
            String userType = sc.nextLine();
            String[] lastThreeTrips = new String[3];
            for (int j = 0; j < 3; j++) {
                System.out.println("\n--- Enter Trip " + (j+1) + " Details ---");
                System.out.print("Trip Date (YYYY-MM-DD): ");
                String tripDate = sc.nextLine();
                System.out.print("Source and Destination (e.g., NJIT Gate 5, Wending Square): ");
                String srcDest = sc.nextLine();
                System.out.print("Fare (€): ");
                double fare = sc.nextDouble();
                sc.nextLine();
                System.out.print("Feedback (press ENTER for NULL): ");
                String feedback = sc.nextLine();
                if (feedback.isEmpty()) feedback = "NULL";
                StringBuilder tripSB = new StringBuilder();
                tripSB.append("Date: ").append(tripDate)
                      .append(", Source: ").append(srcDest.split(",")[0].trim())
                      .append(", Destination: ").append(srcDest.split(",")[1].trim())
                      .append(", Fare (€): ").append(fare)
                      .append(", Feedback: ").append(feedback);
                lastThreeTrips[j] = tripSB.toString();
            }
            RegisteredUsers newUser;
            if (userType.equalsIgnoreCase("VIP User") || userType.equalsIgnoreCase("VIP")) {
                newUser = new VIPUser(fullName, emailAddress, dateOfBirth, cardNumber,cardExpiryDate, cardProvider, cvv, userType, lastThreeTrips);
            } else {
                newUser = new RegularUser(fullName, emailAddress, dateOfBirth, cardNumber,cardExpiryDate, cardProvider, cvv, userType, lastThreeTrips);
            }
            userService.addUser(newUser);
        }
    }

    private void viewRegisteredUsers() {
        System.out.println("\n=== All Registered Users ===");
        List<RegisteredUsers> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No registered users to display");
            return;
        }
        for (RegisteredUsers user : users) {
            System.out.println(user);
        }
    }

    private void removeRegisteredUsers() {
        System.out.println("\n=== Remove Registered User ===");
        if (userService.getAllUsers().isEmpty()) {
            System.out.println("No registered users to remove");
            return;
        }
        System.out.print("Enter the email address of the user to remove: ");
        String targetEmail = sc.nextLine();
        boolean removed = userService.removeUserByEmail(targetEmail);
        if (removed) {
            System.out.println("User with email " + targetEmail + " removed successfully!");
        } else {
            System.out.println("No user found with this email address");
        }
    }

    private void updateRegisteredUsers() {
        System.out.println("\n=== Update Registered User ===");
        if (userService.getAllUsers().isEmpty()) {
            System.out.println("No registered users to update");
            return;
        }
        System.out.print("Enter the email address of the user to update: ");
        String targetEmail = sc.nextLine();
        RegisteredUsers targetUser = userService.findUserByEmail(targetEmail);
        if (targetUser == null) {
            System.out.println("No user found with this email address");
            return;
        }
        System.out.println("Enter new details (press ENTER for no change for strings, enter 0 for no change for numbers)");
        System.out.print("New Full Name: (Press ENTER for no change) ");
        String newFullName = sc.nextLine();
        if (!newFullName.isEmpty()) targetUser.setFullName(newFullName);
        System.out.print("New Date of Birth (YYYY-MM-DD): (Press ENTER for no change) ");
        String newDob = sc.nextLine();
        if (!newDob.isEmpty()) targetUser.setDateOfBirth(newDob);
        System.out.print("New Card Expiry Date (MM/YY): (Press ENTER for no change) ");
        String newExpiry = sc.nextLine();
        if (!newExpiry.isEmpty()) targetUser.setCardExpiryDate(newExpiry);
        System.out.print("New Card Provider: (Press ENTER for no change) ");
        String newProvider = sc.nextLine();
        if (!newProvider.isEmpty()) targetUser.setCardProvider(newProvider);
        System.out.print("New User Type: (Press ENTER for no change) ");
        String newUserType = sc.nextLine();
        if (!newUserType.isEmpty()) targetUser.setUserType(newUserType);
        System.out.print("New Card Number (enter 0 for no change): ");
        long newCardNum = sc.nextLong();
        sc.nextLine();
        if (newCardNum != 0) targetUser.setCardNumber(newCardNum);
        System.out.print("New CVV (enter 0 for no change): ");
        int newCvv = sc.nextInt();
        sc.nextLine();
        if (newCvv != 0) targetUser.setCvv(newCvv);
        System.out.println("User with email " + targetEmail + " updated successfully!");
    }
}