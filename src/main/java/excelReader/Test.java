package excelReader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    private static final String FILE_NAME = "src/SQL Form(1-14).xlsx";

    public static void main(String[] args) {
        ExcelReader excelReader = new ExcelReader(FILE_NAME);

        List<String> nameList = new ExcelReader(FILE_NAME).returnListOfCell("Name");

        Set<String> hobbyList = excelReader
                .mappingHobby(new ExcelReader(FILE_NAME)
                        .returnListOfCell("Hobby"));

        hobbyList.forEach(System.out::println);



    }
}
