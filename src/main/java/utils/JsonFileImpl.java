package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.FrameworkConstants;
import enums.ConfigProperties;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public final class JsonFileImpl {

    private JsonFileImpl() {
    }

    private static HashMap<String, Object> jsonMap = new HashMap<>();

    static {
        try {
            jsonMap = new ObjectMapper().readValue(new File(FrameworkConstants.getJsonDataFilePath()),
                    new TypeReference<HashMap<String, Object>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to retrieve the key value from Json file
     *
     * @param jsonKey - The Enum for which the value needs to be extracted
     * @return value of the specified jsonKey
     */
    public static Object getDataFromJsonFile(ConfigProperties jsonKey) {
        try {
            if (Objects.isNull(jsonKey) || Objects.isNull(jsonMap.get(jsonKey.name().toLowerCase()))) {
                System.err.println("Specified Key -> '" + jsonKey + "' is not found in config properties");
            }
        } catch (NullPointerException e) {
            System.err.println("Null pointer exception" + e.getMessage());
        }
        return jsonMap.get(jsonKey.name().toLowerCase());
    }
}

