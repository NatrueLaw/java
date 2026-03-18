import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class UserRegistration {
    private static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    private static final double VIP_DISCOUNT_UNDER_18 = 20.0;
    private static final double VIP_BASE_FEE = 100.0;
    
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge;
    private int cvv;
    private String userType;
    private boolean emailValid = false;
    private boolean minorAndBirthday = false;
    private boolean minor = false;
    private boolean ageValid = false;
    private boolean cardNumberValid = false;
    private boolean cardStillValid = false;
    private boolean validCVV = false;

    public void registration(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the ERyder Registration.");
        System.out.println("Here are your two options:");
        System.out.println("1. Register as a Regular User");
        System.out.println("2. Register as a VIP User");
        System.out.println("Please enter your choice (1 or 2):");
        int choice = sc.nextInt();
        sc.nextLine();
        if(choice==1){
            userType = "Regular User";
        }else if(choice == 2){
            userType = "VIP User";
        }else{
            System.out.println("Invalid choice. Please restart the registration process.");
            return;
        }
        System.out.println("Let's proceed with the registration... \n");
        System.out.println("Please enter your Full Name:");
        fullName = sc.nextLine();

        System.out.println("Please enter your email address:");
        emailAddress = sc.nextLine();
        System.out.println("Checking your email address validity...");
        emailValid = analyseEmail(emailAddress);
        if (!emailValid) {
            registration();
            return;
        }

        System.out.println("Please enter your date of birth as YYYY-MM-DD: ");
        dateOfBirth = sc.nextLine();
        System.out.println("Checking your age validity...");
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);
        if (!ageValid) {
            registration();
            return;
        }

        System.out.println("Please enter your card number: ");
        cardNumber = sc.nextLong();
        sc.nextLine();
        System.out.println("Checking your card number's validity...");
        cardNumberValid = analyseCardNumber(cardNumber);
        if (!cardNumberValid) {
            registration();
            return;
        }
        
        System.out.println("Please enter the card expiry date (MM/YY):");
        cardExpiryDate = sc.nextLine();
        System.out.println("Checking if your card is still valid...");
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);
        if (!cardStillValid) {
            registration();
            return;
        }

        System.out.println("Please enter your CVV: ");
        cvv = sc.nextInt();
        sc.nextLine();
        System.out.println("Checking your CVV's validity...");
        validCVV = analyseCVV(cvv);
        if (!validCVV) {
            registration();
            return;
        }

        finalCheckpoint();
        sc.close();
    }

    private boolean analyseEmail(String email){
        if(email.contains("@") && email.contains(".")){
            System.out.println("Email is valid");
            return true;
        }else{
            System.out.println("Invalid email address. Going back to the start of the registration");
            return false;
        }
    }

    private boolean analyseAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dob, currentDate);
        int age = period.getYears();

        boolean isBirthday = (dob.getMonth() == currentDate.getMonth() && dob.getDayOfMonth() == currentDate.getDayOfMonth());
        minor = (age < 18);
        minorAndBirthday = (minor && isBirthday);

        if (age >= 18) {
            System.out.println("Age is valid.");
            return true;
        } else {
            System.out.println("Invalid age: User is under 18 years old.");
            System.out.println("Going back to the start of the registration process...");
            return false;
        }
    }

    private boolean analyseCardNumber(long cardNumber) {
        String cardStr = Long.toString(cardNumber);
        if (cardStr.startsWith("4")) {
            cardProvider = "VISA";
            if (cardStr.length() == 13 || cardStr.length() == 16) {
                System.out.println("Valid VISA card number.");
                return true;
            }
        } else if (cardStr.startsWith("5")) {
            cardProvider = "MasterCard";
            if (cardStr.length() == 16) {
                System.out.println("Valid MasterCard card number.");
                return true;
            }
        } else if (cardStr.startsWith("34") || cardStr.startsWith("37")) {
            cardProvider = "American Express";
            if (cardStr.length() == 15) {
                System.out.println("Valid American Express card number.");
                return true;
            }
        }
        System.out.println("Sorry, but we accept only VISA, MasterCard, or American Express cards. Please try again with a valid card.");
        System.out.println("Going back to the start of the registration.");
        return false;
    }

    private boolean analyseCardExpiryDate(String cardExpiryDate) {
        int month = Integer.parseInt(cardExpiryDate.substring(0, 2));
        int year = 2000 + Integer.parseInt(cardExpiryDate.substring(3, 5));

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        if (year > currentYear || (year == currentYear && month >= currentMonth)) {
            System.out.println("The card is still valid");
            return true;
        } else {
            System.out.println("Sorry, your card has expired. Please use a different card.");
            System.out.println("Going back to the start fo the registration process...");
            return false;
        }
    }

    private boolean analyseCVV(int cvv) {
        String cvvStr = Integer.toString(cvv);
        if ((cardProvider.equals("American Express") && cvvStr.length() == 4) ||
            ((cardProvider.equals("VISA") || cardProvider.equals("MasterCard")) && cvvStr.length() == 3)) {
            System.out.println("Card CVV is valid.");
            return true;
        } else {
            System.out.println("Invalid CVV for the given card.");
            System.out.println("Going back to the start of the registration process.");
            return false;
        }
    }

    private void finalCheckpoint() {
        if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
            if (userType.equals("VIP User")) {
                chargeFees();
            }
            System.out.println(this);
        } else {
            System.out.println("Sorry, your registration was unsuccessful due to the following reason(s)");
            if (!emailValid) System.out.println("Invalid email address");
            if (!ageValid) System.out.println("Invalid age");
            if (!cardNumberValid) System.out.println("Invalid card number");
            if (!cardStillValid) System.out.println("Card has expired");
            if (!validCVV) System.out.println("Invalid CVV");
            System.out.println("Going back to the start of the registration process.");
            registration();
        }
    }

    private void chargeFees() {
        if (minorAndBirthday) {
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18_BIRTHDAY / 100);
        } else if (minor) {
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18 / 100);
        } else {
            feeToCharge = VIP_BASE_FEE;
        }
        String cardStr = Long.toString(cardNumber);
        String lastFour = cardStr.substring(cardStr.length() - 4);
        System.out.println("Thank you for your payment.");
        System.out.printf("A fee of %.2f has been charged to your card ending with %s%n", feeToCharge, lastFour);
    }

    @Override
    public String toString() {
        String cardNumberStr = Long.toString(cardNumber);
        String censoredPart = cardNumberStr.substring(0, cardNumberStr.length() - 4).replaceAll(".", "*");
        String lastFourDigits = cardNumberStr.substring(cardNumberStr.length() - 4);
        String censoredNumber = censoredPart + lastFourDigits;

        return "Registration successful! Here are your details:\n" +
               "User Type: " + userType + "\n" +
               "Full Name: " + fullName + "\n" +
               "Email Address: " + emailAddress + "\n" +
               "Date of Birth: " + dateOfBirth + "\n" +
               "Card Number: " + censoredNumber + "\n" +
               "Card Provider: " + cardProvider + "\n" +
               "Card Expiry Date: " + cardExpiryDate;
    }
}