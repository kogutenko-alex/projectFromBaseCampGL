package ua.kogutenko.objects_and_classes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Generated("jsonschema2pojo")
public class Root implements Serializable {

   @SerializedName("word")
   @Expose
   private String word;
   @SerializedName("phonetic")
   @Expose
   private String phonetic;
   @SerializedName("origin")
   @Expose
   private String origin;
   @SerializedName("phonetics")
   @Expose
   private List<Phonetic> phonetics = new ArrayList<Phonetic>();
   @SerializedName("meanings")
   @Expose
   private List<Meaning> meanings = new ArrayList<Meaning>();
   private final static long serialVersionUID = 888946181741640294L;

   /**
    * No args constructor for use in serialization
    *
    */
   public Root() {
   }

   /**
    *
    * @param phonetics
    * @param word
    * @param meanings
    */
   public Root(String word, List<Phonetic> phonetics, List<Meaning> meanings) {
      super();
      this.word = word;
      this.phonetics = phonetics;
      this.meanings = meanings;
   }


   public String getPhonetic() {
      return phonetic;
   }

   public void setPhonetic(String phonetic) {
      this.phonetic = phonetic;
   }

   public String getOrigin() {
      return origin;
   }

   public void setOrigin(String origin) {
      this.origin = origin;
   }


   public String getWord() {
      return word;
   }

   public void setWord(String word) {
      this.word = word;
   }

   public List<Phonetic> getPhonetics() {
      return phonetics;
   }

   public void setPhonetics(List<Phonetic> phonetics) {
      this.phonetics = phonetics;
   }

   public List<Meaning> getMeanings() {
      return meanings;
   }

   public void setMeaning(List<Meaning> meanings) {
      this.meanings = meanings;
   }

   public String gPhonetic() {
      return phonetic;
   }

   public String gWord() {
      return word;
   }

   public String gMeanings() {
      StringBuilder output = new StringBuilder();
      output.append("\"");
      for (Meaning meaning : meanings) {
            output.append(meaning.getPartOfSpeech() + " - ");
            for (Definition def : meaning.getDefinitions()) {
               output.append(def.getDefinition()).append(" ");
            }
      }
      output.append("\"");
      return output.toString();
   }

   public String gExamples() {
      StringBuilder builder = new StringBuilder();
      List<Meaning> meanings = this.meanings;
      builder.append("\"");
      for (Meaning meaning : meanings) {
         List<Definition> definitions = meaning.getDefinitions();
         for (Definition definition : definitions) {
            builder.append(definition.getExample() + ';');
         }
      }
      builder.append("\"");
      return builder.toString();
   }

}
