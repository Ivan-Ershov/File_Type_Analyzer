package analyzer.model;

public class Signature implements Comparable<Signature> {
    private final int priory;
    private final String title;
    private final String signature;

    public Signature(int priory, String title, String signature) {
        this.priory= priory;
        this.title = title;
        this.signature = signature;
    }

    public int getPriory() {
        return priory;
    }

    public String getSignature() {
        return signature;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(Signature signature) {
        return priory - signature.priory;
    }
}
