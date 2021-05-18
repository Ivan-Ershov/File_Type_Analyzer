package analyzer.algorithm.search;

import analyzer.algorithm.search.SearchAlgorithm;

public class NaiveAlgorithm implements SearchAlgorithm {
    @Override
    public boolean hasPattern(String text, String pattern) {
        for (int i = 0; i < (text.length() - pattern.length() + 1); i++) {
            if (text.startsWith(pattern, i)) {
                return true;
            }
        }

        return false;
    }
}
