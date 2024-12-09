import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        // Define folder paths for three topics
        String topic1Path = "C:\\Users\\lukas\\IdeaProjects\\FinalPGWSLab\\src\\Topic1\\";
        String topic2Path = "C:\\Users\\lukas\\IdeaProjects\\FinalPGWSLab\\src\\Topic2\\";
        String topic3Path = "C:\\Users\\lukas\\IdeaProjects\\FinalPGWSLab\\src\\Topic3\\";

        String stopWordsPath = "C:\\Users\\lukas\\IdeaProjects\\FinalPGWSLab\\src\\stopwords.txt";
        String positiveWordsPath = "C:\\Users\\lukas\\IdeaProjects\\FinalPGWSLab\\src\\positive-words.txt";
        String negativeWordsPath = "C:\\Users\\lukas\\IdeaProjects\\FinalPGWSLab\\src\\negative-words.txt";

        List<String> stopWords = FileHandler.readStopWords(stopWordsPath);
        List<String> positiveWords = FileHandler.readPositiveWords(positiveWordsPath);
        List<String> negativeWords = FileHandler.readNegativeWords(negativeWordsPath);

        TextProcessor textProcessor = new TextProcessor(stopWords, positiveWords, negativeWords);
        FileHandler fileHandler = new FileHandler();
        FileAnalyzer fileAnalyzer = new FileAnalyzer();


        // User Interface
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the File Program");
            System.out.println("Select a topic to view articles:");
            System.out.println("1. Topic 1: Football");
            System.out.println("2. Topic 2: Golf");
            System.out.println("3. Topic 3: Basketball");
            System.out.println("4. Use your own folder of articles");
            System.out.println("5. Exit the program");
            System.out.print("Enter your choice: ");
            int topicChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (topicChoice) {
                case 1:
                    handleTopicSelection(topic1Path, fileHandler, fileAnalyzer, textProcessor);
                    break;
                case 2:
                    handleTopicSelection(topic2Path, fileHandler, fileAnalyzer, textProcessor);
                    break;
                case 3:
                    handleTopicSelection(topic3Path, fileHandler, fileAnalyzer, textProcessor);
                    break;
                case 4:
                    System.out.print("Enter the path to your folder: ");
                    String customPath = scanner.nextLine();
                    handleTopicSelection(customPath, fileHandler, fileAnalyzer, textProcessor);
                    break;
                case 5:
                    System.out.println("Exiting Program...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }

    //Reads folders and prints article data
    private static void handleTopicSelection(String folderPath, FileHandler fileHandler, FileAnalyzer fileAnalyzer, TextProcessor textProcessor) {
        List<Path> articles = loadFilesFromFolder(folderPath);

        if (articles.isEmpty()) {
            System.out.println("No articles found in the folder: " + folderPath);
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect an article to view stats:");
            for (int i = 0; i < articles.size(); i++) {
                System.out.println((i + 1) + ". " + articles.get(i).getFileName());
            }
            System.out.println((articles.size() + 1) + ". Back to topic selection");
            System.out.print("Enter your choice: ");
            int articleChoice = scanner.nextInt();

            if (articleChoice == articles.size() + 1) {
                break;
            }

            if (articleChoice < 1 || articleChoice > articles.size()) {
                System.out.println("Invalid article choice! Please try again.");
                continue;
            }

            Path selectedFile = articles.get(articleChoice - 1);
            System.out.println("\nFiltered Article: " + selectedFile.getFileName());
            System.out.println("===================================");

            try {
                List<String> articleLines = fileHandler.readFile(selectedFile.toString());
                List<String> cleanedLines = textProcessor.removeStopWords(articleLines);

                // Print the cleaned article
                for (String line : cleanedLines) {
                    System.out.println(line);
                }

                // Show statistics
                int wordCount = fileAnalyzer.countWords(articleLines);
                System.out.println("Word Count: " + wordCount);

                int statementCount = fileAnalyzer.countStatements(articleLines);
                System.out.println("Number of Statements: " + statementCount);

                System.out.println("Word Frequencies: ");
                fileAnalyzer.rankWordsByFrequency(cleanedLines);

                System.out.println("Number of unique words: ");
                int uniqueWords = fileAnalyzer.countUniqueWords(articleLines);
                System.out.println(uniqueWords);

                List<String> words = new ArrayList<>();
                for (String line : cleanedLines) {
                    for (String word : line.split("\\s+")) {
                        words.add(word.toLowerCase());
                    }
                }

                // Sentiment Analysis
                textProcessor.analyzeSentiment(words);
            } catch (IOException e) {
                System.out.println("Error reading file: " + selectedFile);
            }
        }
    }

    //Loads all files within a folder
    private static List<Path> loadFilesFromFolder(String folderPath) {
        try {
            return Files.list(Paths.get(folderPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error loading files from folder: " + folderPath);
            return Collections.emptyList();
        }
    }
}
