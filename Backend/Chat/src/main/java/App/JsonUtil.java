package App;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by Jasper on 15-6-2016.
 */
public class JsonUtil {
    public static Map<String, String> fromJson(String object){
        return new Gson().fromJson(object, Map.class);
    }
}
