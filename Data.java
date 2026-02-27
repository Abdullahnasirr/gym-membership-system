import java.util.ArrayList;
import java.util.HashMap;

public class Data {
    // ====================== Data Storage Structures =======================
    // Key: member ID
    // Value: Object[] containing member data
    public static final HashMap<Integer, Object[]> membersById = new HashMap<>();

    public static final ArrayList<Integer> memberIdList = new ArrayList<>();

    // ====================== Object[] Index Constants =======================

    public static final int INDEX_ID = 0;
    public static final int INDEX_NAME = 1;
    public static final int INDEX_TYPE = 2;
    public static final int INDEX_VISITS = 3;
    public static final int INDEX_TOTAL_PAID = 4;
    public static final int INDEX_ACTIVE = 5;

    // ===================== Membership Type Constants ========================

    public static final String TYPE_MONTHLY = "Monthly";
    public static final String TYPE_QUARTERLY = "Quarterly";
    public static final String TYPE_ANNUALLY = "Annually";

}