package gymsystem;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class GymController {
    private GymSystem gymSystem;

    @FXML
    private TextArea outputArea;

    public void setGymSystem(GymSystem gymSystem) {
        this.gymSystem = gymSystem;
        refreshAllMembersView();
    }

    @FXML
    private void handleSave() {
        outputArea.setText("Saving data...");
    }

    @FXML
    private void handleLoad() {
        outputArea.setText("Loading data...");
    }

    @FXML
    private void handleQuit() {
        Platform.exit();
    }

    @FXML
    private void handleAbout() {
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("About");
        aboutAlert.setHeaderText("Gym Membership System");
        aboutAlert.setContentText(
                "CPSC 219 W26 (Gym Membership System Project)\n" +
                        "This application manages gym members, visits, payments, and summaries through a JavaFX graphical user interface.\n\n" +
                        "Authors: Abdullah Nasir, Brandon Aung, Ethan Chiu"
        );
        aboutAlert.showAndWait();
    }

    @FXML
    private void handleViewAllMembers() {
        refreshAllMembersView();
    }

    private void refreshAllMembersView() {
        if (outputArea == null || gymSystem == null) {
            return;
        }

        StringBuilder builder = new StringBuilder();
        for (Member member : gymSystem.getAllMembers()) {
            builder.append(member).append("\n-------------------------\n");
        }
        outputArea.setText(builder.toString());
    }

    @FXML
    private void handleAddMember() {
        outputArea.setText("Add Member clicked, not implemented yet");
    }
}