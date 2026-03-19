/**
 * Main.java
 *
 * CPSC 219 W26
 * Demo 2 - Gym Membership System
 *
 * Name: Wai Yan Aung
 *  Date: 18 March 2026
 *  Tutorial: T05
 *
 * Launches the gym membership system.
 */
public class Main {
    public static void main(String[] args) {

        GymSystem gymSystem = new GymSystem();

        // Temporary Demo 2 flow:
        // load sample data into the OO backend
        DemoData.load(gymSystem);

        // TODO:
        // Connect Menu/GymConsoleUI to this gymSystem object.
        // Example future flow:
        // GymConsoleUI ui = new GymConsoleUI(gymSystem);
        // ui.start();

        System.out.println("GymSystem initialized with " +
                gymSystem.getTotalMembers() + " demo members.");
    }
}