package petplan.support;

import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * @author
 */
public class JsonParser {
    private static final String workDir = System.getProperty("user.dir");
    private static final String fullFilePath = workDir + File.separator + "resources" +
            File.separator + "common" + File.separator + "addresses" + File.separator;

    private JSONParser parser = new JSONParser();

    @NotNull
    public void parseJson(String cityId, String generationID, String fileFilename) {
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(
                    fullFilePath + fileFilename));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonObject = (JSONArray) obj;
        JSONObject advertObject = (JSONObject) Objects.requireNonNull(jsonObject).get(0);
        System.out.println(advertObject.toString());
        /**
         *
         * TODO
         * get and set the json obj values
         * @return jsonObject.toJSONString();
         */
    }

    public static JSONObject getJSONFromFile(String fileName) {
        return getJSONFromFile(fullFilePath, fileName);
    }

    public static JSONObject getJSONFromFile(String workDir, String fileName) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(
                    workDir + fileName));
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getJsonFromString(String strJson) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(strJson);
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {


    }
}
