package ua.kogutenko.objects_and_classes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("jsonschema2pojo")
public class Phonetic implements Serializable {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("audio")
    @Expose
    private String audio;
    private final static long serialVersionUID = -590158724861374787L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Phonetic() {
    }

    /**
     *
     * @param text
     * @param audio
     */
    public Phonetic(String text, String audio) {
        super();
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

}
