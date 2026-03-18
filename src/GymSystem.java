import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * GymSystem.java
 * Stores and manages all gym members in the object-oriented version
 * of the project.
 */
public class GymSystem {

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
        return new ArrayList<>(membersById.values());
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
}