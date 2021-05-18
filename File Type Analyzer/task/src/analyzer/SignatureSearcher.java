package analyzer;

import analyzer.algorithm.search.SearchAlgorithm;

public class SignatureSearcher {
    SearchAlgorithm searchAlgorithm;

    public SignatureSearcher(SearchAlgorithm searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    public boolean hasSignature(String text, String signature) {
        return searchAlgorithm.hasPattern(text, signature);
    }
}
