package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "011120775"; // Not mine
        if ( args.length>0) {
            studentNr = args[0];
        }

        // Submissions
        String url = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";
        String jsonContent = Request.Get(url).execute().returnContent().asString();
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(jsonContent, Submission[].class);
        //System.out.println(jsonContent);

        // Week maximums
        url = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        jsonContent = Request.Get(url).execute().returnContent().asString();
        mapper = new Gson();
        CourseInfo info = mapper.fromJson(jsonContent, CourseInfo.class);
        System.out.println(jsonContent);

        // All stats
        int totalCourseH = 0;
        int totalCourseE = 0;
        url = "https://studies.cs.helsinki.fi/ohtustats/stats";
        jsonContent = Request.Get(url).execute().returnContent().asString();
        JsonObject stats = (new JsonParser()).parse(jsonContent).getAsJsonObject();
        for(String key: stats.keySet()) {
            JsonObject week = stats.getAsJsonObject(key);
            totalCourseE += Integer.parseInt(week.get("exercise_total").toString());
            totalCourseH += Integer.parseInt(week.get("hour_total").toString());
            //totalCourseH += week.get("hour_total");
            //System.out.println(week);
        }

        // Print
        System.out.println("Kurssi: Ohjelmistotuotanto 2017");
        System.out.println("");
        System.out.println("Opiskelijanumero: " + studentNr);
        System.out.println("");

        int totalE = 0;
        int totalH = 0;
        int[] maximums = info.getExercises();
        for (Submission submission : subs) {
            totalE += submission.getExercises().length;
            totalH += submission.getHours();
            int max = maximums[submission.getWeek()-1];
            System.out.println(submission.toString(max));
        }

        System.out.println("");
        System.out.println("Yhteensä: "+totalE+" tehtävää "+totalH+" tuntia");

        System.out.println("");
        System.out.println("Kurssilla yhteensä " + totalCourseE + " palautusta, palautettuja tehtäviä " + totalCourseH + " kpl");
    }
}