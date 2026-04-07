package gymsystem;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Handles saving and loading gym data to and from CSV files.
 */
public class GymFileManager {

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
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loadFromCsv(String filename, GymSystem gymSystem) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));

            gymSystem.clearAllData();

            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
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
        if (type.equalsIgnoreCase(GymSystem.TYPE_MONTHLY)) {
            return new MonthlyMembership(startDate, endDate);
        } else if (type.equalsIgnoreCase(GymSystem.TYPE_QUARTERLY)) {
            return new QuarterlyMembership(startDate, endDate);
        } else {
            return new AnnualMembership(startDate, endDate);
        }
    }
}