public class Summaries {

    public static int totalMembers() {
        return Data.membersById.size();
    }

}
public static int activeMembers() {
    int count = 0;

    for (Object[] member : Data.membersById.values()) {
        if ((boolean) member[Data.INDEX_ACTIVE]) {
            count++;
        }
    }

    return count;
}
public static double totalRevenue() {
    double total = 0.0;

    for (Object[] member : Data.membersById.values()) {
        total += (double) member[Data.INDEX_TOTAL_PAID];
    }

    return total;
}
public static int inactiveOrZeroVisits() {
    int count = 0;

    for (Object[] member : Data.membersById.values()) {
        boolean active = (boolean) member[Data.INDEX_ACTIVE];
        int visits = (int) member[Data.INDEX_VISITS];

        if (!active || visits == 0) {
            count++;
        }
    }

    return count;