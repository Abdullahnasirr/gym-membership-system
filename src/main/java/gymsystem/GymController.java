package gymsystem;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.time.LocalDate;

public class GymController {
    private GymSystem gymSystem;

    @FXML
    private TextArea outputArea;

    @FXML
    private TextField memberIdField;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField contactField;

    @FXML
    private TextField addressField;

    @FXML
    private ComboBox<String> membershipTypeComboBox;

    @FXML
    private TextField newNameField;

    @FXML
    private TextField newContactField;

    @FXML
    private TextField newAddressField;

    @FXML
    private TextField paymentAmountField;

    @FXML
    public void initialize() {
        membershipTypeComboBox.getItems().addAll(
                GymSystem.TYPE_MONTHLY,
                GymSystem.TYPE_QUARTERLY,
                GymSystem.TYPE_ANNUALLY
        );
    }

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
    private void handleAddMember() {
        String fullName = fullNameField.getText().trim();
        String contact = contactField.getText().trim();
        String address = addressField.getText().trim();
        String membershipType = membershipTypeComboBox.getValue();

        if (fullName.isEmpty() || contact.isEmpty() || address.isEmpty() || membershipType == null) {
            showErrorAlert("Invalid Input", "Please fill in all Add Member fields.");
            return;
        }

        if (gymSystem.isContactUsed(contact)) {
            showErrorAlert("Duplicate Contact", "That contact is already used by another member.");
            return;
        }

        String memberId = gymSystem.generateMemberId();
        LocalDate startDate = LocalDate.now();
        Membership membership;

        if (membershipType.equals(GymSystem.TYPE_MONTHLY)) {
            membership = new MonthlyMembership(
                    startDate.toString(),
                    startDate.plusMonths(1).toString()
            );
        } else if (membershipType.equals(GymSystem.TYPE_QUARTERLY)) {
            membership = new QuarterlyMembership(
                    startDate.toString(),
                    startDate.plusMonths(3).toString()
            );
        } else {
            membership = new AnnualMembership(
                    startDate.toString(),
                    startDate.plusMonths(12).toString()
            );
        }

        Member member = new Member(memberId, fullName, contact, address, membership);
        gymSystem.addMember(member);

        showInfoAlert("Member Added", "New member added successfully.\nMember ID: " + memberId);
        clearAddMemberFields();
        refreshAllMembersView();
    }

    @FXML
    private void handleUpdateMember() {
        String memberId = memberIdField.getText().trim();
        String newName = newNameField.getText().trim();
        String newContact = newContactField.getText().trim();
        String newAddress = newAddressField.getText().trim();

        if (memberId.isEmpty()) {
            showErrorAlert("Invalid Input", "Please enter a member ID.");
            return;
        }

        Member member = gymSystem.findMemberById(memberId);

        if (member == null) {
            showErrorAlert("Member Not Found", "No member was found with ID: " + memberId);
            return;
        }

        if (!paymentAmountField.getText().trim().isEmpty()) {
            showErrorAlert("Wrong Field Used",
                    "Payment amount is not part of Update Member.\n" +
                            "Clear the payment field or use Record Payment instead.");
            return;
        }

        if (newName.isEmpty() && newContact.isEmpty() && newAddress.isEmpty()) {
            showErrorAlert("No Changes", "Please enter at least one field to update.");
            return;
        }

        // Use old values if field is empty
        String updatedName = newName.isEmpty() ? member.getFullName() : newName;
        String updatedContact = newContact.isEmpty() ? member.getPhoneOrEmail() : newContact;
        String updatedAddress = newAddress.isEmpty() ? member.getAddress() : newAddress;

        // Duplicate contact check ONLY if contact is changed
        if (!updatedContact.equalsIgnoreCase(member.getPhoneOrEmail())
                && gymSystem.isContactUsed(updatedContact)) {
            showErrorAlert("Duplicate Contact", "That contact is already used by another member.");
            return;
        }

        boolean updated = gymSystem.updateMemberInfo(
                memberId,
                updatedName,
                updatedContact,
                updatedAddress
        );

        if (!updated) {
            showErrorAlert("Update Failed", "Could not update member information.");
            return;
        }

        showInfoAlert("Member Updated", "Member updated successfully.");
        clearUpdateFields();
        refreshAllMembersView();
    }

    @FXML
    private void handleRecordCheckIn() {
        String memberId = memberIdField.getText().trim();

        if (memberId.isEmpty()) {
            showErrorAlert("Invalid Input", "Please enter a member ID.");
            return;
        }

        if (!newNameField.getText().trim().isEmpty()
                || !newContactField.getText().trim().isEmpty()
                || !newAddressField.getText().trim().isEmpty()
                || !paymentAmountField.getText().trim().isEmpty()) {
            showErrorAlert("Wrong Fields Used",
                    "Record Check-In only uses the member ID field.\n" +
                            "Clear the update and payment fields first.");
            return;
        }

        Member member = gymSystem.findMemberById(memberId);

        if (member == null) {
            showErrorAlert("Member Not Found", "No member was found with ID: " + memberId);
            return;
        }

        boolean checkedIn = gymSystem.recordCheckIn(memberId);

        if (!checkedIn) {
            showErrorAlert("Check-In Failed",
                    "This member is inactive or the membership has expired.");
            return;
        }

        showInfoAlert("Check-In Recorded", "Check-in recorded successfully for member " + memberId + ".");
        clearCheckInFields();
        refreshAllMembersView();
    }

    @FXML
    private void handleRecordPayment() {
        String memberId = memberIdField.getText().trim();
        String paymentText = paymentAmountField.getText().trim();

        if (memberId.isEmpty()) {
            showErrorAlert("Invalid Input", "Please enter a member ID.");
            return;
        }

        if (paymentText.isEmpty()) {
            showErrorAlert("Invalid Input", "Please enter a payment amount.");
            return;
        }

        if (!newNameField.getText().trim().isEmpty()
                || !newContactField.getText().trim().isEmpty()
                || !newAddressField.getText().trim().isEmpty()) {
            showErrorAlert("Wrong Fields Used",
                    "Name, contact, and address fields are not part of Record Payment.\n" +
                            "Clear those fields or use Update Member instead.");
            return;
        }

        Member member = gymSystem.findMemberById(memberId);

        if (member == null) {
            showErrorAlert("Member Not Found", "No member was found with ID: " + memberId);
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(paymentText);
        } catch (NumberFormatException e) {
            showErrorAlert("Invalid Payment", "Payment amount must be a valid number.");
            return;
        }

        if (amount <= 0) {
            showErrorAlert("Invalid Payment", "Payment amount must be greater than 0.");
            return;
        }

        boolean recorded = gymSystem.recordPayment(memberId, amount);

        if (!recorded) {
            showErrorAlert("Payment Failed", "Could not record payment.");
            return;
        }

        showInfoAlert("Payment Recorded", "Payment recorded successfully for member " + memberId + ".");
        clearPaymentFields();
        refreshAllMembersView();
    }

    @FXML
    private void handleViewOneMember() {
        String memberId = memberIdField.getText().trim();

        if (memberId.isEmpty()) {
            showErrorAlert("Invalid Input", "Please enter a member ID.");
            return;
        }

        Member member = gymSystem.findMemberById(memberId);

        if (member == null) {
            showErrorAlert("Member Not Found", "No member was found with ID: " + memberId);
            return;
        }

        outputArea.setText(member + "\n-------------------------\n");
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

    private void clearAddMemberFields() {
        fullNameField.clear();
        contactField.clear();
        addressField.clear();
        membershipTypeComboBox.setValue(null);
    }

    private void clearUpdateFields() {
        memberIdField.clear();
        newNameField.clear();
        newContactField.clear();
        newAddressField.clear();
    }

    private void clearCheckInFields() {
        memberIdField.clear();
    }

    private void clearPaymentFields() {
        memberIdField.clear();
        paymentAmountField.clear();
    }

    private void showErrorAlert(String title, String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(title);
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }

    private void showInfoAlert(String title, String message) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle(title);
        infoAlert.setHeaderText(null);
        infoAlert.setContentText(message);
        infoAlert.showAndWait();
    }
}