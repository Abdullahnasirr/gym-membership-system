// Represents an annual membership
// @author Ethan Chiu

public class AnnualMembership extends Membership {

    public AnnualMembership(String startDate, String endDate) {
        super(startDate, endDate);
    }

    @Override
    public String getMembershipType() {
        return "Annually";
    }

    @Override
    public int getDurationMonths() {
        return 12;
    }
}
