import java.util.ArrayList;
import java.util.List;

public class Summaries {

    public static int totalMembers() {
        return Data.membersById.size();
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
    }

    public static List<Object[]> topFiveByVisits() {
        List<Object[]> list = new ArrayList<>(Data.membersById.values());

        list.sort((a, b) ->
                Integer.compare((int) b[Data.INDEX_VISITS],
                        (int) a[Data.INDEX_VISITS])
        );

        return list.subList(0, Math.min(5, list.size()));
    }
    public static double averageVisitsByType(String type) {
        int totalVisits = 0;
        int count = 0;

        for (Object[] member : Data.membersById.values()) {
            boolean active = (boolean) member[Data.INDEX_ACTIVE];
            String memberType = (String) member[Data.INDEX_TYPE];

            if (active && memberType.equalsIgnoreCase(type)) {
                totalVisits += (int) member[Data.INDEX_VISITS];
                count++;
            }
        }
        if (count == 0) {
            return 0.0; // avoid divide-by-zero
        }
        return (double) totalVisits / count;
    }

}

