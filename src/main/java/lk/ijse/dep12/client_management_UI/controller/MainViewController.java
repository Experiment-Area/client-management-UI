package lk.ijse.dep12.client_management_UI.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lk.ijse.dep12.client_management_UI.Client;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class MainViewController {
    public VBox vBox;
    public TextField txtID;
    public TextField txtNIC;
    public TextField txtName;
    public TextField txtAddress;
    public RadioButton rdMale;
    public RadioButton rdFemale;
    public CheckBox chkHouse;
    public VBox vBoxAudience;
    public Label lblPrint;
    public DatePicker datePicker;
    public CheckBox chkLand;
    public VBox vBoxAudience1;
    public CheckBox chkMortorbike;
    public CheckBox chkCar;
    public Button btnSave;
    public ToggleGroup grpGender;

    private ArrayList<Client> clientList = new ArrayList<>();

    private void enableUI(boolean enable) {
        vBox.setDisable(!enable);
        btnSave.setDisable(!enable);
    }

    private String generateNewClientID() {
        if (clientList.isEmpty()) return "C-0001";

        int newID = Integer.parseInt(clientList.get(clientList.size() - 1).getId()
                .replace("C-", "")) + 1;
        return "C-%04d".formatted(newID);

    }

    public void initialize() {
        for (Node node : vBox.getChildren()) {
            HBox hBox = (HBox) node;
            for (int i = 0; i < hBox.getChildren().size(); i++) {
                if (hBox.getChildren().get(i) instanceof Label lbl) {
                    lbl.setLabelFor(hBox.getChildren().get(i));
                }
            }
        }
        enableUI(false);
    }

    public void btnNewProgramOnAction(ActionEvent actionEvent) {
        enableUI(true);
        txtNIC.requestFocus();
        txtID.setText(generateNewClientID());
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        ArrayList<String> properties = new ArrayList<>();

        if (chkMortorbike.isSelected()) {
            properties.add(chkMortorbike.getText());
        }
        if (chkCar.isSelected()) {
            properties.add(chkCar.getText());
        }
        if (chkHouse.isSelected()) {
            properties.add(chkHouse.getText());
        }
        if (chkLand.isSelected()) {
            properties.add(chkLand.getText());
        }

        boolean validation = true;

        for (Control control : new Control[]{txtNIC, txtName, txtAddress, rdMale, rdFemale, datePicker, chkLand, chkHouse, chkCar, chkMortorbike}) {
            control.getStyleClass().remove("error");
        }

        if (!(chkCar.isSelected() || chkHouse.isSelected() || chkLand.isSelected() || chkMortorbike.isSelected())) {
            chkMortorbike.getStyleClass().add("error");
            chkCar.getStyleClass().add("error");
            chkLand.getStyleClass().add("error");
            chkHouse.getStyleClass().add("error");
            chkLand.requestFocus();
            validation = false;
        }
        if (datePicker.getValue() == null ||
                Period.between(datePicker.getValue(), LocalDate.now()).getYears() < 18) {
            datePicker.getStyleClass().add("error");
            datePicker.requestFocus();
            validation = false;
        }
        if (grpGender.getSelectedToggle() == null) {
            rdFemale.getStyleClass().add("error");
            rdMale.getStyleClass().add("error");
            rdMale.requestFocus();
            validation = false;
        }
        String address = txtAddress.getText();
        if (!address.isEmpty()) {
            if (address.isBlank() || address.strip().length() < 4) {
                txtAddress.getStyleClass().add("error");
                txtAddress.requestFocus();
                validation = false;
            }
        }
        String name = txtName.getText().strip();
        if (name.isEmpty() || (!isNameValid(name))) {
            txtName.getStyleClass().add("error");
            txtName.requestFocus();
            validation = false;
        }
        String nic = txtNIC.getText().strip();
        if (nic.isBlank() || (!isValidNic(nic))) {
            txtNIC.getStyleClass().add("error");
            txtNIC.requestFocus();
            validation = false;
        }
        if (!validation) return;

        Client client = new Client(txtID.getText(),
                name,
                nic,
                address,
                ((RadioButton) grpGender.getSelectedToggle()).getText(),
                ((LocalDate) datePicker.getValue()),
                properties
        );
        clientList.add(client);

        for (TextField textField : new TextField[]{txtAddress, txtNIC, txtName}) {
            textField.clear();
        }
        chkLand.setSelected(false);
        chkHouse.setSelected(false);
        chkCar.setSelected(false);
        chkMortorbike.setSelected(false);
        grpGender.selectToggle(null);
        datePicker.setValue(null);
        enableUI(false);
    }

    private boolean isValidNic(String nic) {
        if (nic.length() != 10) return false;
        if (!(nic.endsWith("v") || nic.endsWith("V"))) return false;
        String number = nic.substring(0, 9);
        for (char c : number.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private boolean isNameValid(String name) {
        for (char c : name.toCharArray()) {
            if (!(Character.isLetter(c) || Character.isSpaceChar(c))) return false;
        }
        return true;
    }
}
