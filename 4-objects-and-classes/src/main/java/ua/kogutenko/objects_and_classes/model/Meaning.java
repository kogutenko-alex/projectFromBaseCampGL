package ua.kogutenko.objects_and_classes.model;

//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.google.gson.annotations.Expose;
//
//import java.util.List;
//
//public class Meaning {
//    @JsonProperty("partOfSpeech")
//    @Expose private String partOfSpeech;
//    @JsonProperty("definitions")
//    @Expose private List<Definition> definitions;
//
//    public Meaning(String partOfSpeech, List<Definition> definitions) {
//        this.partOfSpeech = partOfSpeech;
//        this.definitions = definitions;
//    }
//
//    public String getPartOfSpeech() {
//        return partOfSpeech;
//    }
//
//    public void setPartOfSpeech(String partOfSpeech) {
//        this.partOfSpeech = partOfSpeech;
//    }
//
//    public List<Definition> getDefinitions() {
//        return definitions;
//    }
//
//    public void setDefinitions(List<Definition> definitions) {
//        this.definitions = definitions;
//    }
//
//}

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Generated("jsonschema2pojo")
public class Meaning implements Serializable
{

    @SerializedName("partOfSpeech")
    @Expose
    private String partOfSpeech;
    @SerializedName("definitions")
    @Expose
    private List<Definition> definitions = new ArrayList<Definition>();
    private final static long serialVersionUID = -6001674712655199092L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Meaning() {
    }

    /**
     *
     * @param partOfSpeech
     * @param definitions
     */
    public Meaning(String partOfSpeech, List<Definition> definitions) {
        super();
        this.partOfSpeech = partOfSpeech;
        this.definitions = definitions;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }

}
