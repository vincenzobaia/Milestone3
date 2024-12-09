import java.io.*;
import java.util.*;

public class FileHandler {
    // Create ArrayList to read file
    public List<String> readFile(String filePath) throws IOException {
        List<String> fileText = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileText.add(line);
            }
        }
        return fileText;
    }
    // Read stopWord list
    public static List<String> readStopWords(String stopWordsPath) throws IOException {
        List<String> stopWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(stopWordsPath))) {
            String stopWord;
            while ((stopWord = reader.readLine()) != null) {
                stopWords.add(stopWord.trim().toLowerCase());
            }
        }
        return stopWords;
    }

    //Read positive word list
    public static List<String> readPositiveWords(String positiveWordsPath) throws IOException {
        List<String> positiveWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(positiveWordsPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                positiveWords.add(line.trim().toLowerCase());
            }
        }
        return positiveWords;
    }

    //Read negative word list
    public static List<String> readNegativeWords(String negativeWordsPath) throws IOException {
        List<String> negativeWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(negativeWordsPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                negativeWords.add(line.trim().toLowerCase());
            }
        }
        return negativeWords;
    }

}
