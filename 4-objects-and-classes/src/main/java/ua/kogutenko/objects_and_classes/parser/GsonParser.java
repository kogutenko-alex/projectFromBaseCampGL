package ua.kogutenko.objects_and_classes.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ua.kogutenko.objects_and_classes.annotation.AnnotationExclusionStrategy;
import ua.kogutenko.objects_and_classes.model.Root;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GsonParser {
    public ArrayList<Root> parse (String responseBody) {
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new AnnotationExclusionStrategy())
                .setLenient()
                .create();
        try {

            Type collectionType = new TypeToken<ArrayList<Root>>(){}.getType();
            return gson.fromJson(responseBody, collectionType);

        } catch (Exception err) {
            System.out.println("Parser: " + err.toString());
        }
        return null;
    }
}
