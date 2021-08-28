package tasks;

import text.elements.*;

import java.util.*;
import java.util.stream.Collectors;

public class Tasks {

    private static List<Sentence> getAllSentences(Text text) {
        List<Sentence> allSentences = new ArrayList<>();
        for (Paragraph paragraph : text.getParagraphs()) {
            allSentences.addAll(paragraph.getSentences());
        }
        return allSentences;
    }

    public static Set<SentenceItem> getUniqueWords(Text text) {
        List<Sentence> allSentences = getAllSentences(text);
        Set<SentenceItem> firstSentenceWords = getSetOfWords(allSentences.get(0));
        if (firstSentenceWords == null) return null;

        allSentences.stream().skip(1).forEach(sentence -> {
            for (SentenceItem item : sentence.getSentenceItems())
                if (item instanceof Word) {
                    if (firstSentenceWords.stream().anyMatch(word -> item.getValue().equalsIgnoreCase(word.getValue())))
                        firstSentenceWords.remove(item);
                }

        });
        return firstSentenceWords;
    }

    private static Set<SentenceItem> getSetOfWords(Sentence sentence) {
        return sentence.getSentenceItems().stream()
                .filter(item -> item instanceof Word).collect(Collectors.toSet());
    }

    public static Set<Word> getWordsInQuestSentenceByLength(Text text, int length) {
        Set<Word> questionWords = new HashSet<>();
        getAllSentences(text).stream()
                        .filter(sentence ->
                                sentence.getSentenceItems()
                                        .get(sentence.getSentenceItems().size() - 1)
                                        .getValue().equals("?")
                        )
                        .forEach(sentence -> sentence.getSentenceItems().forEach(sentenceItem -> {
                            if(sentenceItem instanceof Word && sentenceItem.getValue().length() == length)
                                questionWords.add(new Word(sentenceItem.getValue().toLowerCase()));
                        }));
        return questionWords;
    }

    private static final String CONSONANTS = "BCDFGHJKLMNPQRSTVXZWYЙЦКНГШЩЗХЪФВПРЛДЖЧСМТЬБ";

    public static List<Word> getWordsSortedByFirstConsonant(Text text){
        List<Word> wordsBegunWithVowelLetter = new ArrayList<>();
        getAllSentences(text).forEach(sentence -> sentence.getSentenceItems().forEach(sentenceItem -> {
            if(sentenceItem instanceof Word && !CONSONANTS.contains(sentenceItem.getValue().charAt(0) + ""))
                wordsBegunWithVowelLetter.add((Word) sentenceItem);
        }));
        wordsBegunWithVowelLetter.sort((a, b) -> {
            return getFirstConsonant(a).compareTo(getFirstConsonant(b));
        });
        return wordsBegunWithVowelLetter;
    }

    private static Character getFirstConsonant(Word word) {
        int i = 0;
        while (i < word.getValue().length())
            if (CONSONANTS.contains(word.getValue().charAt(i) + ""))
                return word.getValue().charAt(i);
        i++;
        return Character.MIN_VALUE;
    }
}
