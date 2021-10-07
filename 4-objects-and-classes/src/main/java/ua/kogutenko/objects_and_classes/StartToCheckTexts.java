package ua.kogutenko.objects_and_classes;

import org.json.JSONArray;
import ua.kogutenko.objects_and_classes.model.Root;
import ua.kogutenko.objects_and_classes.parser.GsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class StartToCheckTexts {

    public static HttpURLConnection connection;

    public static void main(String[] args) throws IOException {
        String rootFolder = getApplicationRootFile().getCanonicalPath();
        File dictionary = new File(rootFolder + "\\dictionary.csv");
        if(dictionary.createNewFile()) System.out.println("File is created!!!");
        else System.out.println("File already exists!!!");
        Path knownWords = Paths.get(rootFolder + "\\words\\known.txt");

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        List<Collection<Root>> dictionaryList = new LinkedList<>();
        try {
            List<String> words = Files.readAllLines(knownWords);
            words.remove(0);
//
          for (String word : words) {
              URL url = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/" + word);
              connection = (HttpURLConnection) url.openConnection();

              connection.setRequestMethod("GET");
              connection.setConnectTimeout(5000);
              connection.setReadTimeout(5000);

              int status = connection.getResponseCode();

              if (status > 299) {
                  reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
              } else {
                  reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                  while ((line = reader.readLine()) != null) {
                      responseContent.append(line);
                  }
                  if(dictionaryList.add(new GsonParser().parse(responseContent.toString()))) {
                      JSONArray ja = new JSONArray(responseContent.toString());
                      String str = ja.getJSONObject(0).getString("word");
//                      System.out.println(str + " added");
                  }
              }
              reader.close();
              if (responseContent.length() != 0) {
                  responseContent.delete(0, responseContent.length());
              }
          }
        } catch (Exception err) {
            err.printStackTrace();
        }
        System.out.println("End");
    }

    private static File getApplicationRootFile() {
        URL url = StartToCheckTexts.class.getResource(".");
        String path = url.getPath();
        return new File(path + "../../../../../../../");
    }
}
