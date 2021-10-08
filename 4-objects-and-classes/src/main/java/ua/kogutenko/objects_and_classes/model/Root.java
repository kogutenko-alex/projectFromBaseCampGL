package ua.kogutenko.objects_and_classes.model;

import com.google.gson.annotations.Expose;

import java.util.List;


public class Root {
   @Expose private String word;
   @Expose private String phonetic;
   @Expose(serialize = false) private List<Phonetic> phonetics;
   @Expose(serialize = false) private String origin;
   @Expose private List<Meaning> meanings;

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

   public String getMeanings() {
      StringBuilder output = new StringBuilder();
      output.append("\"");
      for (Meaning meaning : meanings) {
            output.append(meaning.getPartOfSpeech() + " - ");
            for (Definition def : meaning.getDefinitions()) {
               output.append(def.getDefinition()).append(".\n");
            }
      }
      output.append("\"");
      return output.toString();
   }

   public void setMeanings(List<Meaning> meanings) {
      this.meanings = meanings;
   }

   public String getExamples() {
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

   @Override
   public String toString() {
      return word;
   }
}
