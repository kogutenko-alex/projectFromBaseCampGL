package ua.kogutenko.objects_and_classes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ua.kogutenko.objects_and_classes.annotation.Exclude;

public class Phonetic {
    @JsonProperty("text")
    private String text;
    @JsonIgnore
    @Exclude
    private String audio;

    public Phonetic(String text, String audio) {
        this.text = text;
        this.audio = audio;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    @Override
    public String toString() {
        return text;
    }
}
