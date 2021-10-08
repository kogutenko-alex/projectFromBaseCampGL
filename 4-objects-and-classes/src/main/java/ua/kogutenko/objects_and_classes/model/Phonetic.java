package ua.kogutenko.objects_and_classes.model;

import com.google.gson.annotations.Expose;

public class Phonetic {
    @Expose
    private String text;
    @Expose(serialize = false)
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
