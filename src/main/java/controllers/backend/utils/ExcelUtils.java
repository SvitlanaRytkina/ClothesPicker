package controllers.backend.utils;

import models.clothes.Clothes;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static constants.Constants.*;
import static controllers.backend.utils.CommonUtils.*;

public class ExcelUtils {
    private static final Logger LOG = Logger.getLogger(ExcelUtils.class);
    private String filePath;

    public ExcelUtils(String filePath) {
        this.filePath = filePath;
    }

    public <T extends Clothes> List<T> read(T type) {
        List<T> clothes = new ArrayList<>();
        try {
            XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(filePath));
            XSSFSheet sheet = book.getSheet(handleType(type));
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                clothes.add(readClothesByRow(row, type));
            }
            book.close();
        } catch (IOException e) {
            LOG.error(String.format("Error while getting the information from the excel file.\nMessage [%s]\n Error [%s]\n",
                    e.getMessage(), Arrays.toString(e.getStackTrace())));
        }
        return clothes;
    }

    public <T extends Clothes> void add(T type) {
        try {
            XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(filePath));
            XSSFSheet sheet = book.getSheet(handleType(type));
            int last = sheet.getLastRowNum();
            Row row = sheet.createRow(last + 1);
            switch (handleType(type)) {
                case PANTS:
                    addClothes(row, (Pants) type);
                    break;
                case SKIRT:
                    addClothes(row, (Skirt) type);
                    break;
                case DRESS:
                    addClothes(row, (Dress) type);
                    break;
                case SHIRT:
                    addClothes(row, (Shirt) type);
                    break;
                case JUMPER:
                    addClothes(row, (Jumper) type);
                    break;
                case OUTERWEAR:
                    addClothes(row, (Outerwear) type);
                    break;
            }
            setProperId(sheet);
            book.write(new FileOutputStream(filePath));
            book.close();
        } catch (IOException e) {
            LOG.error(String.format("Error while adding the information to the excel file.\nMessage [%s]\n Error [%s]\n",
                    e.getMessage(), Arrays.toString(e.getStackTrace())));
        }
    }

    public <T extends Clothes> void delete(T type, int rowIndex) {
        try {
            XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(filePath));
            XSSFSheet sheet = book.getSheet(handleType(type));
            int lastRowNum = sheet.getLastRowNum();
            if (rowIndex >= 0 && rowIndex < lastRowNum) {
                sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
                setProperId(sheet);
            }
            if (rowIndex == lastRowNum) {
                XSSFRow removingRow = sheet.getRow(rowIndex);
                if (removingRow != null) {
                    sheet.removeRow(removingRow);
                }
            }
            book.write(new FileOutputStream(filePath));
            book.close();
        } catch (IOException e) {
            LOG.error(String.format("Error while deleting the information from the excel file.\nMessage [%s]\n Error [%s]\n",
                    e.getMessage(), Arrays.toString(e.getStackTrace())));
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends Clothes> T readClothesByRow(Row row, T type) {
        switch (handleType(type)) {
            case PANTS:
                return (T) new Pants(getNumericCellValue(row, 0), PantsType.getByValue(getStringCellValue(row, 1)), getStringCellValue(row, 2),
                        getBooleanCellValue(row, 3), getSeasons(getStringCellValue(row, 4)), getStringCellValue(row, 5));
            case SKIRT:
                return (T) new Skirt(getNumericCellValue(row, 0), SkirtType.getByValue(getStringCellValue(row, 1)),
                        SkirtLength.getByValue(getStringCellValue(row, 2)), getStringCellValue(row, 3), getBooleanCellValue(row, 4),
                        getSeasons(getStringCellValue(row, 5)), getStringCellValue(row, 6));
            case DRESS:
                return (T) new Dress(getNumericCellValue(row, 0), DressType.getByValue(getStringCellValue(row, 1)),
                        SkirtLength.getByValue(getStringCellValue(row, 2)), getStringCellValue(row, 3),
                        getSeasons(getStringCellValue(row, 4)), getStringCellValue(row, 5));
            case SHIRT:
                return (T) new Shirt(getNumericCellValue(row, 0), ShirtType.getByValue(getStringCellValue(row, 1)),
                        Material.getByValue(getStringCellValue(row, 2)), getStringCellValue(row, 3),
                        getSeasons(getStringCellValue(row, 4)), getStringCellValue(row, 5));
            case JUMPER:
                return (T) new Jumper(getNumericCellValue(row, 0), JumperType.getByValue(getStringCellValue(row, 1)), getStringCellValue(row, 2),
                        getSeasons(getStringCellValue(row, 3)), getStringCellValue(row, 4));
            case OUTERWEAR:
                return (T) new Outerwear(getNumericCellValue(row, 0), OuterwearType.getByValue(getStringCellValue(row, 1)), getStringCellValue(row, 2),
                        getSeasons(getStringCellValue(row, 3)), getStringCellValue(row, 4));
            default:
                return null;
        }
    }

    private String getStringCellValue(Row row, int i) {
        if (row.getCell(i).getCellType() == XSSFCell.CELL_TYPE_STRING) {
            return row.getCell(i).getStringCellValue();
        }
        return "";
    }

    private int getNumericCellValue(Row row, int i) {
        if (row.getCell(i).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            return (int) row.getCell(i).getNumericCellValue();
        }
        return 0;
    }

    private boolean getBooleanCellValue(Row row, int i) {
        if (row.getCell(i).getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
            return row.getCell(i).getBooleanCellValue();
        }
        return false;
    }

    private void addClothes(Row row, Pants pants) {
        setCellValue(row, 0, pants.getId());
        setCellValue(row, 1, pants.getType().getType());
        setCellValue(row, 2, pants.getColor());
        setCellValue(row, 3, pants.isWornWithBelt());
        setCellValue(row, 4, getStringSeasons(pants.getSeason()));
        setCellValue(row, 5, pants.getComment());
    }

    private void addClothes(Row row, Skirt skirt) {
        setCellValue(row, 0, skirt.getId());
        setCellValue(row, 1, skirt.getType().getType());
        setCellValue(row, 2, skirt.getLength().getLength());
        setCellValue(row, 3, skirt.getColor());
        setCellValue(row, 4, skirt.isWornWithTights());
        setCellValue(row, 5, getStringSeasons(skirt.getSeason()));
        setCellValue(row, 6, skirt.getComment());
    }

    private void addClothes(Row row, Dress dress) {
        setCellValue(row, 0, dress.getId());
        setCellValue(row, 1, dress.getType().getType());
        setCellValue(row, 2, dress.getLength().getLength());
        setCellValue(row, 3, dress.getColor());
        setCellValue(row, 4, getStringSeasons(dress.getSeason()));
        setCellValue(row, 5, dress.getComment());
    }

    private void addClothes(Row row, Shirt shirt) {
        setCellValue(row, 0, shirt.getId());
        setCellValue(row, 1, shirt.getType().getType());
        setCellValue(row, 2, shirt.getMaterial().getMaterial());
        setCellValue(row, 3, shirt.getColor());
        setCellValue(row, 4, getStringSeasons(shirt.getSeason()));
        setCellValue(row, 5, shirt.getComment());
    }

    private void addClothes(Row row, Jumper jumper) {
        setCellValue(row, 0, jumper.getId());
        setCellValue(row, 1, jumper.getType().getType());
        setCellValue(row, 2, jumper.getColor());
        setCellValue(row, 3, getStringSeasons(jumper.getSeason()));
        setCellValue(row, 4, jumper.getComment());
    }

    private void addClothes(Row row, Outerwear outerwear) {
        setCellValue(row, 0, outerwear.getId());
        setCellValue(row, 1, outerwear.getType().getType());
        setCellValue(row, 2, outerwear.getColor());
        setCellValue(row, 3, getStringSeasons(outerwear.getSeason()));
        setCellValue(row, 4, outerwear.getComment());
    }

    private void setProperId(Sheet sheet) {
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        for (int i = 1; rowIterator.hasNext(); i++) {
            Row row = rowIterator.next();
            setCellValue(row, 0, i);
        }
    }

    private void setCellValue(Row row, int index, String value) {
        row.createCell(index).setCellValue(value);
    }

    private void setCellValue(Row row, int index, int value) {
        row.createCell(index).setCellValue(value);
    }

    private void setCellValue(Row row, int index, boolean value) {
        row.createCell(index).setCellValue(value);
    }
}