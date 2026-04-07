package gymsystem;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;
import java.util.List;

public class GymController {

    @FXML
    private TextArea outputArea;

    @FXML
    private ComboBox<String> summaryMembershipTypeComboBox;

    private GymSystem gymSystem;

    public void setGymSystem(GymSystem gymSystem) {
        this.gymSystem = gymSystem;
        refreshAllMembersView();
    }

    // ===================== BASIC VIEW =====================

    public void handleViewAllMembers() {
        refreshAllMembersView();
    }

    private void refreshAllMembersView() {
        StringBuilder sb = new StringBuilder("All Members:\n\n");

        List<Member> members = gymSystem.getAllMembers();

        for (Member member : members) {
            sb.append(member).append("\n\n");
        }

        outputArea.setText(sb.toString());
    }

    // ===================== SUMMARY METHODS =====================

    public void handleShowMemberCounts() {
        outputArea.setText("Total Members: " + gymSystem.getTotalMembers()
                + "\nActive Members: " + gymSystem.getActiveMemberCount());
    }

    public void handleShowTotalRevenue() {
        outputArea.setText("Total Revenue: $" + gymSystem.getTotalRevenue());
    }

    public void handleShowTop5Visits() {
        StringBuilder sb = new StringBuilder("Top 5 Members By Visits:\n\n");

        for (Member member : gymSystem.getTop5MembersByVisits()) {
            sb.append(member).append("\n\n");
        }

        outputArea.setText(sb.toString());
    }

    public void handleShowInactiveOrZeroVisits() {
        StringBuilder sb = new StringBuilder();

        sb.append("Inactive Members:\n\n");
        for (Member member : gymSystem.getInactiveMembers()) {
            sb.append(member).append("\n\n");
        }

        sb.append("\nMembers With Zero Visits:\n\n");
        for (Member member : gymSystem.getMembersWithZeroVisits()) {
            sb.append(member).append("\n\n");
        }

        outputArea.setText(sb.toString());
    }

    public void handleShowAverageVisitsByType() {
        String selectedType = summaryMembershipTypeComboBox.getValue();

        if (selectedType == null || selectedType.isBlank()) {
            outputArea.setText("Please select a membership type.");
            return;
        }

        double avg = gymSystem.getAverageVisitsByMembershipType(selectedType);
        outputArea.setText("Average Visits for " + selectedType + ": " + avg);
    }

    // ===================== SAVE / LOAD =====================

    public void handleSaveData() {
        TextInputDialog dialog = new TextInputDialog("gym_data.csv");
        dialog.setTitle("Save Data");
        dialog.setHeaderText("Save gym data to CSV");
        dialog.setContentText("Enter file name:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String fileName = result.get().trim();

            if (!fileName.endsWith(".csv")) {
                fileName += ".csv";
            }

            GymFileManager.saveToCsv(fileName, gymSystem);
            outputArea.setText("Data saved to " + fileName);
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

            if (!fileName.endsWith(".csv")) {
                fileName += ".csv";
            }

            GymFileManager.loadFromCsv(fileName, gymSystem);

            outputArea.setText("Data loaded from " + fileName + "\n\n");

            refreshAllMembersView();
        }
    }
}}