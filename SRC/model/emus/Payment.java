package model.emus;

public class Payment {
    private String mobileNumber;  // Changed to String
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;    // e.g., "MM/YY"
    private String cvv;

    // Default constructor
    public Payment() {}

    // Parameterized constructor
    public Payment(String mobileNumber, String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        this.mobileNumber = mobileNumber;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    // Getters and Setters
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCardHolderName() { return cardHolderName; }
    public void setCardHolderName(String cardHolderName) { this.cardHolderName = cardHolderName; }

    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }

    @Override
    public String toString() {
        return "Payment{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}