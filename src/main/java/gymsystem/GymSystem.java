package gymsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * GymSystem.java
 *
 * Stores and manages all gym members in the object-oriented version
 * of the project.
 */
public class GymSystem {

    public static final String TYPE_MONTHLY = "Monthly";
    public static final String TYPE_QUARTERLY = "Quarterly";
    public static final String TYPE_ANNUALLY = "Annually";

    private final HashMap<String, Member> membersById;
    private final HashSet<String> usedContacts;
    private int nextMemberNumber;

    public GymSystem() {
        membersById = new HashMap<>();
        usedContacts = new HashSet<>();
        nextMemberNumber = 1000;
    }

    public String generateMemberId() {
        String memberId = "M" + nextMemberNumber;
        nextMemberNumber++;
        return memberId;
    }

    public boolean isContactUsed(String contact) {
        return usedContacts.contains(contact);
    }

    public boolean addMember(Member member) {
        if (member == null) {
            return false;
        }

        if (membersById.containsKey(member.getMemberId())) {
            return false;
        }

        if (usedContacts.contains(member.getPhoneOrEmail())) {
            return false;
        }

        membersById.put(member.getMemberId(), member);
        usedContacts.add(member.getPhoneOrEmail());
        updateNextMemberNumberFromId(member.getMemberId());
        return true;
    }

    public Member findMemberById(String memberId) {
        return membersById.get(memberId);
    }

    public ArrayList<Member> getAllMembers() {
        refreshAllMemberStatuses();
        ArrayList<Member> members = new ArrayList<>(membersById.values());
        Collections.sort(members);
        return members;
    }

    public int getTotalMembers() {
        return membersById.size();
    }

    public boolean updateMemberInfo(String memberId, String name, String contact, String address) {
        Member member = membersById.get(memberId);

        if (member == null) {
            return false;
        }

        String oldContact = member.getPhoneOrEmail();

        if (!oldContact.equalsIgnoreCase(contact) && usedContacts.contains(contact)) {
            return false;
        }

        usedContacts.remove(oldContact);

        member.setFullName(name);
        member.setPhoneOrEmail(contact);
        member.setAddress(address);

        usedContacts.add(contact);
        return true;
    }

    public boolean recordCheckIn(String memberId) {
        Member member = membersById.get(memberId);

        if (member == null) {
            return false;
        }

        member.refreshActiveStatus();

        if (!member.isActive()) {
            return false;
        }

        member.recordCheckIn();
        return true;
    }

    public boolean recordPayment(String memberId, double amount) {
        Member member = membersById.get(memberId);

        if (member == null || amount <= 0) {
            return false;
        }

        member.recordPayment(amount);
        return true;
    }

    public boolean setMemberActive(String memberId, boolean active) {
        Member member = membersById.get(memberId);

        if (member == null) {
            return false;
        }

        member.setActive(active);
        return true;
    }

    public int getActiveMembersCount() {
        refreshAllMemberStatuses();
        int count = 0;

        for (Member member : membersById.values()) {
            if (member.isActive()) {
                count++;
            }
        }

        return count;
    }

    public double getTotalRevenue() {
        double total = 0.0;

        for (Member member : membersById.values()) {
            total += member.getTotalPaid();
        }

        return total;
    }

    public ArrayList<Member> getTop5MembersByVisits() {
        ArrayList<Member> members = getAllMembers();
        members.sort((a, b) -> Integer.compare(b.getTotalVisits(), a.getTotalVisits()));
        return new ArrayList<>(members.subList(0, Math.min(5, members.size())));
    }

    public ArrayList<Member> getInactiveOrZeroVisitMembers() {
        refreshAllMemberStatuses();
        ArrayList<Member> result = new ArrayList<>();

        for (Member member : membersById.values()) {
            if (!member.isActive() || member.getTotalVisits() == 0) {
                result.add(member);
            }
        }

        return result;
    }

    public double getAverageVisitsByMembershipType(String membershipType) {
        int totalVisits = 0;
        int count = 0;

        for (Member member : membersById.values()) {
            if (member.isActive() &&
                    member.getMembershipType().equalsIgnoreCase(membershipType)) {
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
            } catch (NumberFormatException e) {
                // ignore bad ID format
            }
        }
    }

    public void refreshAllMemberStatuses() {
        for (Member member : membersById.values()) {
            member.refreshActiveStatus();
        }
    }
}