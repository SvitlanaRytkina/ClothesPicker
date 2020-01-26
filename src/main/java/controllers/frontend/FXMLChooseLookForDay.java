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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class FXMLChooseLookForDay {
    private Manager manager;
    @FXML
    private TextField temperature;
    @FXML
    private ListView<String> firstLookList;
    @FXML
    private ListView<String> secondLookList;

    public FXMLChooseLookForDay() {
        manager = new Manager();
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
    private void clickChooseButton() {
        List<String> look1 = manager.selectClothes(temperature.getText());
        List<String> look2 = manager.selectClothes(temperature.getText());
        if (look1.equals(look2)) {
            look2 = manager.selectClothes(temperature.getText());
        }
        firstLookList.setItems(FXCollections.observableArrayList(look1));
        secondLookList.setItems(FXCollections.observableArrayList(look2));
    }
}
