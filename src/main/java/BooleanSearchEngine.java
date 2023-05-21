import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BooleanSearchEngine implements SearchEngine {
    List<IndexedWord> indexedWordsPerPage;

    public BooleanSearchEngine(File pdfsDir) throws IOException {
        indexedWordsPerPage = new ArrayList<>();
        List<File> filesFolder = List.of(Objects.requireNonNull(pdfsDir.listFiles()));
        for (File pathToFile : filesFolder) {
            try (var doc = new PdfDocument(new PdfReader(pathToFile));) {
                int numberOfPages = doc.getNumberOfPages();
                for (int i = 1; i <= numberOfPages; i++) {
                    Map<String, Integer> freqs = new HashMap<>();
                    PdfPage pageDocument = doc.getPage(i);
                    var text = PdfTextExtractor.getTextFromPage(pageDocument);
                    String[] words = text.split("\\P{IsAlphabetic}+");
                    for (var word : words) {
                        if (word.isEmpty()) continue;
                        word = word.toLowerCase();
                        freqs.put(word, freqs.getOrDefault(word, 0) + 1);
                    }
                    for (Map.Entry<String, Integer> wordFreqs : freqs.entrySet()) {
                        indexedWordsPerPage.add(new IndexedWord(wordFreqs.getKey(), pathToFile.getName(), i,
                                wordFreqs.getValue()));
                    }
                }
            }
        }
    }

    @Override
    public List<PageEntry> search(String word) {
        List<PageEntry> wordSearchResult = new ArrayList<>();
        for (IndexedWord indexedWord : indexedWordsPerPage) {
            if (indexedWord.getWord().equals(word)) {
                wordSearchResult.add(new PageEntry(
                        indexedWord.getPdfName(),
                        indexedWord.getPage(),
                        indexedWord.getCount()));
            }
        }
        Collections.sort(wordSearchResult);
        return wordSearchResult;
    }
}
