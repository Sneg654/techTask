package com.epam.testproject.util;

import java.util.ResourceBundle;

public class PropertiesUtil {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("dev");
    public static final Integer FILE_SIZE_IN_RECORD = Integer.valueOf(resourceBundle.getString("numbers.of.records"));
    public static final String FILE_NAME = resourceBundle.getString("creator.file.name");
    public static final Integer NORMAL_DEEP_MANAGER_LIMIT = Integer.valueOf(resourceBundle.getString("numbers.of.deep.manager"));
    public static final Double MIN_RATE = Double.valueOf(resourceBundle.getString("salary.rate.lower"));
    public static final Double MAX_RATE = Double.valueOf(resourceBundle.getString("salary.rate.upper"));

}
