package kr.toby.refactoring.utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

    private final String filePath;
    private final ObjectMapper objectMapper;

    public JsonReader(String filePath) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();
    }

    public <T> T readJson(Class<T> valueType) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new IOException("Json file doesn't exist: " + filePath);
        }
        return objectMapper.readValue(file, valueType);
    }
}