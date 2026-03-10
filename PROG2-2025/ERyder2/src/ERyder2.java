public class ERyder2 {
    
    public static final String COMPANY_NAME = "ERyder";
    public static final double BASE_FARE = 1.0;
    public static final double PER_MINUTE_FARE = 0.5;

    private final String LINKED_ACCOUNT;
    private final String LINKED_PHONE_NUMBER;

    private String bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;
    private int totalUsageInMinutes;
    private double totalFare;

    
    public ERyder2(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven,
                  String linkedAccount, String linkedPhoneNumber) {
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        this.LINKED_ACCOUNT = linkedAccount;
        this.LINKED_PHONE_NUMBER = linkedPhoneNumber;
        this.totalUsageInMinutes = 0;
        this.totalFare = 0.0;
    }

    
    public ERyder2(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven,
                  int totalUsageInMinutes, double totalFare,
                  String linkedAccount, String linkedPhoneNumber) {
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        this.totalUsageInMinutes = totalUsageInMinutes;
        this.totalFare = totalFare;
        this.LINKED_ACCOUNT = linkedAccount;
        this.LINKED_PHONE_NUMBER = linkedPhoneNumber;
    }

    
    private double calculateFare(int usageInMinutes) {
        return BASE_FARE + (PER_MINUTE_FARE * usageInMinutes);
    }

    
    public void printRideDetails(int usageInMinutes) {
        this.totalUsageInMinutes = usageInMinutes;
        this.totalFare = calculateFare(usageInMinutes);

        System.out.println(COMPANY_NAME + " Ride Details");
        System.out.println("Linked Account: " + LINKED_ACCOUNT);
        System.out.println("Linked Phone Number: " + LINKED_PHONE_NUMBER);
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Usage Duration : " + totalUsageInMinutes + " minutes");
        System.out.println("Total Fare:"+ totalFare);
        
    }

    
    public String getBikeID() { return bikeID; }
    public int getBatteryLevel() { return batteryLevel; }
    public boolean isAvailable() { return isAvailable; }
    public double getKmDriven() { return kmDriven; }
    public int getTotalUsageInMinutes() { return totalUsageInMinutes; }
    public double getTotalFare() { return totalFare; }
    public String getLINKED_ACCOUNT() { return LINKED_ACCOUNT; }
    public String getLINKED_PHONE_NUMBER() { return LINKED_PHONE_NUMBER; }
}
