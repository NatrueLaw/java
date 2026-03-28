public class RegisteredUsers {
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardExpiryDate;
    private String cardProvider;
    private int cvv;
    private String userType;
    private String[] lastThreeTrips;
    public RegisteredUsers(String fullName, String emailAddress, String dateOfBirth, long cardNumber,
                           String cardExpiryDate, String cardProvider, int cvv, String userType,
                           String[] lastThreeTrips) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.cardProvider = cardProvider;
        this.cvv = cvv;
        this.userType = userType;
        this.lastThreeTrips = lastThreeTrips;
    }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public long getCardNumber() { return cardNumber; }
    public void setCardNumber(long cardNumber) { this.cardNumber = cardNumber; }
    public String getCardExpiryDate() { return cardExpiryDate; }
    public void setCardExpiryDate(String cardExpiryDate) { this.cardExpiryDate = cardExpiryDate; }
    public String getCardProvider() { return cardProvider; }
    public void setCardProvider(String cardProvider) { this.cardProvider = cardProvider; }
    public int getCvv() { return cvv; }
    public void setCvv(int cvv) { this.cvv = cvv; }
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
    public String[] getLastThreeTrips() { return lastThreeTrips; }
    public void setLastThreeTrips(String[] lastThreeTrips) { this.lastThreeTrips = lastThreeTrips; }
    @Override
    public String toString() {
        StringBuilder tripsInfo = new StringBuilder();
        for (int i = 0; i < lastThreeTrips.length; i++) {
            tripsInfo.append("\n  Trip ").append(i+1).append(": ").append(lastThreeTrips[i]);
        }
        String cardStr = Long.toString(cardNumber);
        String censoredCard = cardStr.substring(0, cardStr.length()-4).replaceAll(".", "*") + cardStr.substring(cardStr.length()-4);
        
        return 
               "\nRegistered User Details" +
               "\nFull Name: " + fullName +
               "\nEmail Address: " + emailAddress +
               "\nDate of Birth: " + dateOfBirth +
               "\nCard Number: " + censoredCard +
               "\nCard Provider: " + cardProvider +
               "\nCard Expiry Date: " + cardExpiryDate +
               "\nCVV: " + cvv +
               "\nUser Type: " + userType +
               "\nLast 3 Trips:" + tripsInfo;
    }
}