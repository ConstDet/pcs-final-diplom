public class PageEntry implements Comparable<PageEntry> {
    private final String pdfName;
    private final int page;
    private final int count;

    public PageEntry(String pdfName, int page, int count) {
        this.pdfName = pdfName;
        this.page = page;
        this.count = count;
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
        if (obj == null || getClass() != obj.getClass()) return false;
        PageEntry that = (PageEntry) obj;
        if (pdfName != that.pdfName) return  false;
        if (page != that.page) return false;
        if (count != that.count) return false;
        return this == obj;
    }

    @Override
    public int hashCode() {
        int result = pdfName == null ? 0 : pdfName.hashCode();
        result = 31 * result + page;
        result = 31 * result + count;
        return result;
    }

    @Override
    public String toString() {
        return "{\"pdf\": \"" + pdfName + "\", \"page\": " + page + ", \"count\"= " + count + "}";
    }
}
