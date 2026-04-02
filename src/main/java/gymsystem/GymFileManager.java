package gymsystem;

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

            writer.println("id,name,contact,address,type,startDate,endDate,visits,totalPaid,active");

            for (Member member : gymSystem.getAllMembers()) {
                writer.println(
                        member.getMemberId() + "," +
                                member.getFullName() + "," +
                                member.getPhoneOrEmail() + "," +
                                member.getAddress() + "," +
                                member.getMembershipType() + "," +
                                member.getMembership().getStartDate() + "," +
                                member.getMembership().getEndDate() + "," +
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

                if (parts.length == 10) {
                    String id = parts[0];
                    String name = parts[1];
                    String contact = parts[2];
                    String address = parts[3];
                    String type = parts[4];
                    String startDate = parts[5];
                    String endDate = parts[6];
                    int visits = Integer.parseInt(parts[7]);
                    double totalPaid = Double.parseDouble(parts[8]);
                    boolean active = Boolean.parseBoolean(parts[9]);

                    Membership membership = createMembershipFromType(type, startDate, endDate);
                    Member member = new Member(id, name, contact, address, membership);
                    member.setTotalVisits(visits);
                    member.setTotalPaid(totalPaid);
                    member.setActive(active);
                    gymSystem.addMember(member);
                }
            }

            fileScanner.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Membership createMembershipFromType(String type, String startDate, String endDate) {
        if (type.equalsIgnoreCase("Monthly")) {
            return new MonthlyMembership(startDate, endDate);
        } else if (type.equalsIgnoreCase("Quarterly")) {
            return new QuarterlyMembership(startDate, endDate);
        } else {
            return new AnnualMembership(startDate, endDate);
        }
    }
}