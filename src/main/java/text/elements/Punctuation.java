package text.elements;

public class Punctuation extends SentenceItem {

    public Punctuation(String value){
        this.value = value;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Sign{" + value+ "}";
    }
}
