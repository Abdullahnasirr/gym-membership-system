package gymsystem;// Represents one member check-in.
// @author Ethan Chiu

public class CheckIn {
    private String checkInDate;

    public CheckIn(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    @Override
    public String toString() {
        return "Check-In Date: " + checkInDate;
    }
}
