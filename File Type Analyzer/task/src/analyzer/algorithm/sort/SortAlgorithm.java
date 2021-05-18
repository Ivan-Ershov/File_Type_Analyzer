package analyzer.algorithm.sort;

import java.util.List;

@FunctionalInterface
public interface SortAlgorithm<T> {
    void sort(List<T> input);
}
