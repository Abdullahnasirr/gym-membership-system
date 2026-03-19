// Represents a quarterly membership
// @author Ethan Chiu

public class QuarterlyMembership extends Membership {

    public QuarterlyMembership(String startDate, String endDate) {
        super(startDate, endDate);
    }

    @Override
    public String getMembershipType() {
        return "Quarterly";
    }

    @Override
    public int getDurationMonths() {
        return 3;
    }
}