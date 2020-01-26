package controllers.backend;

import controllers.backend.utils.ExcelUtils;
import models.clothes.Clothes;
import models.clothes.bottom.Tights;
import models.clothes.bottom.pants.Pants;
import models.clothes.bottom.skirt.Skirt;
import models.clothes.dress.Dress;
import models.clothes.top.jumper.Jumper;
import models.clothes.top.outerwear.Outerwear;
import models.clothes.top.shirt.Shirt;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

import static constants.Constants.*;
import static controllers.backend.utils.CommonUtils.getCurrentSeason;
import static controllers.backend.utils.CommonUtils.handleType;

public class ClothesController {
    public static final String EXCEL_FILE_PATH = "src/main/java/resources/resources/Clothes.xlsx";
    private static final Logger LOG = Logger.getLogger(ClothesController.class);
    private ExcelUtils utils;

    public ClothesController() {
        utils = new ExcelUtils(EXCEL_FILE_PATH);
    }

    public <T extends Clothes> boolean addClothes(T type) {
        LOG.info(String.format("Adding %s [%s] to the excel file", handleType(type), type.toString()));
        utils.add(type);
        return utils.read(type).contains(type);
    }

    public <T extends Clothes> void deleteClothes(T type, int id) {
        LOG.info(String.format("Deleting %s with id [%s] to the excel file", handleType(type), id));
        List<T> clothes = utils.read(type);
        if (clothes.size() >= id) {
            utils.delete(type, id);
        } else {
            LOG.error(String.format("Try to delete by wrong id [%s]. There are only [%s] %s", id, clothes.size(), handleType(type)));
        }
        utils.read(type);
    }

    public <T extends Clothes> List<String> getAllClothes(T type) {
        LOG.info(String.format("Getting all %s from the excel file", handleType(type)));
        return utils.read(type).stream().map(Object::toString).collect(Collectors.toList());
    }

    public List<String> chooseLook(int temperature) {
        LOG.info(String.format("Choosing the look for temperature %s", temperature));
        Map<String, Integer> clothes = chooseClothes(new Random().nextInt(5), temperature);
        if (clothes.values().size() == 1) {
            return Collections.singletonList(clothes.keySet().stream().findFirst().get());
        } else {
            List<String> look = clothes.entrySet().stream()
                    .filter(c -> !c.getKey().equals(TIGHTS))
                    .map(c -> utils.read(handleType(c.getKey())).stream().filter(el -> c.getValue().equals(el.getId())).map(Object::toString).findFirst().get())
                    .collect(Collectors.toList());
            if (clothes.containsKey(TIGHTS)) {
                look.add(clothes.entrySet().stream()
                        .filter(c -> c.getKey().equals(TIGHTS)).map(c -> Tights.getByValue(c.getValue())).map(Object::toString).findFirst().get());
            }
            return look;
        }
    }

    public Map<String, Integer> chooseClothes(int number, int temperature) {
        int casesPassed = 0;
        switch (number) {
            case 0: {
                casesPassed++;
                Pants pants = chooseClothes(new Pants(), temperature);
                Shirt shirt = chooseClothes(new Shirt(), temperature);
                Outerwear outerwear = chooseClothes(new Outerwear(), temperature);
                if (pants != null && shirt != null) {
                    Map<String, Integer> selectedClothes = new HashMap<>();
                    selectedClothes.put(PANTS, pants.getId());
                    selectedClothes.put(SHIRT, shirt.getId());
                    LOG.info(String.format("Selected clothes for today:\n[%s]\n[%s]", pants.toString(), shirt.toString()));
                    if (outerwear != null) {
                        selectedClothes.put(OUTERWEAR, outerwear.getId());
                        LOG.info(String.format("[%s]", outerwear.toString()));
                    }
                    Tights tights = chooseTights(temperature, PANTS, false);
                    if (tights != null) {
                        selectedClothes.put(TIGHTS, tights.getId());
                        LOG.info(String.format("[%s]", tights.toString()));
                    }
                    return selectedClothes;
                }
            }
            case 1: {
                casesPassed++;
                Pants pants = chooseClothes(new Pants(), temperature);
                Jumper jumper = chooseClothes(new Jumper(), temperature);
                Outerwear outerwear = chooseClothes(new Outerwear(), temperature);
                if (pants != null && jumper != null) {
                    Map<String, Integer> selectedClothes = new HashMap<>();
                    selectedClothes.put(PANTS, pants.getId());
                    selectedClothes.put(JUMPER, jumper.getId());
                    LOG.info(String.format("Selected clothes for today:\n[%s]\n[%s]", pants.toString(), jumper.toString()));
                    if (outerwear != null) {
                        selectedClothes.put(OUTERWEAR, outerwear.getId());
                        LOG.info(String.format("[%s]", outerwear.toString()));
                    }
                    Tights tights = chooseTights(temperature, PANTS, false);
                    if (tights != null) {
                        selectedClothes.put(TIGHTS, tights.getId());
                        LOG.info(String.format("[%s]", tights.toString()));
                    }
                    return selectedClothes;
                }
            }
            case 2: {
                casesPassed++;
                Skirt skirt = chooseClothes(new Skirt(), temperature);
                Shirt shirt = chooseClothes(new Shirt(), temperature);
                Outerwear outerwear = chooseClothes(new Outerwear(), temperature);
                if (skirt != null && shirt != null) {
                    Map<String, Integer> selectedClothes = new HashMap<>();
                    selectedClothes.put(SKIRT, skirt.getId());
                    selectedClothes.put(SHIRT, shirt.getId());
                    LOG.info(String.format("Selected clothes for today:\n[%s]\n[%s]", skirt.toString(), shirt.toString()));
                    if (outerwear != null) {
                        selectedClothes.put(OUTERWEAR, outerwear.getId());
                        LOG.info(String.format("[%s]", outerwear.toString()));
                    }
                    Tights tights = chooseTights(temperature, SKIRT, skirt.isWornWithTights());
                    if (tights != null) {
                        selectedClothes.put(TIGHTS, tights.getId());
                        LOG.info(String.format("[%s]", tights.toString()));
                    }
                    return selectedClothes;
                }
            }
            case 3: {
                casesPassed++;
                Skirt skirt = chooseClothes(new Skirt(), temperature);
                Jumper jumper = chooseClothes(new Jumper(), temperature);
                Outerwear outerwear = chooseClothes(new Outerwear(), temperature);
                if (skirt != null && jumper != null) {
                    Map<String, Integer> selectedClothes = new HashMap<>();
                    selectedClothes.put(SKIRT, skirt.getId());
                    selectedClothes.put(JUMPER, jumper.getId());
                    LOG.info(String.format("Selected clothes for today:\n[%s]\n[%s]", skirt.toString(), jumper.toString()));
                    if (outerwear != null) {
                        selectedClothes.put(OUTERWEAR, outerwear.getId());
                        LOG.info(String.format("[%s]", outerwear.toString()));
                    }
                    Tights tights = chooseTights(temperature, SKIRT, skirt.isWornWithTights());
                    if (tights != null) {
                        selectedClothes.put(TIGHTS, tights.getId());
                        LOG.info(String.format("[%s]", tights.toString()));
                    }
                    return selectedClothes;
                }
            }
            case 4: {
                casesPassed++;
                Dress dress = chooseClothes(new Dress(), temperature);
                Outerwear outerwear = chooseClothes(new Outerwear(), temperature);
                if (dress != null) {
                    Map<String, Integer> selectedClothes = new HashMap<>();
                    selectedClothes.put(DRESS, dress.getId());
                    LOG.info(String.format("Selected clothes for today:\n[%s]", dress.toString()));
                    if (outerwear != null) {
                        selectedClothes.put(OUTERWEAR, outerwear.getId());
                        LOG.info(String.format("[%s]", outerwear.toString()));
                    }
                    Tights tights = chooseTights(temperature, DRESS, false);
                    if (tights != null) {
                        selectedClothes.put(TIGHTS, tights.getId());
                        LOG.info(String.format("[%s]", tights.toString()));
                    }
                    return selectedClothes;
                }
            }
            default: {
                if (casesPassed == 5) {
                    Map<String, Integer> selectedClothes = new HashMap<>();
                    selectedClothes.put("Nothing to wear for this temperature!", 0);
                    LOG.info("Nothing to wear for this temperature!");
                    return selectedClothes;
                } else return chooseClothes(0, temperature);
            }
        }
    }

    private <T extends Clothes> T chooseClothes(T type, int temperature) {
        List<T> selectedClothes = utils.read(type).stream().filter(el -> el.getSeason().contains(getCurrentSeason()))
                .filter(el -> el.choose(temperature)).collect(Collectors.toList());
        if (selectedClothes.isEmpty()) {
            LOG.info("No pants were found!");
            return null;
        } else if (selectedClothes.size() == 1) {
            return selectedClothes.get(0);
        } else {
            return selectedClothes.get(new Random().nextInt(selectedClothes.size()));
        }
    }

    private Tights chooseTights(int temperature, String clothesType, boolean isWornWithTights) {
        switch (clothesType) {
            case PANTS:
                if (temperature <= -10) {
                    return Arrays.stream(Tights.values()).filter(el -> el.choose(temperature)).findFirst().get();
                }
                break;
            case SKIRT:
                if (temperature <= 10) {
                    return Arrays.stream(Tights.values()).filter(el -> el.choose(temperature)).findFirst().get();
                } else if (isWornWithTights) {
                    return Arrays.stream(Tights.values()).filter(el -> el.choose(temperature)).findFirst().get();
                }
                break;
            case DRESS:
                if (temperature <= 10) {
                    return Arrays.stream(Tights.values()).filter(el -> el.choose(temperature)).findFirst().get();
                }
                break;
            default:
                LOG.info("No tights should be worn today!");
                return null;
        }
        LOG.info("No tights should be worn today!");
        return null;
    }
}
