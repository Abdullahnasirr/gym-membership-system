import java.util.Scanner;

public class Menu {

    private static final Scanner INPUT = new Scanner(System.in);

    public static void start() {

        boolean running = true;

        while (running) {
            printHeader();
            printMenu();

            int choice = readInt("please choose one of the options: ");

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

                    System.out.println("bye!!!");

                    break;
                default:

                    System.out.println("your option was invalid, please go again");
            }

            System.out.println();
        }
    }
    private static void printHeader() {
        System.out.println();
        System.out.println("=================================");
        System.out.println("        gym membership system");
        System.out.println("=================================");
    }
    private static void printMenu() {
        System.out.println("==== Gym Membership System ====");

        System.out.println("1) add a new member");

        System.out.println("2) add update a members info");

        System.out.println("3) record a members check in");

        System.out.println("4) record a payment");

        System.out.println("5) set a membership active or inactive");

        System.out.println("6) view all the members");

        System.out.println("7) view members by their ID");

        System.out.println("8) summary of total members and active members");

        System.out.println("9) summary of total revenue");

        System.out.println("10) summary of top 5 visits");

        System.out.println("11) Summary of inactive or 0 visits");

        System.out.println("12) Summary of average visits by type");

        System.out.println("0) Exit");
    }

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

    private static String readString(String prompt) {
        System.out.print(prompt);
        return INPUT.nextLine().trim();
    }
}