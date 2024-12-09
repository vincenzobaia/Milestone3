import java.util.*;

public class FileAnalyzer {

    // Method for counting number of words
    public int countWords(List<String> textLines) {
        int wordCount = 0;
        for (String line : textLines) {
            String[] words = line.split("\\s+");
            wordCount += words.length;
        }
        return wordCount;
    }

    // Counts the amount times that words appear
    public int countUniqueWords(List<String> textLines) {
        Set<String> uniqueWords = new HashSet<>();
        for (String line : textLines) {
            String[] words = line.split("\\s+");
            for (String word : words) {
                if (!uniqueWords.contains(word)) {
                    uniqueWords.add(word);
                }
            }
        }
        return uniqueWords.size();
    }

    // Method for counting number of statements
    public int countStatements(List<String> textLines) {
        int statementCount = 0;
        for (String line : textLines) {
            statementCount += line.split("[.!?]").length;
        }
        return statementCount;
    }

    // Method to rank words by frequency
    public void rankWordsByFrequency(List<String> textLines) {
        List<String> uniqueWords = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        for (String line : textLines) {
            String[] words = line.toLowerCase().split("\\s+");
            for (String word : words) {
                if (!word.isEmpty()) {
                    int index = uniqueWords.indexOf(word);
                    if (index == -1) {
                        uniqueWords.add(word);
                        counts.add(1);
                    } else {
                        counts.set(index, counts.get(index) + 1);
                    }
                }
            }
        }

        // Sort both lists by frequency in descending order
        for (int i = 0; i < counts.size(); i++) {
            for (int j = i + 1; j < counts.size(); j++) {
                if (counts.get(i) < counts.get(j)) {
                    // Swap counts
                    int tempCount = counts.get(i);
                    counts.set(i, counts.get(j));
                    counts.set(j, tempCount);

                    // Swap corresponding words
                    String tempWord = uniqueWords.get(i);
                    uniqueWords.set(i, uniqueWords.get(j));
                    uniqueWords.set(j, tempWord);
                }
            }
        }

        // Print the ranked words and their frequencies
        for (int i = 0; i < uniqueWords.size(); i++) {
            System.out.println(uniqueWords.get(i) + ": " + counts.get(i));
        }
    }
}

