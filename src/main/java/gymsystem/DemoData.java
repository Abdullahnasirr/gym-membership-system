package gymsystem;

/**
 * GymSystem.DemoData.java
 *
 * CPSC 219 W26
 * Demo 2 - Gym GymSystem.Membership System
 *
 * Name: Wai Yan Aung
 * Date: 18 March 2026
 * Tutorial: T05
 *
 * Loads sample members into the object-oriented GymSystem.GymSystem.
 */
public class DemoData {

    /**
     * Loads sample data into the provided GymSystem.GymSystem instance.
     *
     * @param gymSystem system to populate with sample members
     */
    public static void load(GymSystem gymSystem) {
        Member m1 = new Member(gymSystem.generateMemberId(), "GymSystem.Member 1",
                "member1@email.com", "Calgary",
                new MonthlyMembership("2026-03-19", "2026-04-19"));

        Member m2 = new Member(gymSystem.generateMemberId(), "GymSystem.Member 2",
                "member2@email.com", "Calgary",
                new AnnualMembership("2026-03-19", "2027-03-19"));

        Member m3 = new Member(gymSystem.generateMemberId(), "GymSystem.Member 3",
                "member3@email.com", "Calgary",
                new QuarterlyMembership("2026-03-19", "2026-06-19"));

        Member m4 = new Member(gymSystem.generateMemberId(), "GymSystem.Member 4",
                "member4@email.com", "Calgary",
                new MonthlyMembership("2026-03-19", "2026-04-19"));

        Member m5 = new Member(gymSystem.generateMemberId(), "GymSystem.Member 5",
                "member5@email.com", "Calgary",
                new AnnualMembership("2026-03-19", "2027-03-19"));

        Member m6 = new Member(gymSystem.generateMemberId(), "GymSystem.Member 6",
                "member6@email.com", "Calgary",
                new QuarterlyMembership("2026-03-19", "2026-06-19"));

        Member m7 = new Member(gymSystem.generateMemberId(), "GymSystem.Member 7",
                "member7@email.com", "Calgary",
                new MonthlyMembership("2026-03-19", "2026-04-19"));

        Member m8 = new Member(gymSystem.generateMemberId(), "GymSystem.Member 8",
                "member8@email.com", "Calgary",
                new AnnualMembership("2026-03-19", "2027-03-19"));

        gymSystem.addMember(m1);
        gymSystem.addMember(m2);
        gymSystem.addMember(m3);
        gymSystem.addMember(m4);
        gymSystem.addMember(m5);
        gymSystem.addMember(m6);
        gymSystem.addMember(m7);
        gymSystem.addMember(m8);

        // Visits
        gymSystem.recordCheckIn(m1.getMemberId());
        gymSystem.recordCheckIn(m1.getMemberId());
        gymSystem.recordCheckIn(m1.getMemberId());

        gymSystem.recordCheckIn(m2.getMemberId());
        gymSystem.recordCheckIn(m2.getMemberId());

        gymSystem.recordCheckIn(m3.getMemberId());

        gymSystem.recordCheckIn(m4.getMemberId());
        gymSystem.recordCheckIn(m4.getMemberId());
        gymSystem.recordCheckIn(m4.getMemberId());
        gymSystem.recordCheckIn(m4.getMemberId());

        gymSystem.recordCheckIn(m5.getMemberId());

        for (int i = 0; i < 10; i++) {
            gymSystem.recordCheckIn(m8.getMemberId());
        }

        // Payments
        gymSystem.recordPayment(m1.getMemberId(), 50);
        gymSystem.recordPayment(m2.getMemberId(), 200);
        gymSystem.recordPayment(m3.getMemberId(), 120);
        gymSystem.recordPayment(m4.getMemberId(), 80);
        gymSystem.recordPayment(m5.getMemberId(), 300);
        gymSystem.recordPayment(m6.getMemberId(), 100);
        gymSystem.recordPayment(m8.getMemberId(), 1000);

        // Set some inactive
        gymSystem.setMemberActive(m6.getMemberId(), false);
        gymSystem.setMemberActive(m7.getMemberId(), false);
    }
}