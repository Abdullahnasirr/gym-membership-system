import java.util.List;
import java.util.Scanner;

public class Menu {

    private static final Scanner INPUT = new Scanner(System.in);

    public static void start() {

        boolean running = true;

        while (running) {
            printHeader();
            printMenu();

            int choice = readMenuChoice();

            switch (choice) {

                //Option 1
                case Constants.ADD_MEMBER:
                    System.out.println("\nAdd member Selected\n");
                    handleAddMember();
                    break;

                //Option 2
                case Constants.UPDATE_MEMBER:
                    System.out.println("\nUpdate member selected\n");
                    handleUpdateMember();
                    break;

                //Option 3
                case Constants.CHECK_IN:
                    System.out.println("\nCheck in selected\n");
                    handleCheckIn();
                    break;

                //Option 4
                case Constants.PAYMENT:
                    System.out.println("\nPayment selected\n");
                    handlePayment();
                    break;

                //Option 5
                case Constants.TOGGLE_ACTIVE:
                    System.out.println("\nChange membership's status\n");
                    handleToggleActive();
                    break;

                //Option 6
                case Constants.VIEW_ALL:
                    System.out.println("\nView all members selected\n");
                    handleViewAllMembers();
                    break;

                //Option 7
                case Constants.VIEW_ONE:
                    System.out.println("\nView one member by ID selected\n");
                    handleViewOneMember();
                    break;

                //Option 8
                case Constants.SUMMARY_COUNTS:
                    System.out.println("\nSummary of total members and active members selected\n");
                    handleSummaryCounts();
                    break;

                //Option 9
                case Constants.SUMMARY_REVENUE:
                    System.out.println("\nSummary of total revenue selected\n");
                    handleSummaryRevenue();
                    break;

                //Option 10
                case Constants.SUMMARY_TOP5:
                    System.out.println("\nSummary of top 5 visits selected\n");
                    handleSummaryTopFive();
                    break;

                //Option 11
                case Constants.SUMMARY_INACTIVE:
                    System.out.println("\nSummary of inactive or 0 visits selected\n");
                    handleSummaryInactiveOrZero();
                    break;

                //Option 12
                case Constants.SUMMARY_AVG_BY_TYPE:
                    System.out.println("\nSummary of average visits by type selected\n");
                    handleSummaryAverageByType();
                    break;

                case Constants.EXIT:

                    running = false;

                    printGoodbye();

                    break;
                default:

                    System.out.println("your option was invalid, please go again");
            }

            if (running) {
                System.out.println();
                pressEnterToContinue();
            }
        }
    }

    // ==============================  Print / UI Methods ====================================

    private static void printHeader() {
        System.out.println();
        System.out.println("=================================");
        System.out.println("        gym membership system");
        System.out.println("=================================");
    }

    private static void printMenu() {

        String[] menuOptions = {
                "1)  Add a new member",
                "2)  Update a member's info",
                "3)  Record a member's check in",
                "4)  Record a payment",
                "5)  Change membership's active/inactive status",
                "6)  View all the members",
                "7)  View one member by ID",
                "8)  Summary of total members and active members",
                "9)  Summary of total revenue",
                "10) Summary of top 5 visits",
                "11) Summary of inactive or 0 visits",
                "12) Summary of average visits by type",
                "0)  Exit"
        };

        for (String option : menuOptions) {
            System.out.println(option);
        }
    }

    private static void printGoodbye() {
        System.out.println();
        System.out.println("Thanks  for using our gym membership system.");
        System.out.println("bye bye!");
    }

    private static void pressEnterToContinue() {
        System.out.println("press ENTER to continue");
        INPUT.nextLine();
    }

    private static void printOneMember(Object[] member) {
        System.out.println("Member ID: " + member[Data.INDEX_ID]);
        System.out.println("Name: " + member[Data.INDEX_NAME]);
        System.out.println("Membership Type: " + member[Data.INDEX_TYPE]);
        System.out.println("Visits: " + member[Data.INDEX_VISITS]);
        System.out.println("Total Paid: $" + String.format("%.2f", (Double) member[Data.INDEX_TOTAL_PAID]));
        System.out.println("Status: " + (((Boolean) member[Data.INDEX_ACTIVE]) ? "Active" : "Inactive"));
    }

    private static void printMemberTableHeader() {
        System.out.printf("%-8s %-20s %-12s %-8s %-12s %-10s%n",
                "ID", "Name", "Type", "Visits", "Total Paid", "Status");
        System.out.println("--------------------------------------------------------------------------");
    }

    private static void printMemberTableRow(Object[] member) {
        System.out.printf("%-8d %-20s %-12s %-8d $%-11.2f %-10s%n",
                (Integer) member[Data.INDEX_ID],
                (String) member[Data.INDEX_NAME],
                (String) member[Data.INDEX_TYPE],
                (Integer) member[Data.INDEX_VISITS],
                (Double) member[Data.INDEX_TOTAL_PAID],
                ((Boolean) member[Data.INDEX_ACTIVE]) ? "Active" : "Inactive");
    }

    // ==============================  Read Methods ====================================

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!INPUT.hasNextInt()) {
            INPUT.nextLine();
            System.out.print("please enter a valid integer: ");
        }
        int value = INPUT.nextInt();
        INPUT.nextLine();
        return value;
    }

    private static double readDouble(String prompt) {
        System.out.println(prompt);

        while (! INPUT.hasNextDouble()) {
            INPUT.nextLine();
            System.out.print("please enter a valid number: ");
        }

        double value = INPUT.nextDouble();
        INPUT.nextLine();
        return value;
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return INPUT.nextLine().trim();
    }

    private static boolean readBoolean(String prompt) {
        String input = readString(prompt).toLowerCase();

        while (!input.equals("yes") && !input.equals("no")) {
            System.out.println("Please type yes or no.");
            input = readString(prompt).toLowerCase();
        }

        return input.equals("yes");
    }

    private static String readNonEmptyString(String prompt) {
        String value = readString(prompt);

        while (value.isEmpty()) {
            System.out.println("Input cannot be empty!");
            value = readString(prompt);
        }

        return value;
    }

    private static int readMenuChoice() {

        int choice = readInt(" choose one of the options: ");

        while (choice < 0 || choice > 12) {
            System.out.println("please choose a number between 0 and 12 ");
            choice = readInt("please choose one of the options: ");
        }

        return choice;
    }

    private static String readMembershipType() {
        System.out.println("Choose membership type:");
        System.out.println("1) Monthly");
        System.out.println("2) Quarterly");
        System.out.println("3) Annually");

        int choice = readInt("Enter membership type: ");

        while (choice < 1 || choice > 3) {
            System.out.println("Please choose 1, 2, or 3.");
            choice = readInt("Enter membership type: ");
        }

        if (choice == 1) {
            return Data.TYPE_MONTHLY;
        } else if (choice == 2) {
            return Data.TYPE_QUARTERLY;
        } else {
            return Data.TYPE_ANNUALLY;
        }
    }

    // ==============================  Handle Methods  ===================================

    private static void handleAddMember() {
        System.out.println("=== Add New Member ===");

        String name = readNonEmptyString("Enter member name: ");
        String type = readMembershipType();

        int id = Data.addMember(name, type);

        System.out.println("Member added successfully.");
        System.out.println("Generated member ID: " + id);
    }

    private static void handleUpdateMember() {
        System.out.println("=== Update Member Info ===");

        int id = readInt("Enter member ID: ");
        Object[] member = Data.getMemberById(id);

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        System.out.println("Current member info:");
        printOneMember(member);

        String newName = readNonEmptyString("Enter new member name: ");
        String newType = readMembershipType();

        boolean updated = Data.updateMemberInfo(id, newName, newType);

        if (updated) {
            System.out.println("Member information updated successfully.");
        } else {
            System.out.println("Update failed.");
        }
    }

    private static void handleCheckIn() {
        System.out.println("=== Record Check-In ===");

        int id = readInt("Enter member ID: ");
        boolean success = Data.recordCheckIn(id);

        if (success) {
            Object[] member = Data.getMemberById(id);
            System.out.println("Check-in recorded successfully.");
            System.out.println("Total visits is now: " + member[Data.INDEX_VISITS]);
        } else {
            System.out.println("Member not found.");
        }
    }

    private static void handlePayment() {
        System.out.println("=== Record Payment ===");

        int id = readInt("Enter member ID: ");
        double amount = readDouble("Enter payment amount: ");

        while (amount < 0) {
            System.out.println("Payment amount cannot be negative.");
            amount = readDouble("Enter payment amount: ");
        }

        boolean success = Data.recordPayment(id, amount);

        if (success) {
            Object[] member = Data.getMemberById(id);
            System.out.println("Payment recorded successfully.");
            System.out.println("Total paid is now: $" + String.format("%.2f", (Double) member[Data.INDEX_TOTAL_PAID]));
        } else {
            System.out.println("Member not found.");
        }
    }

    private static void handleToggleActive() {
        System.out.println("=== Set Member's Active / Inactive Status ===");

        int id = readInt("Enter member ID: ");
        Object[] member = Data.getMemberById(id);

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        System.out.println("Current status: " + (((Boolean) member[Data.INDEX_ACTIVE]) ? "Active" : "Inactive"));
        boolean newStatus = readBoolean("Set status to active? (yes/no): ");

        boolean success = Data.setActiveStatus(id, newStatus);

        if (success) {
            System.out.println("Member status updated successfully.");
        } else {
            System.out.println("Status update failed.");
        }
    }

    private static void handleViewAllMembers() {
        System.out.println("=== View All Members ===");

        List<Object[]> members = Data.getAllMembers();

        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }

        printMemberTableHeader();

        for (Object[] member : members) {
            printMemberTableRow(member);
        }
    }

    private static void handleViewOneMember() {
        System.out.println("=== View Member By ID ===");

        int id = readInt("Enter member ID: ");
        Object[] member = Data.getMemberById(id);

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        printOneMember(member);
    }

    private static void handleSummaryCounts() {
        System.out.println("=== Summary: Total Members and Active Members ===");
        System.out.println("Total members: " + Summaries.totalMembers());
        System.out.println("Active members: " + Summaries.activeMembers());
    }

    private static void handleSummaryRevenue() {
        System.out.println("=== Summary: Total Revenue ===");
        System.out.println("Total revenue collected: $" + String.format("%.2f", Summaries.totalRevenue()));
    }

    private static void handleSummaryTopFive() {
        System.out.println("=== Summary: Top 5 Members By Visits ===");

        List<Object[]> topMembers = Summaries.topFiveByVisits();

        if (topMembers.isEmpty()) {
            System.out.println("No members found.");
            return;
        }

        printMemberTableHeader();

        for (Object[] member : topMembers) {
            printMemberTableRow(member);
        }
    }

    private static void handleSummaryInactiveOrZero() {
        System.out.println("=== Summary: Inactive Or Zero Visits ===");
        System.out.println("Members who are inactive or have 0 visits: " + Summaries.inactiveOrZeroVisits());
    }

    private static void handleSummaryAverageByType() {
        System.out.println("=== Summary: Average Visits By Membership Type ===");

        System.out.println("Monthly average visits: " +
                String.format("%.2f", Summaries.averageVisitsByType(Data.TYPE_MONTHLY)));
        System.out.println("Quarterly average visits: " +
                String.format("%.2f", Summaries.averageVisitsByType(Data.TYPE_QUARTERLY)));
        System.out.println("Annually average visits: " +
                String.format("%.2f", Summaries.averageVisitsByType(Data.TYPE_ANNUALLY)));
    }
}