package com.epam.testproject.service;

import com.epam.testproject.util.PropertiesUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateCSVFileService {
    private final static String[] NAMES = {"John", "Doe", "Jane", "Smith", "Alice", "Bob", "Ariba", "Diego", "Viktor", "Ave", "Riko", "Anna"};
    private final static Integer BASIC_SALARY = 30000;
    private final static Integer DELTA_SALARY = 60000;
    private final static String COMMA = ",";
    private final static Integer MANAGERS_MAX_ID = 300;
    private final static String NEW_LINE = "\n";


    public void generateFile(String filename) throws IOException {
        FileWriter csvWriter = new FileWriter(filename);

        generateColumnTitles(csvWriter);
        fillCsvFile(csvWriter);
        csvWriter.flush();
        csvWriter.close();
    }

    private void generateColumnTitles(FileWriter fileWriter) throws IOException {
        fileWriter.append("Id");
        fileWriter.append(COMMA);
        fileWriter.append("firstName");
        fileWriter.append(COMMA);
        fileWriter.append("lastName");
        fileWriter.append(COMMA);
        fileWriter.append("salary");
        fileWriter.append(COMMA);
        fileWriter.append("managerId");
        fileWriter.append(NEW_LINE);

    }

    private void fillCsvFile(FileWriter fileWriter) throws IOException {
        Random random = new Random();
        {
            for (int i = 1; i <= PropertiesUtil.FILE_SIZE_IN_RECORD; i++) {
                String firstName = NAMES[random.nextInt(NAMES.length)];
                String lastName = NAMES[random.nextInt(NAMES.length)];
                int salary = BASIC_SALARY + random.nextInt(DELTA_SALARY);
                int managerId = 1 + random.nextInt(MANAGERS_MAX_ID);
                fileWriter.append(Integer.toString(i));
                fileWriter.append(COMMA);
                fileWriter.append(firstName);
                fileWriter.append(COMMA);
                fileWriter.append(lastName);
                fileWriter.append(COMMA);
                fileWriter.append(Integer.toString(salary));
                fileWriter.append(COMMA);
                fileWriter.append(i == 0 ? "" : Integer.toString(managerId));
                fileWriter.append(NEW_LINE);
            }
        }
    }
}
