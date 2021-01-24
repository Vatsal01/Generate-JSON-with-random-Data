import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateJson {

    public static void main (String[] args) throws Exception {
        int a;
        double r;
        double min = 0.0;
        double max = 255.0;

        String col[] = {"red", "green", "blue", "pink"};
        Random ran = new Random();
        JSONArray num = new JSONArray();
        JSONArray colr = new JSONArray();

        JSONObject json = new JSONObject();

        //Loop to generate random number between in range 0 - 255
        //And choose color randomly from the col array  based on random index
        // Adding color name and value to JSON Array
        for (int i = 0; i < 15; i++) {
            r = min + (max - min) * ran.nextDouble();
            num.add(r);
            a = ran.nextInt(4);
            colr.add(col[a]);
        }

        json.put("values", num); // Adding JSON Array to JSON Object
        json.put("color", colr);

        //System.out.println(json);
        FileWriter file = new FileWriter("random.json"); //Creating JSON FILE
        file.write(json.toJSONString()); //Writing JSON Object onto JSON File
        file.flush();


        System.out.println(calcAvg("red"));
        //Calling function calcAv which calculates and returns the average value
        //associated with particular colour passed as argument

    }

// Function Definition
        public static double calcAvg (String c) throws IOException, ParseException {

            double avg = 0;
            double sum = 0;
            double val = 0;
            int count = 0; //For counting total occurence of the color

            FileReader fr = new FileReader("random.json"); //Reader
            JSONParser jsonParser = new JSONParser(); //Creating Object of JSONParser
            Object obj = jsonParser.parse(fr);
            JSONObject json = (JSONObject) obj;

            JSONArray colorArr = (JSONArray) json.get("color"); //Creating JSON Array for color and values
            JSONArray valueArr = (JSONArray) json.get("values");


//Iterating over the color array in JSON to first compare the color with the color passed ad argument

            for (int i = 0; i < colorArr.size(); i++) {

                    String s = (String) colorArr.get(i);
                    if (s.equals(c)) {
                        val = (Double) valueArr.get(i);
                        sum += val;
                        count++;
                        System.out.println("Color :" + s);
                        System.out.println("Value :" + val);
                    }
                }

            avg = sum / count;

            return avg;
        }

}
