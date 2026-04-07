package gymsystem;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

import java.util.List;
import java.util.Optional;

public class GymController {

    @FXML
    private TextArea outputArea;

    @FXML
    private ComboBox<String> summaryMembershipTypeComboBox;

    private GymSystem gymSystem;

    @FXML
    public void initialize() {
        if (summaryMembershipTypeComboBox != null) {
            summaryMembershipTypeComboBox.getItems().addAll(
                    GymSystem.TYPE_MONTHLY,
                    GymSystem.TYPE_QUARTERLY,
                    GymSystem.TYPE_ANNUALLY
            );
        }
    }

    public void setGymSystem(GymSystem gymSystem) {
        this.gymSystem = gymSystem;
        refreshAllMembersView();
    }

    public void handleViewAllMembers() {
        refreshAllMembersView();
    }

    private void refreshAllMembersView() {
        if (gymSystem == null || outputArea == null) {
            return;
        }

        StringBuilder sb = new StringBuilder("All Members:\n\n");
        List<Member> members = gymSystem.getAllMembers();

        if (members.isEmpty()) {
            sb.append("No members found.");
        } else {
            for (Member member : members) {
                sb.append(member).append("\n\n");
            }
        }

        outputArea.setText(sb.toString());
    }

    public void handleShowMemberCounts() {
        outputArea.setText(
                "Total Members: " + gymSystem.getTotalMembers()
                        + "\nActive Members: " + gymSystem.getActiveMembersCount()
        );
    }

    public void handleShowTotalRevenue() {
        outputArea.setText(String.format("Total Revenue: $%.2f", gymSystem.getTotalRevenue()));
    }

    public void handleShowTop5Visits() {
        StringBuilder sb = new StringBuilder("Top 5 Members By Visits:\n\n");

        List<Member> topMembers = gymSystem.getTop5MembersByVisits();

        if (topMembers.isEmpty()) {
            sb.append("No members found.");
        } else {
            for (Member member : topMembers) {
                sb.append(member).append("\n\n");
            }
        }

        outputArea.setText(sb.toString());
    }

    public void handleShowInactiveOrZeroVisits() {
        StringBuilder sb = new StringBuilder("Inactive or Zero-Visit Members:\n\n");

        List<Member> members = gymSystem.getInactiveOrZeroVisitMembers();

        if (members.isEmpty()) {
            sb.append("None found.");
        } else {
            for (Member member : members) {
                sb.append(member).append("\n\n");
            }
        }

        outputArea.setText(sb.toString());
    }

    public void handleShowAverageVisitsByType() {
        String selectedType = summaryMembershipTypeComboBox.getValue();

        if (selectedType == null || selectedType.isBlank()) {
            outputArea.setText("Please select a membership type.");
            return;
        }

        double average = gymSystem.getAverageVisitsByMembershipType(selectedType);
        outputArea.setText("Average visits for " + selectedType + " members: " + average);
    }

    public void handleSaveData() {
        TextInputDialog dialog = new TextInputDialog("gym_data.csv");
        dialog.setTitle("Save Data");
        dialog.setHeaderText("Save gym data to CSV");
        dialog.setContentText("Enter file name:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String fileName = result.get().trim();

            if (fileName.isEmpty()) {
                outputArea.setText("Save cancelled. File name cannot be empty.");
                return;
            }

            if (!fileName.endsWith(".csv")) {
                fileName += ".csv";
            }

            boolean success = GymFileManager.saveToCsv(fileName, gymSystem);

            if (success) {
                outputArea.setText("Data saved to " + fileName);
            } else {
                outputArea.setText("Failed to save data to " + fileName);
            }
        }
    }

    public void handleLoadData() {
        TextInputDialog dialog = new TextInputDialog("gym_data.csv");
        dialog.setTitle("Load Data");
        dialog.setHeaderText("Load gym data from CSV");
        dialog.setContentText("Enter file name:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String fileName = result.get().trim();

            if (fileName.isEmpty()) {
                outputArea.setText("Load cancelled. File name cannot be empty.");
                return;
            }

            if (!fileName.endsWith(".csv")) {
                fileName += ".csv";
            }

            boolean success = GymFileManager.loadFromCsv(fileName, gymSystem);

            if (success) {
                outputArea.setText("Data loaded from " + fileName + "\n\n");
                refreshAllMembersView();
            } else {
                outputArea.setText("Failed to load data from " + fileName);
            }
        }
    }
}