package ua.kogutenko.objects_and_classes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.kogutenko.objects_and_classes.annotation.Exclude;

import java.util.List;


public class Root {
   @JsonProperty("word")
   private String word;
   @JsonProperty("phonetic")
   private String phonetic;
   @Exclude
   @JsonProperty("phonetics")
   private List<Phonetic> phonetics;
   @Exclude
   @JsonProperty("origin")
   private String origin;
   @JsonProperty("meanings")
   private List<Meaning> meanings;

   public Root(String word, String phonetic, List<Phonetic> phonetics, String origin, List<Meaning> meanings) {
      this.word = word;
      this.phonetic = phonetic;
      this.phonetics = phonetics;
      this.origin = origin;
      this.meanings = meanings;
   }

   public String getWord() {
      return word;
   }

   public void setWord(String word) {
      this.word = word;
   }

   public String getPhonetic() {
      return phonetic;
   }

   public void setPhonetic(String phonetic) {
      this.phonetic = phonetic;
   }

   public List<Phonetic> getPhonetics() {
      return phonetics;
   }

   public void setPhonetics(List<Phonetic> phonetics) {
      this.phonetics = phonetics;
   }

   public String getOrigin() {
      return origin;
   }

   public void setOrigin(String origin) {
      this.origin = origin;
   }

   public List<Meaning> getMeanings() {
      return meanings;
   }

   public void setMeanings(List<Meaning> meanings) {
      this.meanings = meanings;
   }
}
