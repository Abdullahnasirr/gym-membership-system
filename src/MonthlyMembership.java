// Represents a monthly membership.
// @author Ethan Chiu


public class MonthlyMembership extends Membership {

    public MonthlyMembership(String startDate, String endDate) {
        super(startDate, endDate);
    }

    @Override
    public String getMembershipType() {
        return "Monthly";
    }

    @Override
    public int getDurationMonths() {
        return 1;
    }
}
