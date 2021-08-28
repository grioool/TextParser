package text.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sentence {

    List<SentenceItem> sentenceItems;

    public Sentence(){
        this.sentenceItems = new ArrayList<>();
    }

    public List<SentenceItem> getSentenceItems(){
        return sentenceItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(sentenceItems, sentence.sentenceItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sentenceItems);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(SentenceItem item : sentenceItems) {
            if(item instanceof Word)
                sb.append(" ");
            sb.append(item);
        }
        return sb.toString();
    }
}
