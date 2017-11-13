package ohtu;

import com.google.gson.Gson;
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

        // Week maximums
        url = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        jsonContent = Request.Get(url).execute().returnContent().asString();
        mapper = new Gson();
        CourseInfo info = mapper.fromJson(jsonContent, CourseInfo.class);

        // Print
        System.out.println("Kurssi: Ohjelmistotuotanto 2017\n");
        System.out.println("Opiskelijanumero: " + studentNr);

        int[] maximums = info.getExercises();
        for (Submission submission : subs) {
            int max = maximums[submission.getWeek()-1];
            System.out.println(submission.toString(max));
        }

    }
}