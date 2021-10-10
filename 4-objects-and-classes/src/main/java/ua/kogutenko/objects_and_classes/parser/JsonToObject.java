package ua.kogutenko.objects_and_classes.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.kogutenko.objects_and_classes.model.Root;

import java.util.ArrayList;
import java.util.Arrays;

public class JsonToObject {

    public static ArrayList<Root> converter(String responseBody) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Root[] root = mapper.readValue(responseBody, Root[].class);
            return new ArrayList<>(Arrays.asList(root));
        } catch (Exception e) {
            System.out.println("Json to obj : " + e.toString());
        }
        return null;
    }

}
