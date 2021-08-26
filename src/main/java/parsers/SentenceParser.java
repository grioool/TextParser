package parsers;

import text.elements.Punctuation;
import text.elements.Sentence;
import text.elements.Word;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements IParser<Sentence> {

    public Sentence parse(String plain) {
        plain = plain.trim();
        Pattern pattern = Pattern.compile("(\\s+)|(\\s*(([.]{2,3})|([.?!,:;\"'`()\\[\\]{}]))\\s*)");
        Matcher matcher = pattern.matcher(plain);

        int nextStart = 0;
        Sentence sentence = new Sentence();
        while (matcher.find()) {

            String word = plain.substring(nextStart, matcher.start()).trim();
            if(word.length() > 0)
            sentence.getSentenceItems().add(new Word(word));

            String punctuationCandidate = matcher.group().trim();
            if (punctuationCandidate.length() > 0)
                sentence.getSentenceItems().add(new Punctuation(punctuationCandidate));

            nextStart = matcher.end();
        }
        return sentence;
    }
}
