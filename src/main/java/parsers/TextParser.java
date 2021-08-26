package parsers;

import text.elements.Paragraph;
import text.elements.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser implements IParser<Text> {

    private final IParser<Paragraph> paragraphParser;

    public TextParser() {
        paragraphParser = new ParagraphParser();
    }

    @Override
    public Text parse(String plain) {
        plain = plain.trim();
        Pattern pattern = Pattern.compile("(\n+.*\\{)|(\n+([\t ]+))|$");
        Matcher matcher = pattern.matcher(plain);

        int nextStart = 0;
        Text text = new Text();
        while (matcher.find()) {
            if(nextStart > matcher.start()) continue;
            if(matcher.group().endsWith("{")) {
                String paragraph = plain.substring(nextStart, matcher.start()).trim();
                if (paragraph.length() > 0)
                    text.getParagraphs().add(paragraphParser.parse(paragraph));

                int codeBlockEnd = getPairCloseFigureBreakIndex(plain, matcher.end() - 1);
                String codeBlock = plain.substring(matcher.start(), codeBlockEnd + 1).trim();
                text.getParagraphs().add(paragraphParser.parse(codeBlock));
                nextStart = codeBlockEnd + 1;
            } else {
                String paragraph = plain.substring(nextStart, matcher.start()).trim();
                if (paragraph.length() > 0)
                    text.getParagraphs().add(paragraphParser.parse(paragraph));

                nextStart = matcher.end();
            }
        }
        return text;
    }

    private int getPairCloseFigureBreakIndex(String plain, int figureBreakIndex) {
        int blockNesting = 0;
        int currentIndex = figureBreakIndex;
        do {
            char c = plain.charAt(currentIndex);
            if (c == '{') blockNesting++;
            if (c == '}') blockNesting--;
            currentIndex++;
        } while (blockNesting > 0 && currentIndex < plain.length());

        if (currentIndex == plain.length()) return -1;
        return currentIndex - 1;
    }
}
