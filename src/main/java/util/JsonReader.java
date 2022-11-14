package util;

import configs.ApplicationConfig;
import exceptions.JsonFileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class JsonReader {

    private static final Map<String, Path> JSON_FILES = new HashMap<>();

    static {
        try (Stream<Path> paths = Files.walk(Paths.get(ApplicationConfig.TEST_DATA_DIR))) {
            paths.forEach(f -> {
                if (Files.isRegularFile(f)) {
                    String[] filePath = f.toString().split("/");
                    String key = filePath[filePath.length - 1].toUpperCase();
                    JSON_FILES.put(key, f);
                }

            });

            log.info("Total Data Files :: " + JSON_FILES.size());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static <T> Path getClassName(Class<T> tClass) {
        String extractedClassName = tClass.getName().replace("[L", "").replace(";", "");
        String[] arr = extractedClassName.split("\\.");
        String className = arr[arr.length - 1].toUpperCase().concat(".JSON");
         Path path = JSON_FILES.get(className);

        if (path == null) {
            throw new JsonFileNotFoundException(className + " is not found");
        }
        return path;
    }


}
