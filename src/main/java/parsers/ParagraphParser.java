package parsers;

import text.elements.Paragraph;
import text.elements.Sentence;
import text.elements.Word;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements IParser<Paragraph> {

    private final IParser<Sentence> sentenceParser;

    public ParagraphParser() {
        this.sentenceParser = new SentenceParser();
    }

    @Override
    public Paragraph parse(String plain) {
        plain = plain.trim();
        final String sentenceFinalizers = ".!?:";
        Pattern pattern = Pattern.compile("[" + sentenceFinalizers + "]\\s");
        Matcher matcher = pattern.matcher(plain);

        int nextStart = 0;
        Paragraph paragraph = new Paragraph();
        paragraph.setType(defineParagraphType(plain));
        switch (paragraph.getType()) {
            case TEXT:
                while (matcher.find()) {
                    String sentence = plain.substring(nextStart, matcher.start() + 1).trim();
                    if (!sentence.substring(sentence.length() - 1).matches("[" + sentenceFinalizers + "]")) sentence = sentence + ".";

                    paragraph.getSentences().add(sentenceParser.parse(sentence));
                    nextStart = matcher.end();
                }
                paragraph.getSentences().add(sentenceParser.parse(plain.substring(nextStart)));
                break;
            case TITLE:
                paragraph.getSentences().add(sentenceParser.parse(plain));
                break;
            case CODE:
                Sentence sentence = new Sentence();
                sentence.getSentenceItems().add(new Word(plain));
                paragraph.getSentences().add(sentence);
                break;
            default:
                throw new NullPointerException();
        }
        return paragraph;
    }

    private Paragraph.Type defineParagraphType(String paragraph) {
        if (paragraph.matches("\\s*([0-9]+\\.\\s*)+.+"))
            return Paragraph.Type.TITLE;
        if (paragraph.contains("{"))
            return Paragraph.Type.CODE;
        return Paragraph.Type.TEXT;
    }
}
