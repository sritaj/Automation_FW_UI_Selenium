package utils;

import constants.FrameworkConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

public final class PropertiesFileImpl {

    private PropertiesFileImpl() {
    }

    private static Properties prop = new Properties();
    private static final HashMap<String, String> CONFIGMAP = new HashMap<>();

    static {
        try {
            File file = new File(FrameworkConstants.getPropertiesFilePath());
            FileInputStream fis = new FileInputStream(file);
            prop.load(fis);

            prop.forEach((key, value) -> CONFIGMAP.put(String.valueOf(key), String.valueOf(value)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDataFromPropertyFile(String propertyKey) {
        try{
            if(Objects.isNull(propertyKey) || Objects.isNull(CONFIGMAP.get(propertyKey))){
                System.err.println("Specified Key -> '" + propertyKey + "' is not found in config properties");
            }
        }catch (NullPointerException e){
            System.err.println("Null pointer exception" + e.getMessage());
        }
        return CONFIGMAP.get(propertyKey);
    }
}
