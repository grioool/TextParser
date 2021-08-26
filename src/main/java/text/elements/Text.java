package text.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Text {

    private final List<Paragraph> paragraphs;

    public Text() {
        this.paragraphs = new ArrayList<>();
    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return Objects.equals(paragraphs, text.paragraphs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paragraphs);
    }

    @Override
    public String toString() {
        return "text.elements.Text{" +
                "sentences=" + paragraphs +
                '}';
    }
}


