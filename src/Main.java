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

        DemoData.load();

        Menu.start();
    }
}