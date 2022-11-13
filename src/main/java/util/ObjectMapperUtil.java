package util;

import com.google.gson.Gson;

public class ObjectMapperUtil {

    private static final Gson GSON = new Gson();


    public static <T> T mapStringToObject(Class<T> tClass, String object) {
        return GSON.fromJson(object, tClass);
    }


    public static <T> T[] mapStringArrayToObjectArray(Class<T[]> tClass, String object) {
        return GSON.fromJson(object, tClass);
    }

    public static <T> String mapObjectToString(T object) {
        return GSON.toJson(object);
    }

}
