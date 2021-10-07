package ua.kogutenko.objects_and_classes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Meaning {
    @JsonProperty("partOfSpeech")
    private String partOfSpeech;
    @JsonProperty("definitions")
    private List<Definition> definitions;

    public Meaning(String partOfSpeech, List<Definition> definitions) {
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

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(partOfSpeech + " - ");
        for (Definition def : definitions) {
            output.append(def.getDefinition()).append(";");
        }
        output.append("\t");
        for (Definition def : definitions) {
            output.append(def.getExample()).append(";");
        }
        return output.toString();
    }
}
