package excelReader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ExcelReader {

    private List<String> listOfCell = new ArrayList<>();

    private final String FILE_NAME;

    public ExcelReader(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }


    public List<String> returnListOfCell(String nameOfCell) {
        try (FileInputStream excelFile = new FileInputStream(new File(FILE_NAME))) {

            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);

            int index = 0;

            for (Row row : sheet) {
                for (Cell cell : row) {

                    if (cell.getCellType() == CellType.STRING) {
                        if (cell.getStringCellValue().toLowerCase()
                                .equals(nameOfCell.toLowerCase()))
                            index = cell.getColumnIndex();

                        if (cell.getColumnIndex() == index)
                            listOfCell.add(cell.getStringCellValue());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        listOfCell.remove("Name");
        listOfCell.remove("Hobby");
        listOfCell.remove("ID");
        listOfCell.remove("email");
        return listOfCell;

    }

    public Set<String > mappingHobby(List<String> hobbyList) {

        hobbyList = hobbyList.stream()
                .map(s -> s.replace(".", ""))
                .collect(Collectors.toList());

        String str = "";
        String[] arrStr;
        for (String s : hobbyList) {
            if (!(s.charAt(s.length() - 1) == ','))
                s += ",";
            str += s;
        }
        arrStr = str.split(",");

        hobbyList.removeAll(hobbyList);

        for (String s : arrStr) {
            hobbyList.add(s.replace(",", "").trim().toLowerCase());
        }


        return new HashSet<>(hobbyList);
    }


}
