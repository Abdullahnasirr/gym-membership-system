package gymsystem;

import javafx.fxml.FXML;
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
}