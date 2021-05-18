package analyzer.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

public class MergeSort<T extends Comparable<T>> implements SortAlgorithm<T>{
    @Override
    public void sort(List<T> input) {
        mergeSort(input, 0, input.size() - 1);
    }

    private void mergeSort(List<T> input, int left, int right) {
        if ((right - left + 1) <= 1) {
            return;
        }

        int middle = left + (right - left + 1) / 2;

        mergeSort(input, left, middle - 1);
        mergeSort(input, middle, right);

        merge(input, left, middle, right);
    }

    private void merge(List<T> input, int left, int middle, int right) {
        int i = left;
        int j = middle;
        List<T> temp = new ArrayList<>(right - left);

        while (i < middle && j <= right) {
            if (input.get(i).compareTo(input.get(j)) <= 0) {
                temp.add(input.get(i));
                i++;
            } else {
                temp.add(input.get(j));
                j++;
            }
        }

        for (;i < middle; i++) {
            temp.add(input.get(i));
        }

        for (;j < right; j++) {
            temp.add(input.get(j));
        }

        for (i = left; i <= right; i++) {
            input.set(i, temp.get(i - left));
        }

    }

}
