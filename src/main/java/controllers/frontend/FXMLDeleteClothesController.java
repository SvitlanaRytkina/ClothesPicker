package controllers.frontend;

import controllers.backend.Manager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static constants.Constants.*;

public class FXMLDeleteClothesController {
    private Manager manager;
    @FXML
    private ListView<String> pantsListView;
    @FXML
    private ListView<String> skirtListView;
    @FXML
    private ListView<String> dressListView;
    @FXML
    private ListView<String> shirtListView;
    @FXML
    private ListView<String> jumperListView;
    @FXML
    private ListView<String> outerwearListView;
    @FXML
    private ToggleGroup category;
    @FXML
    private RadioButton pants;
    @FXML
    private RadioButton skirt;
    @FXML
    private RadioButton dress;
    @FXML
    private RadioButton shirt;
    @FXML
    private RadioButton jumper;
    @FXML
    private RadioButton outerwear;
    @FXML
    private TextField index;


    public FXMLDeleteClothesController() {
        manager = new Manager();
    }

    @FXML
    private void initialize() {
        /*Set radio buttons value*/
        pants.setUserData(PANTS);
        skirt.setUserData(SKIRT);
        dress.setUserData(DRESS);
        shirt.setUserData(SHIRT);
        jumper.setUserData(JUMPER);
        outerwear.setUserData(OUTERWEAR);
        /*List view*/
        pantsListView.setItems(FXCollections.observableArrayList(manager.getAllClothes(PANTS)));
        skirtListView.setItems(FXCollections.observableArrayList(manager.getAllClothes(SKIRT)));
        dressListView.setItems(FXCollections.observableArrayList(manager.getAllClothes(DRESS)));
        shirtListView.setItems(FXCollections.observableArrayList(manager.getAllClothes(SHIRT)));
        jumperListView.setItems(FXCollections.observableArrayList(manager.getAllClothes(JUMPER)));
        outerwearListView.setItems(FXCollections.observableArrayList(manager.getAllClothes(OUTERWEAR)));
    }

    @FXML
    private void clickBackButton(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXMLMainScene.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clickDeleteButton() {
        manager.deleteClothes(getSelectedToggle(category), index.getText());
        pantsListView.setItems(FXCollections.observableArrayList(manager.getAllClothes(PANTS)));
        skirtListView.setItems(FXCollections.observableArrayList(manager.getAllClothes(SKIRT)));
        dressListView.setItems(FXCollections.observableArrayList(manager.getAllClothes(DRESS)));
        shirtListView.setItems(FXCollections.observableArrayList(manager.getAllClothes(SHIRT)));
        jumperListView.setItems(FXCollections.observableArrayList(manager.getAllClothes(JUMPER)));
        outerwearListView.setItems(FXCollections.observableArrayList(manager.getAllClothes(OUTERWEAR)));
    }

    private String getSelectedToggle(ToggleGroup toggle) {
        return toggle.getSelectedToggle().getUserData().toString();
    }

}
