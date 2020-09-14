package cn.logcode.xhufiveface.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GsonUtil {

    public static final Log logger = LogFactory.getLog(GsonUtil.class);

    public static JsonParser jsonParser;

    public static JsonParser jsonParser() {
        if (jsonParser == null) {
            jsonParser = new JsonParser();
        }
        return jsonParser;
    }

    public static Gson gson() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .create();
    }

    public static Optional<String> parseString(String body, String field) {
        JsonObject object = jsonParser().parse(body).isJsonObject() ? null : jsonParser().parse(body).getAsJsonObject();
        if (object == null) {
            return Optional.empty();
        }
        if (object.has(field)) {
            return Optional.of(object.get(field).getAsString());
        }
        return Optional.empty();
    }


    public static List<String> parseStringList(String body, String field) {
        JsonObject object = jsonParser().parse(body).isJsonObject() ? null : jsonParser().parse(body).getAsJsonObject();
        List<String> list = new ArrayList<>();
        if (object != null) {
            if (object.get(field).isJsonArray()) {
                object.get(field).getAsJsonArray().forEach(jsonElement -> {
                    list.add(jsonElement.getAsString());
                });
            }
        }
        return list;
    }

    public static Optional<Integer> parseInteger(String body, String field) {
        JsonObject object = jsonParser().parse(body).isJsonObject() ? null : jsonParser().parse(body).getAsJsonObject();
        if (object == null) {
            return Optional.empty();
        }
        if (object.has(field)) {
            return Optional.of(object.get(field).getAsInt());
        }
        return Optional.empty();
    }

    public static List<Integer> parseIntegerList(String body, String field) {
        JsonObject object = jsonParser().parse(body).isJsonObject() ? null : jsonParser().parse(body).getAsJsonObject();
        List<Integer> list = new ArrayList<>();
        if (object != null) {
            if (object.get(field).isJsonArray()) {
                object.get(field).getAsJsonArray().forEach(jsonElement -> {
                    list.add(jsonElement.getAsInt());
                });
            }
        }
        return list;
    }


    public static Optional<Boolean> parseBoolean(String body, String field) {
        JsonObject object = jsonParser().parse(body).isJsonObject() ? null : jsonParser().parse(body).getAsJsonObject();
        if (object == null) {
            return Optional.empty();
        }
        if (object.has(field)) {
            return Optional.of(object.get(field).getAsBoolean());
        }
        return Optional.empty();
    }

    public static Optional<Short> parseShort(String body, String field) {
        JsonObject object = jsonParser().parse(body).isJsonObject() ? null : jsonParser().parse(body).getAsJsonObject();
        if (object == null) {
            return Optional.empty();
        }
        if (object.has(field)) {
            return Optional.of(object.get(field).getAsShort());
        }
        return Optional.empty();
    }

    public static Optional<Byte> parseByte(String body, String field) {
        JsonObject object = jsonParser().parse(body).isJsonObject() ? null : jsonParser().parse(body).getAsJsonObject();
        if (object == null) {
            return Optional.empty();
        }
        if (object.has(field)) {
            return Optional.of(object.get(field).getAsByte());
        }
        return Optional.empty();
    }

    public static <T> Optional<T> parseObject(String body, String field, Class<T> clazz) {
        JsonObject object = jsonParser().parse(body).isJsonObject() ? null : jsonParser().parse(body).getAsJsonObject();
        if (object != null) {
            return Optional.of(gson().fromJson(object.get(field).getAsString(), clazz));
        }
        return Optional.empty();
    }


    public static String toJson(Object data) {
        return gson().toJson(data);
    }
}
