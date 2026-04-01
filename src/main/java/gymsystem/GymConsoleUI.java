package gymsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class GymConsoleUI {
    private GymSystem gymSystem;
    private Scanner scanner;

    public GymConsoleUI(GymSystem gymSystem) {
        this.gymSystem = gymSystem;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            showMainMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    handleAddMember();
                    break;
                case 2:
                    handleUpdateMember();
                    break;
                case 3:
                    handleRecordCheckIn();
                    break;
                case 4:
                    handleRecordPayment();
                    break;
                case 5:
                    handleSetMemberActive();
                    break;
                case 6:
                    handleViewAllMembers();
                    break;
                case 7:
                    handleViewOneMember();
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                    handleShowSummaries(choice);
                    break;
                case 13:
                    handleLoadFromFile();
                    break;
                case 14:
                    handleSaveToFile();
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

            if (running) {
                System.out.println("\nPress ENTER to continue...");
                scanner.nextLine();
            }
        }
    }

    public void showMainMenu() {
        System.out.println("\n=== Gym GymSystem.Membership System ===");
        System.out.println("1) Add member");
        System.out.println("2) Update member info");
        System.out.println("3) Record member check-in");
        System.out.println("4) Record payment");
        System.out.println("5) Set membership active/inactive");
        System.out.println("6) View all members");
        System.out.println("7) View one member by ID");
        System.out.println("8) Summary of total members and active members");
        System.out.println("9) Summary of total revenue");
        System.out.println("10) Summary of top 5 visits");
        System.out.println("11) Summary of inactive or 0 visits");
        System.out.println("12) Summary of average visits by type");
        System.out.println("13) Load from file");
        System.out.println("14) Save to file");
        System.out.println("0) Exit");
    }

    public void handleAddMember() {
        String memberId = gymSystem.generateMemberId();
        String fullName = readString("Enter full name: ");
        String phoneOrEmail = readString("Enter phone or email: ");
        String address = readString("Enter address: ");

        System.out.println("Choose membership type:");
        System.out.println("1) Monthly");
        System.out.println("2) Quarterly");
        System.out.println("3) Annually");
        int membershipChoice = readInt("Enter choice: ");

        LocalDate startDate = LocalDate.now();
        Membership membership;

        if (membershipChoice == 1) {
            membership = new MonthlyMembership(startDate.toString(), startDate.plusMonths(1).toString());
        } else if (membershipChoice == 2) {
            membership = new QuarterlyMembership(startDate.toString(), startDate.plusMonths(3).toString());
        } else {
            membership = new AnnualMembership(startDate.toString(), startDate.plusMonths(12).toString());
        }

        Member member = new Member(memberId, fullName, phoneOrEmail, address, membership);
        gymSystem.addMember(member);

        System.out.println("GymSystem.Member added successfully.");
        System.out.println("New member ID: " + memberId);
    }

    public void handleUpdateMember() {
        String memberId = readString("Enter member ID: ");
        String fullName = readString("Enter new full name: ");
        String phoneOrEmail = readString("Enter new phone or email: ");
        String address = readString("Enter new address: ");

        boolean updated = gymSystem.updateMemberInfo(memberId, fullName, phoneOrEmail, address);

        if (updated) {
            System.out.println("GymSystem.Member updated successfully.");
        } else {
            System.out.println("GymSystem.Member not found.");
        }
    }

    public void handleRecordCheckIn() {
        String memberId = readString("Enter member ID: ");
        boolean success = gymSystem.recordCheckIn(memberId);

        if (success) {
            System.out.println("Check-in recorded successfully.");
        } else {
            System.out.println("GymSystem.Member not found.");
        }
    }

    public void handleRecordPayment() {
        String memberId = readString("Enter member ID: ");
        double amount = readDouble("Enter payment amount: ");

        boolean success = gymSystem.recordPayment(memberId, amount);

        if (success) {
            System.out.println("GymSystem.Payment recorded successfully.");
        } else {
            System.out.println("GymSystem.Member not found.");
        }
    }

    public void handleSetMemberActive() {
        String memberId = readString("Enter member ID: ");
        String answer = readString("Set active? (yes/no): ").toLowerCase();

        boolean active = answer.equals("yes") || answer.equals("y");
        boolean success = gymSystem.setMemberActive(memberId, active);

        if (success) {
            System.out.println("GymSystem.Member active status updated.");
        } else {
            System.out.println("GymSystem.Member not found.");
        }
    }

    public void handleViewAllMembers() {
        ArrayList<Member> members = gymSystem.getAllMembers();

        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }

        for (Member member : members) {
            System.out.println(member);
            System.out.println("-------------------------");
        }
    }

    public void handleViewOneMember() {
        String memberId = readString("Enter member ID: ");
        Member member = gymSystem.findMemberById(memberId);

        if (member == null) {
            System.out.println("GymSystem.Member not found.");
        } else {
            System.out.println(member);
        }
    }

    public void handleShowSummaries(int choice) {
        switch (choice) {
            case 8:
                System.out.println("Total members: " + gymSystem.getTotalMembers());
                System.out.println("Active members: " + gymSystem.getActiveMembersCount());
                break;
            case 9:
                System.out.println("Total revenue: $" + gymSystem.getTotalRevenue());
                break;
            case 10:
                System.out.println("Top 5 members by visits:");
                ArrayList<Member> topMembers = gymSystem.getTop5MembersByVisits();
                for (Member member : topMembers) {
                    System.out.println(member);
                    System.out.println("-------------------------");
                }
                break;
            case 11:
                System.out.println("Inactive or zero-visit members:");
                ArrayList<Member> inactiveMembers = gymSystem.getInactiveOrZeroVisitMembers();
                if (inactiveMembers.isEmpty()) {
                    System.out.println("None found.");
                } else {
                    for (Member member : inactiveMembers) {
                        System.out.println(member);
                        System.out.println("-------------------------");
                    }
                }
                break;
            case 12:
                System.out.println("Choose membership type:");
                System.out.println("1) Monthly");
                System.out.println("2) Quarterly");
                System.out.println("3) Annually");
                int membershipChoice = readInt("Enter choice: ");

                String membershipType;

                if (membershipChoice == 1) {
                    membershipType = "Monthly";
                } else if (membershipChoice == 2) {
                    membershipType = "Quarterly";
                } else {
                    membershipType = "Annually";
                }

                double average = gymSystem.getAverageVisitsByMembershipType(membershipType);
                System.out.println("Average visits for " + membershipType + " members: " + average);
                break;

            default:
                System.out.println("Invalid summary option.");
        }
    }

    public void handleLoadFromFile() {
        String fileName = readString("Enter CSV file name to load: ");
        boolean success = GymFileManager.loadFromCsv(fileName, gymSystem);

        if (success) {
            System.out.println("GymSystem.Data loaded successfully.");
        } else {
            System.out.println("Failed to load data from file.");
        }
    }

    public void handleSaveToFile() {
        String fileName = readString("Enter CSV file name to save: ");
        boolean success = GymFileManager.saveToCsv(fileName, gymSystem);

        if (success) {
            System.out.println("GymSystem.Data saved successfully.");
        } else {
            System.out.println("Failed to save data to file.");
        }
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Please enter a valid integer: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.print("Please enter a valid number: ");
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}