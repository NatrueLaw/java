public class Main {
    public static void main(String[] args) {
        
        ERyder2 bike1 = new ERyder2(
                "eRyder001",
                50,
                true,
                10,
                "user_A",
                "110"
        );
        ERyder2 bike2 = new ERyder2(
                "eRyder002",
                55,
                false,
                12,
                0,
                0.0,
                "user_B",
                "120"
        );

        bike1.printRideDetails(20);
        bike2.printRideDetails(35);


    }
}