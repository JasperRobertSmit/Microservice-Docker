package App;

import spark.ResponseTransformer;
import com.google.gson.Gson;
/**
 * Created by Jasper on 21-6-2016.
 */
public class JsonTransformer implements ResponseTransformer{

    private Gson gson = new Gson();

    @Override
    public String render(Object o) throws Exception {
        return gson.toJson(o);
    }
}
