import java.util.Objects;

public class Word {
    private String tag_ = "", content_ = "", number_ = "";    //tag = "Noun or Verb or Abjective"; content = "the actual word"; number = "plural or singular" 
    private int headTokenIndex_ = 0;    //PartOfSpeach useful for syntax tree

    public Word(String tag, String content, String number, int headTokenIndex) {
        tag_ = tag;
        content_ = content;
        number_ = number;
        headTokenIndex_ = headTokenIndex;
    }

    public Word(String tag, String content, String number) {
        tag_ = tag;
        content_ = content;
        number_ = number;
        headTokenIndex_ = 0;
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

    public int getHeadTokenIndex(){
        return headTokenIndex_;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o; //Casting o to Word
        return headTokenIndex_ == word.headTokenIndex_ &&
               Objects.equals(tag_, word.tag_) &&
               Objects.equals(content_, word.content_) &&
               Objects.equals(number_, word.number_);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag_, content_, number_, headTokenIndex_);
    }

    // For debugging purposes 
    @Override
    public String toString() {
        return String.format("Word{tag='%s', content='%s', number='%s', headTokenIndex=%d}", 
                              tag_, content_, number_, headTokenIndex_);
    }
}