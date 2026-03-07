public class Main {
    public static void main(String[] args) {

        ERyder defaultBike = new ERyder();
        System.out.println("Default Constructor Bike");
        defaultBike.printBikeDetails();

        ERyder paramBike = new ERyder("EB12345", 75, true, 125.5);
        System.out.println("Parameter Constructor Bike");
        paramBike.printBikeDetails();
    }
}