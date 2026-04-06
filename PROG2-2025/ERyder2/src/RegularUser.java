public class RegularUser extends RegisteredUsers {

    public RegularUser(String fullName, String emailAddress, String dateOfBirth, long cardNumber,
                       String cardExpiryDate, String cardProvider, int cvv, String userType,
                       String[] lastThreeTrips) {
        super(fullName, emailAddress, dateOfBirth, cardNumber,
                cardExpiryDate, cardProvider, cvv, userType, lastThreeTrips);
    }

    @Override
    public double calculateFare(double baseFare) {
        return super.calculateFare(baseFare);
    }

    @Override
    public void displayUserType() {
        System.out.println("Regular User");
    }
}