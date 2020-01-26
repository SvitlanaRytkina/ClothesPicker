package controllers.backend;

import models.clothes.bottom.pants.Pants;
import models.clothes.bottom.pants.PantsType;
import models.clothes.bottom.skirt.Skirt;
import models.clothes.bottom.skirt.SkirtLength;
import models.clothes.bottom.skirt.SkirtType;
import models.clothes.dress.Dress;
import models.clothes.dress.DressType;
import models.clothes.top.jumper.Jumper;
import models.clothes.top.jumper.JumperType;
import models.clothes.top.outerwear.Outerwear;
import models.clothes.top.outerwear.OuterwearType;
import models.clothes.top.shirt.Material;
import models.clothes.top.shirt.Shirt;
import models.clothes.top.shirt.ShirtType;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;

import static constants.Constants.*;
import static controllers.backend.utils.CommonUtils.getSeasons;

/**
 * FACADE
 **/
public class Manager {
    private static final Logger LOG = Logger.getLogger(Manager.class);
    private ClothesController controller;

    public Manager() {
        controller = new ClothesController();
    }

    // category - 0, type - 1, length - 2, material - 3, color - 4, answer - 5, seasons - 6, comment - 7
    public void addClothes(List<String> params) {
        LOG.info("Got parameters from UI. Adding the clothes to excel file.");
        switch (params.get(0)) {
            case PANTS:
                controller.addClothes(new Pants(0, PantsType.getByValue(params.get(1)), params.get(4),
                        getBooleanValue(params.get(5)), getSeasons(params.get(6)), params.get(7)));
                return;
            case SKIRT:
                controller.addClothes(new Skirt(0, SkirtType.getByValue(params.get(1)), SkirtLength.getByValue(params.get(2)),
                        params.get(4), getBooleanValue(params.get(5)), getSeasons(params.get(6)), params.get(7)));
                return;

            case DRESS:
                controller.addClothes(new Dress(0, DressType.getByValue(params.get(1)), SkirtLength.getByValue(params.get(2)),
                        params.get(4), getSeasons(params.get(6)), params.get(7)));
                return;

            case SHIRT:
                controller.addClothes(new Shirt(0, ShirtType.getByValue(params.get(1)), Material.getByValue(params.get(3)),
                        params.get(4), getSeasons(params.get(6)), params.get(7)));
                return;

            case JUMPER:
                controller.addClothes(new Jumper(0, JumperType.getByValue(params.get(1)), params.get(4),
                        getSeasons(params.get(6)), params.get(7)));
                return;

            case OUTERWEAR:
                controller.addClothes(new Outerwear(0, OuterwearType.getByValue(params.get(1)), params.get(4),
                        getSeasons(params.get(6)), params.get(7)));
        }
    }

    public void deleteClothes(String category, String index) {
        LOG.info("Got parameters from UI. Deleting the clothes from excel file.");
        if (index.matches(".*\\d.*")) {
            switch (category) {
                case PANTS:
                    controller.deleteClothes(new Pants(), Integer.parseInt(index));
                    break;
                case SKIRT:
                    controller.deleteClothes(new Skirt(), Integer.parseInt(index));
                    break;
                case DRESS:
                    controller.deleteClothes(new Dress(), Integer.parseInt(index));
                    break;
                case SHIRT:
                    controller.deleteClothes(new Shirt(), Integer.parseInt(index));
                    break;
                case JUMPER:
                    controller.deleteClothes(new Jumper(), Integer.parseInt(index));
                    break;
                case OUTERWEAR:
                    controller.deleteClothes(new Outerwear(), Integer.parseInt(index));
                    break;
            }
        } else {
            LOG.error(String.format("Try to delete by wrong id [%s]", index));
        }
    }

    public List<String> selectClothes(String temperature) {
        LOG.info("Got parameters from UI. Choosing the look.");
        if (temperature.matches(".*\\d.*")) {
            return controller.chooseLook(Integer.parseInt(temperature));
        } else {
            LOG.error(String.format("Try to choose the look by wrong temperature [%s]", temperature));
            return Collections.singletonList("Nothing to wear for this temperature!");
        }
    }

    public List<String> getAllClothes(String category) {
        LOG.info("Getting the clothes from excel file.");
        switch (category) {
            case PANTS:
                return controller.getAllClothes(new Pants());
            case SKIRT:
                return controller.getAllClothes(new Skirt());
            case DRESS:
                return controller.getAllClothes(new Dress());
            case SHIRT:
                return controller.getAllClothes(new Shirt());
            case JUMPER:
                return controller.getAllClothes(new Jumper());
            case OUTERWEAR:
                return controller.getAllClothes(new Outerwear());
            default:
                return null;
        }
    }

    private boolean getBooleanValue(String answer) {
        return "Yes".equals(answer);
    }
}
