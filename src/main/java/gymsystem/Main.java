package gymsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

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
public class Main extends Application {

    private static GymSystem gymSystem;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gymView.fxml"));
        Parent root = loader.load();

        GymController controller = loader.getController();
        controller.setGymSystem(gymSystem);

        Scene scene = new Scene(root);
        stage.setTitle("Gym Membership System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        gymSystem = new GymSystem();

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

        launch(args);
    }
}