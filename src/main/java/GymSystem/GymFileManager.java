package GymSystem;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Handles saving and loading gym data to and from CSV files.
 *
 * @author Ethan Chiu
 */
public class GymFileManager {

    /**
     * Saves all members from the gym system into a CSV file.
     *
     * @param filename output CSV filename
     * @param gymSystem system to save
     * @return true if save succeeds, false otherwise
     */
    public static boolean saveToCsv(String filename, GymSystem gymSystem) {
        try {
            PrintWriter writer = new PrintWriter(new File(filename));

            writer.println("id,name,contact,address,type,visits,totalPaid,active");

            for (Member member : gymSystem.getAllMembers()) {
                writer.println(
                        member.getMemberId() + "," +
                                member.getFullName() + "," +
                                member.getPhoneOrEmail() + "," +
                                member.getAddress() + "," +
                                member.getMembershipType() + "," +
                                member.getTotalVisits() + "," +
                                member.getTotalPaid() + "," +
                                member.isActive()
                );
            }

            writer.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Loads member data from a CSV file into the gym system.
     *
     * @param filename input CSV filename
     * @param gymSystem system to load into
     * @return true if load succeeds, false otherwise
     */
    public static boolean loadFromCsv(String filename, GymSystem gymSystem) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));

            gymSystem.clearAllData();

            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine(); // skip header
            }

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 8) {
                    String id = parts[0];
                    String name = parts[1];
                    String contact = parts[2];
                    String address = parts[3];
                    String type = parts[4];
                    int visits = Integer.parseInt(parts[5]);
                    double totalPaid = Double.parseDouble(parts[6]);
                    boolean active = Boolean.parseBoolean(parts[7]);

                    Membership membership = createMembershipFromType(type);
                    Member member = new Member(id, name, contact, address, membership);
                    gymSystem.addMember(member);

                    for (int i = 0; i < visits; i++) {
                        gymSystem.recordCheckIn(id);
                    }

                    gymSystem.recordPayment(id, totalPaid);
                    gymSystem.setMemberActive(id, active);
                }
            }

            fileScanner.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Membership createMembershipFromType(String type) {
        if (type.equalsIgnoreCase("Monthly")) {
            return new MonthlyMembership("2026-03-19", "2026-04-19");
        } else if (type.equalsIgnoreCase("Quarterly")) {
            return new QuarterlyMembership("2026-03-19", "2026-06-19");
        } else {
            return new AnnualMembership("2026-03-19", "2027-03-19");
        }
    }
}