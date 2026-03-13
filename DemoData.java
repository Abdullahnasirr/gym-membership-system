public class DemoData {

    public static void load() {

        int m1 = Data.addMember("Member 1", Data.TYPE_MONTHLY);
        int m2 = Data.addMember("Member 2", Data.TYPE_ANNUALLY);
        int m3 = Data.addMember("Member 3", Data.TYPE_QUARTERLY);
        int m4 = Data.addMember("Member 4", Data.TYPE_MONTHLY);
        int m5 = Data.addMember("Member 5", Data.TYPE_ANNUALLY);
        int m6 = Data.addMember("Member 6", Data.TYPE_QUARTERLY);
        int m7 = Data.addMember("Member 7", Data.TYPE_MONTHLY);
        int m8 = Data.addMember("Member 8", Data.TYPE_ANNUALLY);

        // Visits
        Data.recordCheckIn(m1);
        Data.recordCheckIn(m1);
        Data.recordCheckIn(m1);

        Data.recordCheckIn(m2);
        Data.recordCheckIn(m2);

        Data.recordCheckIn(m3);

        Data.recordCheckIn(m4);
        Data.recordCheckIn(m4);
        Data.recordCheckIn(m4);
        Data.recordCheckIn(m4);

        Data.recordCheckIn(m5);

        Data.recordCheckIn(m8);
        Data.recordCheckIn(m8);
        Data.recordCheckIn(m8);
        Data.recordCheckIn(m8);
        Data.recordCheckIn(m8);
        Data.recordCheckIn(m8);
        Data.recordCheckIn(m8);
        Data.recordCheckIn(m8);
        Data.recordCheckIn(m8);
        Data.recordCheckIn(m8);


        // Payments
        Data.recordPayment(m1, 50);
        Data.recordPayment(m2, 200);
        Data.recordPayment(m3, 120);
        Data.recordPayment(m4, 80);
        Data.recordPayment(m5, 300);
        Data.recordPayment(m6, 100);
        Data.recordPayment(m8, 1000);

        // Set some inactive
        Data.setActiveStatus(m6, false);
        Data.setActiveStatus(m7, false);
    }
}