public class PrevalenceEntry implements Comparable<PrevalenceEntry> {

    private String word;
    private Integer prevalence;


    public String getWord() {
        return word;
    }


    public void setWord(String word) {
        this.word = word;
    }


    public Integer getPrevalence() {
        return prevalence;
    }


    public void setPrevalence(Integer prevalence) {
        this.prevalence = prevalence;
    }


    public PrevalenceEntry getEntry() {
        return this;
    }


    public PrevalenceEntry(String word, Integer prevalence) {
        this.word = word;
        this.prevalence = prevalence;
    }


    @Override
    public int compareTo(PrevalenceEntry o) {
        return this.prevalence.compareTo(o.prevalence);
    }
}
