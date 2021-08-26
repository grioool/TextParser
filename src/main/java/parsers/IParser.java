package parsers;

public interface IParser<T> {
    T parse(String plain);
}
