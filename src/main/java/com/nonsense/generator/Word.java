package com.nonsense.generator;
import java.util.Objects;

public class Word {
    private String tag_ = "", content_ = "", number_ = "";    //tag = "Noun or Verb or Abjective"; content = "the actual word"; number = "plural or singular" 

    public Word(String tag, String content, String number) {
        tag_ = tag;
        content_ = content;
        number_ = number;
    }

    // Getter
    public String getTag(){
        return tag_;
    }
    
    public String getContent(){
        return content_;
    }

    public String getNumber(){
        return number_;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o; // Casting o to Word
        return Objects.equals(tag_, word.tag_) &&
               Objects.equals(content_, word.content_) &&
               Objects.equals(number_, word.number_);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag_, content_, number_);
    }

    // For debugging purposes 
    @Override
    public String toString() {
        return String.format("Word{tag='%s', content='%s', number='%s'", 
                              tag_, content_, number_);
    }
}