public class PageEntry implements Comparable<PageEntry> {
    private final String word;
    private final String pdfName;
    private final int page;
    private final int count;

    public PageEntry(String word, String pdfName, int page, int count) {
        this.word = word;
        this.pdfName = pdfName;
        this.page = page;
        this.count = count;
    }

    public String getWord() {
        return word;
    }
    @Override
    public int compareTo(PageEntry o) {
        if (this.count < o.count) {
            return 1;
        } else if (this.count > o.count) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PageEntry that = (PageEntry) obj;
        if (pdfName != that.pdfName) return  false;
        if (page != that.page) return false;
        if (count != that.count) return false;
        return word.equals(((PageEntry) obj).word);
    }

    @Override
    public int hashCode() {
        int result = word == null ? 0 : word.hashCode();
        result = pdfName == null ? 0 : 31 * result + pdfName.hashCode();
        result = 31 * result + page;
        result = 31 * result + count;
        return result;
    }
}
