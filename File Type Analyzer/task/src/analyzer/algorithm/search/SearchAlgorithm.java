package analyzer.algorithm.search;

@FunctionalInterface
public interface SearchAlgorithm {
    boolean hasPattern(String text, String pattern);
}
