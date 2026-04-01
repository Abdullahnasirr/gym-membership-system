package GymSystem;// Abstract parent class for all membership types.
// @author Ethan Chiu

public abstract class Membership {
    private String startDate;
    private String endDate;

    public Membership(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public abstract String getMembershipType();

    public abstract int getDurationMonths();

    @Override
    public String toString() {
        return "Type: " + getMembershipType() +
                ", Start Date: " + startDate +
                ", End Date: " + endDate +
                ", Duration: " + getDurationMonths() + " month(s)";
    }
}
