package text.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Paragraph {

    public enum Type {
        CODE, TEXT, TITLE
    }

    private Type type;

    private final List<Sentence> sentences;

    public Paragraph(){
        this.sentences = new ArrayList<>();
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paragraph paragraph = (Paragraph) o;
        return type == paragraph.type && Objects.equals(sentences, paragraph.sentences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, sentences);
    }

    @Override
    public String toString() {
        String paragraphBegin  = type == Type.CODE ? "" : "\t";
        StringBuilder sb = new StringBuilder(paragraphBegin);
        for(Sentence sentence : sentences) {
            sb.append(sentence);
        }
        return sb.toString();
    }
}
