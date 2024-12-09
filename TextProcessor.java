import java.util.*;

public class TextProcessor {

    //Constructors
    private List<String> stopWords;
    private List<String> positiveWords;
    private List<String> negativeWords;

    //Positive and Negative Word list Constructors
    public TextProcessor(List<String> stopWords, List<String> positiveWords, List<String> negativeWords) {
        this.stopWords = stopWords;
        this.positiveWords = positiveWords;
        this.negativeWords = negativeWords;
    }


    // Remove stop words from the text
    public List<String> removeStopWords(List<String> textLines) {
        List<String> processedLines = new ArrayList<>();
        for (String line : textLines) {
            StringBuilder filteredLine = new StringBuilder();
            for (String word : line.split("\\s+")) {
                if (!stopWords.contains(word)) {
                    filteredLine.append(word).append(" ");
                }
            }
            processedLines.add(filteredLine.toString().trim());
        }
        return processedLines;
    }

    //Analyze sentiment by counting positive and negative words
    public void analyzeSentiment(List<String> words) {
        int positiveCount = 0;
        int negativeCount = 0;

        for (String word : words) {
            if (positiveWords.contains(word)) {
                positiveCount++;
            }
            else if (negativeWords.contains(word)) {
                negativeCount++;
            }
        }
        System.out.println("Positive words count: " + positiveCount);
        System.out.println("Negative words count: " + negativeCount);
        if (positiveCount > negativeCount) {
            System.out.println("The article is positive");
        }
        if (negativeCount > positiveCount) {
            System.out.println("The article is negative");
        }
        if (positiveCount == negativeCount){
            System.out.println("The article is neutral.");
        }
    }
}
