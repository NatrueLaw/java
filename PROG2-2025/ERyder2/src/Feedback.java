public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;
    public Feedback(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public void analyseFeedback(boolean isConcatenation, String sent1, String sent2, String sent3, String sent4, String sent5) {
        if (isConcatenation) {
            completeFeedback = feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
            checkFeedbackLength(completeFeedback);
            createReviewID(firstName, lastName, completeFeedback);
        } else {
            completeFeedback = feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5).toString();
            checkFeedbackLength(completeFeedback);
            createReviewID(firstName, lastName, completeFeedback);
        }
    }

    private String feedbackUsingConcatenation(String sent1, String sent2, String sent3, String sent4, String sent5) {
        String concatenatedFeedback = "";
        concatenatedFeedback += sent1;
        concatenatedFeedback += sent2;
        concatenatedFeedback += sent3;
        concatenatedFeedback += sent4;
        concatenatedFeedback += sent5;
        return concatenatedFeedback;
    }

    private StringBuilder feedbackUsingStringBuilder(String sent1, String sent2, String sent3, String sent4, String sent5) {
        StringBuilder sb = new StringBuilder();
        sb.append(sent1).append(sent2).append(sent3).append(sent4).append(sent5);
        return sb;
    }

    private boolean checkFeedbackLength(String feedback) {
        if (feedback.length() > 500) {
            longFeedback = true;
            return longFeedback;
        }
         
        longFeedback = false;
        return longFeedback;
    }
    private void createReviewID(String firstName, String lastName, String completeFeedback) {
        String namePart = (firstName + lastName).substring(2, 6).toUpperCase();
        String feedbackPart = completeFeedback.substring(10, 15).toLowerCase();
        String lengthPart = completeFeedback.length() + "_";
        String timestampPart = String.valueOf(System.currentTimeMillis());
        String fullReviewID = (namePart + feedbackPart + lengthPart + timestampPart).replace(" ", "");
        this.reviewID = fullReviewID;
    }

    @Override
    public String toString() {
        return
                "\nFirst Name: " + firstName +
                "\nLast Name: " + lastName +
                "\nEmail: " + email +
                "\nComplete Feedback: " + completeFeedback +
                "\nIs Long Feedback: " + longFeedback +
                "\nReview ID: " + reviewID;
                
    }

}    