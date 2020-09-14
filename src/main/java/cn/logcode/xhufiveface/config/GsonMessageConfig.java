package cn.logcode.xhufiveface.config;

import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.lang.reflect.Type;


/**
 * Gson 转换时的一些问题，手动配置一下转换的对象.
 *
 * @author rxliuli
 */
@Configuration
public class GsonMessageConfig implements JsonSerializer<Gson> {

    /**
     * 项目中转换 json 默认使用的 Gson 对象.
     */
    public static final Gson DEFAULT_GSON = new GsonBuilder()
            .serializeNulls()
            .disableHtmlEscaping()
            .registerTypeAdapter(Gson.class, new GsonMessageConfig())
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();

    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter() {
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setGson(DEFAULT_GSON);
        return gsonHttpMessageConverter;
    }

    @Override
    public JsonElement serialize(Gson json, Type type, JsonSerializationContext jsonSerializationContext) {
        final JsonParser parser = new JsonParser();
        return parser.parse(json.toString());
    }
}
