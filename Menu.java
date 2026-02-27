import java.util.Scanner;

public class Menu {

    private static final Scanner INPUT = new Scanner(System.in);

    public static void start() {

        boolean functionrunning = true;

        while (functionrunning) {

            printMenu();

            int makeachoice = readInt("please choose one of the options: ");

            switch (makeachoice) {

                case 0:
                    functionrunning = false;
                    System.out.println("bye!!!");
                    break;
                default:
                    System.out.println("your option was invalid, please go again");
            }

            System.out.println();
        }
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
        INPUT.nextLine(); // consume newline
        return value;
    }
}
