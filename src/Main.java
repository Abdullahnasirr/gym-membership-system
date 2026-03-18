/**
 * Main.java
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