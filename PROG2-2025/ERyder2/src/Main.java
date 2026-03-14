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
    
        String sent1 = "I was very satisfied with the service.";
        String sent2 = "The e-Bike is quite comfortable to ride.";
        String sent3 = "The battery life of the e-Bike is impressive.";
        String sent4 = "The customer support was helpful and responsive.";
        String sent5 = "I would recommend this e-Bike to my friends and family.";

        Feedback feedback = new Feedback("Aaaa", "Bbbb", "Aaaa.Bbbb@qq.com");
        feedback.analyseFeedback(true, sent1, sent2, sent3, sent4, sent5);
        System.out.println(feedback);
    }


}