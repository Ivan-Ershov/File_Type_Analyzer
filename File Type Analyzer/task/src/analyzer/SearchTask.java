package analyzer;

import analyzer.algorithm.search.SearchAlgorithm;
import analyzer.model.Signature;

import java.util.List;
import java.util.concurrent.Callable;

public class SearchTask implements Callable<String> {
    private static final String DEFAULT_TITLE = "Unknown file type";

    private final SearchAlgorithm algorithm;
    private final String text;
    private final List<Signature> signatures;

    public SearchTask (SearchAlgorithm algorithm, String text, List<Signature> signatures) {
        this.algorithm = algorithm;
        this.text = text;
        this.signatures = signatures;
    }

    @Override
    public String call() {
        for (Signature signature : signatures) {
            if (algorithm.hasPattern(text, signature.getSignature())) {
                return signature.getTitle();
            }
        }

        return DEFAULT_TITLE;
    }
}
