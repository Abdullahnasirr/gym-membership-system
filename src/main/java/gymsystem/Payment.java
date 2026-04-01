package gymsystem;

// Represents one payment made by a member.
// @author Ethan Chiu
public class Payment {
    private double amount;
    private String paymentDate;

    public Payment(double amount, String paymentDate) {
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "GymSystem.Payment Amount: $" + String.format("%.2f", amount) +
                ", Date: " + paymentDate;
    }
}