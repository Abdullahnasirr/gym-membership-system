package gymsystem;

/**
 * GymSystem.Main.java
 *
 * CPSC 219 W26
 * Demo 2 - Gym GymSystem.Membership System
 *
 * Name: Wai Yan Aung
 * Date: 18 March 2026
 * Tutorial: T05
 *
 * Launches the gym membership system.
 */
public class Main {
    public static void main(String[] args) {
        GymSystem gymSystem = new GymSystem();

        if (args.length > 0) {
            boolean loaded = GymFileManager.loadFromCsv(args[0], gymSystem);

            if (loaded) {
                System.out.println("Loaded members from file: " + args[0]);
            } else {
                System.out.println("Could not load file: " + args[0]);
                System.out.println("Starting with demo data instead.");
                DemoData.load(gymSystem);
            }
        } else {
            DemoData.load(gymSystem);
        }

        GymConsoleUI ui = new GymConsoleUI(gymSystem);
        ui.start();
    }
}