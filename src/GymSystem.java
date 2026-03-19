import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * GymSystem.java
 *
 * CPSC 219 W26
 * Demo 2 - Gym Membership System
 *
 * Name: Wai Yan Aung
 *  Date: 18 March 2026
 *  Tutorial: T05
 *
 * Stores and manages all gym members in the object-oriented version
 * of the project.
 */
public class GymSystem {

    public static final String TYPE_MONTHLY = "Monthly";
    public static final String TYPE_QUARTERLY = "Quarterly";
    public static final String TYPE_ANNUALLY = "Annually";

    /** Lookup structure for members by unique member ID. */
    private final HashMap<String, Member> membersById;

    /** Prevents duplicate contacts if your group wants contact uniqueness. */
    private final HashSet<String> usedContacts;

    /** Counter used to generate new member IDs. */
    private int nextMemberNumber;

    /**
     * Creates an empty GymSystem.
     */
    public GymSystem() {
        membersById = new HashMap<>();
        usedContacts = new HashSet<>();
        nextMemberNumber = 1000;
    }

    /**
     * Generates the next unique member ID.
     *
     * @return unique member ID string
     */
    public String generateMemberId() {
        String memberId = "M" + nextMemberNumber;
        nextMemberNumber++;
        return memberId;
    }

    /**
     * Checks whether a contact value has already been used.
     *
     * @param contact contact string
     * @return true if already used
     */
    public boolean isContactUsed(String contact) {
        return usedContacts.contains(contact);
    }

    /**
     * Adds a member object to the system.
     *
     * @param member member to add
     */
    public void addMember(Member member) {
        membersById.put(member.getMemberId(), member);
        usedContacts.add(member.getPhoneOrEmail());
        updateNextMemberNumberFromId(member.getMemberId());
    }

    /**
     * Finds one member by member ID.
     *
     * @param memberId unique member ID
     * @return matching Member or null if not found
     */
    public Member findMemberById(String memberId) {
        return membersById.get(memberId);
    }

    /**
     * Returns all members as a list.
     *
     * @return all stored members
     */
    public ArrayList<Member> getAllMembers() {
        ArrayList<Member> members = new ArrayList<>(membersById.values());
        Collections.sort(members);
        return members;
    }

    /**
     * Returns total member count.
     *
     * @return total number of members
     */
    public int getTotalMembers() {
        return membersById.size();
    }

    /**
     * Updates editable member information.
     *
     * @param memberId member ID
     * @param name new name
     * @param contact new contact
     * @param address new address
     * @return true if update succeeded
     */
    public boolean updateMemberInfo(String memberId, String name, String contact, String address) {
        Member member = membersById.get(memberId);

        if (member == null) {
            return false;
        }

        // Remove old contact before replacing it in the uniqueness "set".
        usedContacts.remove(member.getPhoneOrEmail());

        member.setFullName(name);
        member.setPhoneOrEmail(contact);
        member.setAddress(address);

        usedContacts.add(contact);
        return true;
    }

    /**
     * Records a member check-in.
     *
     * @param memberId member ID
     * @return true if member exists
     */
    public boolean recordCheckIn(String memberId) {
        Member member = membersById.get(memberId);

        if (member == null) {
            return false;
        }

        member.recordCheckIn();
        return true;
    }

    /**
     * Records a payment for a member.
     *
     * @param memberId member ID
     * @param amount payment amount
     * @return true if member exists and amount is valid
     */
    public boolean recordPayment(String memberId, double amount) {
        Member member = membersById.get(memberId);

        if (member == null || amount < 0) {
            return false;
        }

        member.recordPayment(amount);
        return true;
    }

    /**
     * Changes a member's active status.
     *
     * @param memberId member ID
     * @param active new active status
     * @return true if member exists
     */
    public boolean setMemberActive(String memberId, boolean active) {
        Member member = membersById.get(memberId);

        if (member == null) {
            return false;
        }

        member.setActive(active);
        return true;
    }

    /**
     * Counts active members in the system.
     *
     * @return number of active members
     */
    public int getActiveMembersCount() {
        int count = 0;

        for (Member member : membersById.values()) {
            if (member.isActive()) {
                count++;
            }
        }

        return count;
    }

    /**
     * Calculates total revenue collected from all members.
     *
     * @return total revenue
     */
    public double getTotalRevenue() {
        double total = 0.0;

        for (Member member : membersById.values()) {
            total += member.getTotalPaid();
        }

        return total;
    }

    /**
     * Returns up to the top 5 members ranked by total visits.
     *
     * @return list of top members by visits
     */
    public ArrayList<Member> getTop5MembersByVisits() {
        ArrayList<Member> members = getAllMembers();

        members.sort((a, b) -> Integer.compare(b.getTotalVisits(), a.getTotalVisits()));

        return new ArrayList<>(members.subList(0, Math.min(5, members.size())));
    }

    /**
     * Returns members that are inactive or have zero visits.
     *
     * @return list of inactive or zero-visit members
     */
    public ArrayList<Member> getInactiveOrZeroVisitMembers() {
        ArrayList<Member> result = new ArrayList<>();

        for (Member member : membersById.values()) {
            if (!member.isActive() || member.getTotalVisits() == 0) {
                result.add(member);
            }
        }

        return result;
    }

    /**
     * Calculates average visits for active members of a given membership type.
     *
     * @param membershipType membership type to filter by
     * @return average visits, or 0.0 if none match
     */
    public double getAverageVisitsByMembershipType(String membershipType) {
        int totalVisits = 0;
        int count = 0;

        for (Member member : membersById.values()) {
            if (member.isActive()
                    && member.getMembershipType().equalsIgnoreCase(membershipType)) {
                totalVisits += member.getTotalVisits();
                count++;
            }
        }

        if (count == 0) {
            return 0.0;
        }

        return (double) totalVisits / count;
    }

    public void clearAllData() {
        membersById.clear();
        usedContacts.clear();
        nextMemberNumber = 1000;
    }

    private void updateNextMemberNumberFromId(String memberId) {
        if (memberId != null && memberId.startsWith("M")) {
            try {
                int numericPart = Integer.parseInt(memberId.substring(1));
                if (numericPart >= nextMemberNumber) {
                    nextMemberNumber = numericPart + 1;
                }
            } catch (NumberFormatException e) {// Ignore bad ID format
            }
        }
    }
}