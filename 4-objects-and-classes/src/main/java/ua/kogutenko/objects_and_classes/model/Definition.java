package ua.kogutenko.objects_and_classes.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Definition {
    private String definition;
    private String example;
    private List<String> synonyms;
    private List<Object> antonyms;

    public Definition(String definition, String example, List<String> synonyms, List<Object> antonyms) {
        this.definition = definition;
        this.example = example;
        this.synonyms = synonyms;
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

    public List<Object> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(List<Object> antonyms) {
        this.antonyms = antonyms;
    }
}
