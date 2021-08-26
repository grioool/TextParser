import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parsers.TextParser;
import text.elements.Text;

import java.io.*;
import java.util.Scanner;

public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static byte[] readFromFile() throws IOException {
        InputStream inputStream = new FileInputStream("src/main/resources/inputText.txt");
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer, 0, inputStream.available());
        inputStream.close();
        return buffer;
    }

//    public static void parseTextFromFile() {
//        try {
//            String textString = new String(readFromFile(), StandardCharsets.UTF_8);
//            TextParser textParser = new TextParser();
//            Text textParsed = textParser.parse(textString);
//            System.out.println(Arrays.toString(textParsed));
//            SentenceParser sentenceParser = new SentenceParser();
//            String[] words = sentenceParser.parse(textString);
//            System.out.println(Arrays.toString(words));
//        } catch (IOException e) {
//            logger.error("", e);
//        }
//    }
    
    public static void writeToFile(byte[] content) throws IOException {
        OutputStream outputStream = new FileOutputStream("outputText.txt");
        outputStream.write(content);
        outputStream.close();
    }

    public static void getInformationAboutApp() {
        logger.info("Information handling app. You can read text from file and parse it. \nYou can use 3 functions of this app: \n 1 - find word in the first sentence, which is only in this sentence. \n 2 - In all the questionable sentence find and write words with needed size. \n 3 - Words with first vowel letter sort by alphabet by first consonant letter.\nApp was made by Grigorieva Olga \n");
    }

    public static boolean chooseOption() {
        logger.info("Hello! It's information handling app.");
        logger.info("Please, choose option. \n " +
                "1 - read and parse text from file \n " +
                "2 - find word in the first sentence, which is only in this sentence. \n " +
                "3 - in all the quest sentence find and write words with needed size. \n " +
                "4 - words with first vowel letter sort by alphabet by first consonant letter. \n " +
                "5 - write text to file \n " +
                "6 - exit \n");
        Scanner in = new Scanner(System.in);
        switch (in.nextLine()) {
            case "1":
//                parseTextFromFile();
                return true;
            case "2":
                findUniqueWords();
                return true;
            case "3":
                findWordsInQuestSentence();
                return true;
            case "4":
                findWordsWithFirstVowel();
                return true;
            case "5":
                //writeToFile();
                return true;
            case "6":
                logger.info("Thank you for using app! Goodbye!");
                return false;
            default:
                logger.info("Please, enter number from 1 to 6.");
                return true;
        }
    }

    private static void findWordsWithFirstVowel() {
    }


    public static boolean isValidInteger(int input) {
        return input > 0 && input < Integer.MAX_VALUE;
    }

    private static void findWordsInQuestSentence() {
        Scanner in = new Scanner(System.in);
        logger.info("Please, enter size of words for quest sentence.");
        int size = 0;
        do {
            logger.info("Please, enter amount of total.");
            try {
                size = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                logger.error("Invalid number");
            }
        } while (!isValidInteger(size));

//        QuestSentence questSentence = new QuestSentence(size);
//        questSentence.findWords();
    }

    private static void findUniqueWords() {

    }

    public static void main(String[] args) {
        try {
            String plainText = new String(readFromFile());
            TextParser textParser = new TextParser();
            Text text = textParser.parse(plainText);
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        while (chooseOption()){
//        }
    }
}