package ua.kogutenko.objects_and_classes.model;

//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.google.gson.annotations.Expose;
//
//import java.util.List;
//
//public class Definition {
//    @JsonProperty("definition")
//    @Expose private String definition;
//    @JsonProperty("example")
//    @Expose private String example;
//    @JsonProperty("synonyms")
//    @Expose(serialize = false) private List<String> synonyms;
//    @JsonProperty("antonyms")
//    @Expose(serialize = false) private List<Object> antonyms;
//
//    public Definition(String definition, String example, List<String> synonyms, List<Object> antonyms) {
//        this.definition = definition;
//        this.example = example;
//        this.synonyms = synonyms;
//        this.antonyms = antonyms;
//    }
//
//    public String getDefinition() {
//        return definition;
//    }
//
//    public void setDefinition(String definition) {
//        this.definition = definition;
//    }
//
//    public String getExample() {
//        return example;
//    }
//
//    public void setExample(String example) {
//        this.example = example;
//    }
//
//    public List<String> getSynonyms() {
//        return synonyms;
//    }
//
//    public void setSynonyms(List<String> synonyms) {
//        this.synonyms = synonyms;
//    }
//
//    public List<Object> getAntonyms() {
//        return antonyms;
//    }
//
//    public void setAntonyms(List<Object> antonyms) {
//        this.antonyms = antonyms;
//    }
//}

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Generated("jsonschema2pojo")
public class Definition implements Serializable
{

    @SerializedName("definition")
    @Expose
    private String definition;
    @SerializedName("example")
    @Expose
    private String example;
    @SerializedName("synonyms")
    @Expose
    private List<String> synonyms = new ArrayList<String>();
    @SerializedName("antonyms")
    @Expose
    private List<Object> antonyms;
    private final static long serialVersionUID = -8913700284869848762L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Definition() {
    }

    /**
     *
     * @param synonyms
     * @param definition
     * @param example
     */
    public Definition(String definition, String example, List<String> synonyms) {
        super();
        this.definition = definition;
        this.example = example;
        this.synonyms = synonyms;
    }


    public List<Object> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(List<Object> antonyms) {
        this.antonyms = antonyms;
    }


    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

}