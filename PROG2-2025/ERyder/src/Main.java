public class Main {
    public static void main(String[] args) {

        ERyder defaultBike = new ERyder();
        System.out.println("Default Constructor Bike");
        defaultBike.printBikeDetails();

        ERyder paramBike = new ERyder("eRyder-1", 50, true, 100);
        System.out.println("Parameter Constructor Bike");
        paramBike.printBikeDetails();
    }
}
