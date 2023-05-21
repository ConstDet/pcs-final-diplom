import java.util.Map;

public class IndexedWord implements Comparable<IndexedWord> {
    private final String word;
    private final String pdfName;
    private final int page;
    private final int count;

    public IndexedWord(String word, String pdfName, int page, int count) {
        this.word = word;
        this.pdfName = pdfName;
        this.page = page;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public String getPdfName() {
        return pdfName;
    }

    public int getPage() {
        return page;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compareTo(IndexedWord o) {
        return this.pdfName.compareTo(o.pdfName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        IndexedWord that = (IndexedWord) obj;
        if (pdfName != that.pdfName) return  false;
        if (page != that.page) return false;
        if (count != that.count) return false;
        return word.equals(((IndexedWord) obj).word);
    }

    @Override
    public int hashCode() {
        int result = word == null ? 0 : word.hashCode();
        result = pdfName == null ? 0 : 31 * result + pdfName.hashCode();
        result = 31 * result + page;
        result = 31 * result + count;
        return result;
    }

    @Override
    public String toString() {
        return "Слово: " + word
                + " Имя файла: " + pdfName
                + " Страница: " + page
                + " Количество: " + count;
    }
}
