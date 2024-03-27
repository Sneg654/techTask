package com.epam.testproject.service;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class GenerateCSVFileServiceTest {

    private static final String FILE_NAME = "Test_file_CSV";

    @Before
    public void setUp() {
        final File newFile = new File(FILE_NAME); // filePath is a valid path

        if (newFile.exists()) {
            newFile.delete();
        }
    }

    @Test
    public void shouldCheckFileWith1000RecordsCreated() throws IOException {
        //given
        GenerateCSVFileService generateCSVFileService = new GenerateCSVFileService();

        //when
        generateCSVFileService.generateFile(FILE_NAME);

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            assertEquals("Id,firstName,lastName,salary,managerId", br.readLine());
            assertEquals(1000, br.lines().collect(Collectors.toList()).size());

        }

    }
}
