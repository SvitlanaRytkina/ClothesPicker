package controllers.frontend;

import constants.Constants;
import controllers.backend.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.clothes.dress.DressType;
import models.clothes.top.jumper.JumperType;
import models.clothes.top.shirt.ShirtType;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static constants.Constants.JUMPER;
import static constants.Constants.*;
import static models.clothes.bottom.pants.PantsType.*;
import static models.clothes.bottom.skirt.SkirtLength.*;
import static models.clothes.bottom.skirt.SkirtType.A_LINE;
import static models.clothes.bottom.skirt.SkirtType.*;
import static models.clothes.dress.DressType.*;
import static models.clothes.top.jumper.JumperType.*;
import static models.clothes.top.outerwear.OuterwearType.*;
import static models.clothes.top.shirt.Material.*;
import static models.clothes.top.shirt.ShirtType.*;

public class FXMLAddClothesController {
    private Manager manager;
    @FXML
    private ToggleGroup pantsType;
    @FXML
    private RadioButton jeans;
    @FXML
    private RadioButton shorts;
    @FXML
    private RadioButton leggings;
    @FXML
    private RadioButton trousers;
    @FXML
    private RadioButton cargo;
    @FXML
    private TextField pantsColor;
    @FXML
    private ToggleGroup isWornWithBelt;
    @FXML
    private RadioButton wornWithBelt;
    @FXML
    private RadioButton notWornWithBelt;
    @FXML
    private CheckBox pantsWinterCheckBox;
    @FXML
    private CheckBox pantsSpringCheckBox;
    @FXML
    private CheckBox pantsSummerCheckBox;
    @FXML
    private CheckBox pantsAutumnCheckBox;
    @FXML
    private TextField pantsComment;
    @FXML
    private ToggleGroup skirtType;
    @FXML
    private RadioButton aLineSkirt;
    @FXML
    private RadioButton accordionSkirt;
    @FXML
    private RadioButton tulipSkirt;
    @FXML
    private RadioButton circleSkirt;
    @FXML
    private RadioButton pencilSkirt;
    @FXML
    private RadioButton straightSkirt;
    @FXML
    private ToggleGroup skirtLength;
    @FXML
    private RadioButton maxiSkirt;
    @FXML
    private RadioButton mediumSkirt;
    @FXML
    private RadioButton miniSkirt;
    @FXML
    private ToggleGroup isWornWithTights;
    @FXML
    private RadioButton wornWithTights;
    @FXML
    private RadioButton notWornWithTights;
    @FXML
    private CheckBox skirtWinterCheckBox;
    @FXML
    private CheckBox skirtSpringCheckBox;
    @FXML
    private CheckBox skirtSummerCheckBox;
    @FXML
    private CheckBox skirtAutumnCheckBox;
    @FXML
    private TextField skirtColor;
    @FXML
    private TextField skirtComment;
    @FXML
    private ToggleGroup dressType;
    @FXML
    private RadioButton shiftDress;
    @FXML
    private RadioButton shirtDress;
    @FXML
    private RadioButton bodyconDress;
    @FXML
    private RadioButton sheathDress;
    @FXML
    private RadioButton aLineDress;
    @FXML
    private ToggleGroup dressLength;
    @FXML
    private RadioButton maxiDress;
    @FXML
    private RadioButton mediumDress;
    @FXML
    private RadioButton miniDress;
    @FXML
    private CheckBox dressWinterCheckBox;
    @FXML
    private CheckBox dressSpringCheckBox;
    @FXML
    private CheckBox dressSummerCheckBox;
    @FXML
    private CheckBox dressAutumnCheckBox;
    @FXML
    private TextField dressColor;
    @FXML
    private TextField dressComment;
    @FXML
    private ToggleGroup shirtType;
    @FXML
    private RadioButton shiftShirt;
    @FXML
    private RadioButton shortSleeveShirt;
    @FXML
    private RadioButton shirt;
    @FXML
    private RadioButton blouse;
    @FXML
    private RadioButton gypsy;
    @FXML
    private TextField shirtColor;
    @FXML
    private ToggleGroup material;
    @FXML
    private RadioButton cotton;
    @FXML
    private RadioButton linen;
    @FXML
    private RadioButton denim;
    @FXML
    private RadioButton polyester;
    @FXML
    private CheckBox shirtWinterCheckBox;
    @FXML
    private CheckBox shirtSpringCheckBox;
    @FXML
    private CheckBox shirtSummerCheckBox;
    @FXML
    private CheckBox shirtAutumnCheckBox;
    @FXML
    private TextField shirtComment;
    @FXML
    private ToggleGroup jumperType;
    @FXML
    private RadioButton sleevelessShirt;
    @FXML
    private RadioButton tshirt;
    @FXML
    private RadioButton hoody;
    @FXML
    private RadioButton sweatshirt;
    @FXML
    private RadioButton jumper;
    @FXML
    private RadioButton turtleneckShirt;
    @FXML
    private TextField jumperColor;
    @FXML
    private CheckBox jumperWinterCheckBox;
    @FXML
    private CheckBox jumperSpringCheckBox;
    @FXML
    private CheckBox jumperSummerCheckBox;
    @FXML
    private CheckBox jumperAutumnCheckBox;
    @FXML
    private TextField jumperComment;
    @FXML
    private ToggleGroup outerwearType;
    @FXML
    private RadioButton cardigan;
    @FXML
    private RadioButton blazer;
    @FXML
    private RadioButton jacket;
    @FXML
    private TextField outerwearColor;
    @FXML
    private CheckBox outerwearWinterCheckBox;
    @FXML
    private CheckBox outerwearSpringCheckBox;
    @FXML
    private CheckBox outerwearSummerCheckBox;
    @FXML
    private CheckBox outerwearAutumnCheckBox;
    @FXML
    private TextField outerwearComment;


    public FXMLAddClothesController() {
        manager = new Manager();
    }

    @FXML
    private void initialize() {
        /*Pants*/
        jeans.setUserData(JEANS.getType());
        shorts.setUserData(SHORTS.getType());
        leggings.setUserData(LEGGINGS.getType());
        trousers.setUserData(TROUSERS.getType());
        cargo.setUserData(CARGO.getType());
        wornWithBelt.setUserData("Yes");
        notWornWithBelt.setUserData("No");
        /*Skirt*/
        aLineSkirt.setUserData(A_LINE.getType());
        accordionSkirt.setUserData(ACCORDION.getType());
        tulipSkirt.setUserData(TULIP.getType());
        circleSkirt.setUserData(CIRCLE.getType());
        pencilSkirt.setUserData(PENCIL.getType());
        straightSkirt.setUserData(STRAIGHT.getType());
        maxiSkirt.setUserData(MAXI.getLength());
        mediumSkirt.setUserData(MIDI.getLength());
        miniSkirt.setUserData(MINI.getLength());
        wornWithTights.setUserData("Yes");
        notWornWithTights.setUserData("No");
        /*Dress*/
        shiftDress.setUserData(SHIFT.getType());
        shirtDress.setUserData(DressType.SHIRT.getType());
        bodyconDress.setUserData(BODYCON.getType());
        sheathDress.setUserData(SHEATH.getType());
        aLineDress.setUserData(DressType.A_LINE.getType());
        maxiDress.setUserData(MAXI.getLength());
        mediumDress.setUserData(MIDI.getLength());
        miniDress.setUserData(MINI.getLength());
        /*Shirt*/
        shiftShirt.setUserData(ShirtType.SHIRT.getType());
        shortSleeveShirt.setUserData(SHORT_SLEEVE_SHORT.getType());
        shirt.setUserData(ShirtType.SHIRT.getType());
        blouse.setUserData(BLOUSE.getType());
        gypsy.setUserData(GYPSY.getType());
        cotton.setUserData(COTTON.getMaterial());
        linen.setUserData(LINEN.getMaterial());
        denim.setUserData(DENIM.getMaterial());
        polyester.setUserData(POLYESTER.getMaterial());
        /*Jumper*/
        sleevelessShirt.setUserData(SLEEVELESS_SHIRT.getType());
        tshirt.setUserData(T_SHIRT.getType());
        hoody.setUserData(HOODY.getType());
        sweatshirt.setUserData(SWEATSHIRT.getType());
        jumper.setUserData(JumperType.JUMPER.getType());
        turtleneckShirt.setUserData(TURTLENECK_SHIRT.getType());
        /*Outerwear*/
        cardigan.setUserData(CARDIGAN.getType());
        blazer.setUserData(BLAZER.getType());
        jacket.setUserData(JACKET.getType());
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
    private void clickAddPants() {
        manager.addClothes(Arrays.asList(PANTS, getSelectedToggle(pantsType), null, null, pantsColor.getText(),
                getSelectedToggle(isWornWithBelt),
                getSelectedSeasons(pantsWinterCheckBox, pantsSpringCheckBox, pantsSummerCheckBox, pantsAutumnCheckBox),
                pantsComment.getText()));
    }

    @FXML
    private void clickAddSkirt() {
        manager.addClothes(Arrays.asList(SKIRT, getSelectedToggle(skirtType), getSelectedToggle(skirtLength), null, skirtColor.getText(),
                getSelectedToggle(isWornWithTights),
                getSelectedSeasons(skirtWinterCheckBox, skirtSpringCheckBox, skirtSummerCheckBox, skirtAutumnCheckBox),
                skirtComment.getText()));
    }

    @FXML
    private void clickAddDress() {
        manager.addClothes(Arrays.asList(DRESS, getSelectedToggle(dressType), getSelectedToggle(dressLength), null, dressColor.getText(),
                null, getSelectedSeasons(dressWinterCheckBox, dressSpringCheckBox, dressSummerCheckBox, dressAutumnCheckBox),
                dressComment.getText()));
    }

    @FXML
    private void clickAddShirt() {
        manager.addClothes(Arrays.asList(Constants.SHIRT, getSelectedToggle(shirtType), null, getSelectedToggle(material), shirtColor.getText(),
                null, getSelectedSeasons(shirtWinterCheckBox, shirtSpringCheckBox, shirtSummerCheckBox, shirtAutumnCheckBox),
                shirtComment.getText()));
    }

    @FXML
    private void clickAddJumper() {
        manager.addClothes(Arrays.asList(JUMPER, getSelectedToggle(jumperType), null, null, jumperColor.getText(),
                null, getSelectedSeasons(jumperWinterCheckBox, jumperSpringCheckBox, jumperSummerCheckBox, jumperAutumnCheckBox),
                jumperComment.getText()));
    }

    @FXML
    private void clickAddOuterwear() {
        manager.addClothes(Arrays.asList(OUTERWEAR, getSelectedToggle(outerwearType), null, null, outerwearColor.getText(),
                null, getSelectedSeasons(outerwearWinterCheckBox, outerwearSpringCheckBox, outerwearSummerCheckBox, outerwearAutumnCheckBox),
                outerwearComment.getText()));
    }

    private String getSelectedToggle(ToggleGroup toggle) {
        return toggle.getSelectedToggle().getUserData().toString();
    }

    private String getSelectedSeasons(CheckBox winterCheckBox, CheckBox springCheckBox, CheckBox summerCheckBox, CheckBox autumnCheckBox) {
        return Stream.of(winterCheckBox, springCheckBox, summerCheckBox, autumnCheckBox)
                .filter(CheckBox::isSelected).map(Labeled::getText).collect(Collectors.joining(", "));
    }
}
