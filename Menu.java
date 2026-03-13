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
                case Constants.ADD_MEMBER:
                    System.out.println("add member selected");
                    break;

                case Constants.UPDATE_MEMBER:
                    System.out.println("update member selected");
                    break;

                case Constants.CHECK_IN:
                    System.out.println("check in selected");
                    break;

                case Constants.PAYMENT:
                    System.out.println("payment selected");
                    break;

                case Constants.VIEW_ALL:
                    System.out.println("view all members selected");
                    break;
                case Constants.EXIT:

                    running = false;

                    printGoodbye();

                    break;
                default:

                    System.out.println("your option was invalid, please go again");
            }

            System.out.println();
            pressEnterToContinue();
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
                "1) add a new member",
                "2) add update a members info",
                "3) record a members check in",
                "4) record a payment",
                "5) set a membership active or inactive",
                "6) view all the members",
                "7) view members by their ID",
                "8) summary of total members and active members",
                "9) summary of total revenue",
                "10) summary of top 5 visits",
                "11) Summary of inactive or 0 visits",
                "12) Summary of average visits by type",
                "0) Exit"
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
}