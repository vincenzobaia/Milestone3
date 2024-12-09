Milestone-1-File Analyzer
Group Members - Lukas Voges, Vincenzo Baia, Brian Connolly

Fairfield University School of Engineering and Computing BA and BS, Computer Science

Project Purpose:

Start: The process begins
Read Articles and Stopwords: The program reads the article files, stopword list, positive and negative words list
Clean and Filter Text: The text is processed to remove stopwords and cleaned
Count Words and Statements: The word and statement counts are calculated
Rank Words by Frequency: The program ranks words based on their frequency in the text
Sentiment Analysis: The program checks if a word is positive or negative, then caculates if articel is positive or negative
Print Results: The results (filtered text, word counts, frequencies, unique words, and sentiment analysis) are printed
End: The process is completed
Methods/Classes:

File Handler. reads txt file, stop words file, and pos and neg words file
Text Processor, Cleans the given txt article of stop words, punctuation, and analyzes sentiment score
File Analyzer, Provides statistics of the articles including word count, statements, and words ranked by frequency
Main Method, Calls upon all aforementioned methods, does it for 3 different txt files![UML Diagram](https://github.com/user-attachments/assets/4019c4df-a0ca-4e83-8813-64edef88c361)
![Screenshot 2024-12-09 002656](https://github.com/user-attachments/assets/d9d02a32-f650-4d3b-b385-392a21c6188c)

Things Refactored:
Main
TextProcessor

The main reasoning behind these two is that after milestone two, far too much of our code and logic was inside the main, using terrible logic to make it so that we were basically brute forcing a way to print all articles at once. As a result, when making it into a UI, we moved a majority of the logic that was taking place in the main into the text processor. Now, the only methods remaining in the main method was the UI and the method to load files in a folder. 
