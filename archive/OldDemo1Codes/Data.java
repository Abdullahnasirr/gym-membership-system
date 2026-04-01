package gymsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data {
    // ====================== GymSystem.Data Storage Structures =======================
    // Key: member ID
    // Value: Object[] containing member data
    public static final HashMap<Integer, Object[]> membersById = new HashMap<>();

    public static final ArrayList<Integer> memberIdList = new ArrayList<>();

    // ====================== Object[] Index GymSystem.Constants =======================

    public static final int INDEX_ID = 0;
    public static final int INDEX_NAME = 1;
    public static final int INDEX_TYPE = 2;
    public static final int INDEX_VISITS = 3;
    public static final int INDEX_TOTAL_PAID = 4;
    public static final int INDEX_ACTIVE = 5;

    // ===================== GymSystem.Membership Type GymSystem.Constants ========================

    public static final String TYPE_MONTHLY = "Monthly";
    public static final String TYPE_QUARTERLY = "Quarterly";
    public static final String TYPE_ANNUALLY = "Annually";

    // ===================== ID Generator ========================

    // Starting ID number (Starting ID number is set to 1000 for now, but can be changed)
    private static int nextId = 1000;

    // Method that generates a unique member ID
    private static int generateNewId() {

        // Safety check: ensure member ID does not already exist
        while (membersById.containsKey(nextId)) {
            nextId++;
        }

        return nextId++;
    }

    // ===================== Option 1 - Add GymSystem.Member ========================

    public static int addMember(String name, String type) {

        // Generate unique ID
        int id = generateNewId();

        // Create new member record
        Object[] member = new Object[6];

        member[INDEX_ID] = id;
        member[INDEX_NAME] = name;
        member[INDEX_TYPE] = type;

        // Default values
        member[INDEX_VISITS] = 0;        // Integer
        member[INDEX_TOTAL_PAID] = 0.0;  // Double
        member[INDEX_ACTIVE] = true;     // Boolean

        // Store in HashMap
        membersById.put(id, member);

        // Maintain insertion order list
        memberIdList.add(id);

        return id;

    }

    // ===================== Option 2 - Update GymSystem.Member ========================

    public static boolean updateMemberInfo(int id, String newName, String newType) {

        Object[] member = membersById.get(id);
        if (member == null) {
            return false; // not found
        }

        member[INDEX_NAME] = newName;
        member[INDEX_TYPE] = newType;

        return true;
    }

    // ===================== Option 3 - Record Check-In ========================

    public static boolean recordCheckIn(int id) {

        Object[] member = membersById.get(id);

        if (member == null) {
            return false; // GymSystem.Member not found
        }

        int visits = (Integer) member[INDEX_VISITS];
        member[INDEX_VISITS] = visits + 1;

        return true;
    }

    // ===================== Option 4 - Record GymSystem.Payment ========================

    public static boolean recordPayment(int id, double amount) {

        Object[] member = membersById.get(id);

        if (member == null) {
            return false; // GymSystem.Member not found
        }

        double currentTotal = (Double) member[INDEX_TOTAL_PAID];
        member[INDEX_TOTAL_PAID] = currentTotal + amount;

        return true;
    }

    // ===================== Option 5 - Set Active Status ========================

    public static boolean setActiveStatus(int id, boolean status) {

        Object[] member = membersById.get(id);

        if (member == null) {
            return false; // GymSystem.Member not found
        }

        member[INDEX_ACTIVE] = status;
        return true;
    }

// ===================== Option 6 - Get All Members ========================

    public static List<Object[]> getAllMembers() {

        ArrayList<Object[]> allMembers = new ArrayList<>();

        for (int id : memberIdList) {
            Object[] member = membersById.get(id);
            if (member != null) {
                allMembers.add(member);
            }
        }

        return allMembers;
    }

// ===================== Option 7 - Lookup GymSystem.Member By ID ========================

    public static Object[] getMemberById(int id) {
        return membersById.get(id); // Returns null if not found
    }
}
