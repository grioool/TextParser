package text.elements;

import java.util.Objects;

public abstract class SentenceItem {

    protected String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SentenceItem that = (SentenceItem) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "text.elements.SentenceItem{" +
                "value='" + value + '\'' +
                '}';
    }
}
