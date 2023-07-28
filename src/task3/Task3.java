package task3;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task3 {
    public static void main(String[] args) throws IOException, ParseException {
        FileReader testsFile = new FileReader(args[0]);
        FileReader valuesFile = new FileReader(args[1]);

        JSONParser parser = new JSONParser();
        JSONObject reportsJSON = new JSONObject();
        JSONObject testsJSON = (JSONObject) parser.parse(testsFile);
        JSONObject valuesJSON = (JSONObject) parser.parse(valuesFile);

        JSONArray valueJsonArray = (JSONArray) valuesJSON.get("values");
        JSONArray testJsonArray = (JSONArray) testsJSON.get("tests");
        JSONArray reportJsonArray = new JSONArray();

        for (Object o : testJsonArray) {
            JSONObject oneTest = (JSONObject) o;
            reportJsonArray.add(formJSONObj(oneTest,valueJsonArray));
            System.out.println(reportJsonArray);

        }

        reportsJSON.put("reports", reportJsonArray);
        System.out.println(reportsJSON.toJSONString());
        Files.write(Paths.get("reports.json"), reportsJSON.toString().getBytes());
    }
    static public String valueById(long id, JSONArray array){
        for (Object o:
             array) {
            JSONObject one = (JSONObject) o;
            if ((long)one.get("id") == (id)) {
                return one.get("value").toString();
            }
        }
        return "";
    }
    static public JSONObject formJSONObj(JSONObject oneTest, JSONArray valueJsonArray){

        JSONObject oneTestWithValues = new JSONObject();
        oneTestWithValues.put("id", oneTest.get("id"));
        oneTestWithValues.put("title",oneTest.get("title").toString());
        oneTestWithValues.put("value", valueById((long)oneTest.get("id"),valueJsonArray));
        if (oneTest.containsKey("values")){
            JSONArray testJsonArray = (JSONArray) oneTest.get("values");
            JSONArray valuesTestJsonArray = new JSONArray();
            for (Object o : testJsonArray) {
                JSONObject oneTestInValues = (JSONObject) o;
                valuesTestJsonArray.add(formJSONObj(oneTestInValues,valueJsonArray));
                System.out.println(valuesTestJsonArray);
            }
            oneTestWithValues.put("values", valuesTestJsonArray);
        }
        return oneTestWithValues;
    }
}
