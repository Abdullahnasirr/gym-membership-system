import java.util.Scanner;

public class Menu {
    private static int readMenuChoice() {

        int choice = readInt(" choose one of the options: ");

        while (choice < 0 || choice > 12) {
            System.out.println("please choose a number between 0 and 12 ");
            choice = readInt("please choose one of the options: ");
        }

        return choice;
    }

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
        }
    }
    private static void printHeader() {
        System.out.println();
        System.out.println("=================================");
        System.out.println("        gym membership system");
        System.out.println("=================================");
    }
    private static void printGoodbye() {
        System.out.println();
        System.out.println("Thanks  for using our gym membership system.");
        System.out.println("bye bye!");
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