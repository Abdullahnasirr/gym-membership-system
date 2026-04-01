package gymsystem;

/**
 * GymSystem.Member.java
 *
 * CPSC 219 W26
 * Demo 2 - Gym GymSystem.Membership System
 *
 * Name: Wai Yan Aung
 *  Date: 18 March 2026
 *  Tutorial: T05
 *
 * Represents one gym member in the object-oriented version of the program.
 * This class replaces the old Object[] member record from Demo 1.
 */
public class Member implements Comparable<Member> {

    /** Unique member ID used to identify the member. */
    private final String memberId;

    /** Full name of the member. */
    private String fullName;

    /** Contact info of member such as phone number or email. */
    private String phoneOrEmail;

    /** Home or mailing address of the member. */
    private String address;

    /** Whether the membership is currently active. */
    private boolean active;

    /** GymSystem.Membership object associated with this member. */
    private Membership membership;

    /** Total number of visits recorded for this member. */
    private int totalVisits;

    /** Total amount paid by this member. */
    private double totalPaid;

    /**
     * Creates a new GymSystem.Member object with default visit/payment values.
     *
     * @param memberId unique member ID
     * @param fullName member full name
     * @param phoneOrEmail contact info
     * @param address address
     * @param membership membership type label
     */
    public Member(String memberId, String fullName, String phoneOrEmail,
                  String address, Membership membership) {
        this.memberId = memberId;
        this.fullName = fullName;
        this.phoneOrEmail = phoneOrEmail;
        this.address = address;
        this.membership = membership;
        // New members always begin as active, no visits and no payments recorded.
        this.active = true;
        this.totalVisits = 0;
        this.totalPaid = 0.0;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneOrEmail() {
        return phoneOrEmail;
    }

    public String getAddress() {
        return address;
    }

    public boolean isActive() {
        return active;
    }

    public String getMembershipType() {
        return membership.getMembershipType();
    }

    public Membership getMembership() {
        return membership;
    }

    public int getTotalVisits() {
        return totalVisits;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneOrEmail(String phoneOrEmail) {
        this.phoneOrEmail = phoneOrEmail;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    /**
     * Records one visit for the member.
     */
    public void recordCheckIn() {
        totalVisits++;
    }

    /**
     * Adds a payment to the running total.
     *
     * @param amount payment amount
     */
    public void recordPayment(double amount) {
        totalPaid += amount;
    }

    /**
     * Default string representation for printing member information.
     *
     * @return formatted member summary
     */
    @Override
    public String toString() {
        return String.format(
                "GymSystem.Member ID: %s | Name: %s | Contact: %s | Address: %s | GymSystem.Membership Type: %s | Total Visits: %d | Total Paid: $%.2f | Status: %s",
                memberId,
                fullName,
                phoneOrEmail,
                address,
                membership.getMembershipType(),
                totalVisits,
                totalPaid,
                active ? "Active" : "Inactive"
        );
    }

    /**
     * Defines equality by unique member ID.
     *
     * @param obj object to compare against
     * @return true if both Members have the same memberId
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Member other)) {
            return false;
        }
        // Equality is based on the unique member ID, not fields like name or address.
        return memberId.equals(other.memberId);
    }

    /**
     * Generates a hash code based on unique member ID.
     *
     * @return hash code of memberId
     */
    @Override
    public int hashCode() {
        return memberId.hashCode();
    }

    /**
     * Compares members by member ID for natural ordering.
     *
     * @param other other GymSystem.Member object
     * @return comparison result by memberId
     */
    @Override
    public int compareTo(Member other) {
        return this.memberId.compareTo(other.memberId);
    }
}