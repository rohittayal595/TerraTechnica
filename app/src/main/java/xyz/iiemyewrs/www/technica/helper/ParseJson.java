package xyz.iiemyewrs.www.technica.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by iiemyewrs on 16/1/16.
 */
public class ParseJson {
    public static String[] ids;
    public static String[] data;
    public static String[] date;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_ID = "_ID";
    public static final String KEY_DATA = "text";
    public static final String KEY_DATE = "date";

    private JSONArray result = null;

    private String json;

    public ParseJson(String json){
        this.json = json;
    }

    public void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            result = jsonObject.getJSONArray(JSON_ARRAY);

            ids = new String[result.length()];
            data = new String[result.length()];
            date = new String[result.length()];

            for(int i=0;i<result.length();i++){
                JSONObject jo = result.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                data[i] = jo.getString(KEY_DATA);
                date[i] = jo.getString(KEY_DATE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
