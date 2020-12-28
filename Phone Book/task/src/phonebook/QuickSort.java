package phonebook;

import java.util.Collections;
import java.util.List;

public class QuickSort extends TimedSort {

    @Override
    public boolean timedSort(List<String> list, long compareTime) {
        if (list == null || list.isEmpty()) {
            return true;
        }

        this.compareTime = compareTime;
        startTime = System.currentTimeMillis();

        return timedQuickSort(list, 0, list.size() - 1);
    }

    private boolean timedQuickSort(List<String> list, int left, int right) {
        if(System.currentTimeMillis() - startTime > compareTime * 10) return false;

        if (left < right) {
            int pivotIndex = partition(list, left, right);

            timedQuickSort(list, left, pivotIndex - 1);
            timedQuickSort(list, pivotIndex + 1, right);
        }
        return true;
    }

    private int partition(List<String> list, int left, int right) {
        substrIndex = list.get(right).indexOf(" ") + 1;
        String pivot = list.get(right).substring(substrIndex);
        int partitionIndex = left; //the first element that if greater than the pivot then will be moved

        //11 15 3 8 10 - 0
        //3 15 11 8 10 - 2
        //3 8 11 15 10 - 3
        //3 8 10 11 15 - 4
        for (int i = left; i <= right; i++) {
            substrIndex = list.get(i).indexOf(" ") + 1;
            if (list.get(i).substring(substrIndex).compareTo(pivot) <= 0) {
                Collections.swap(list, i, partitionIndex);
                partitionIndex++;
            }
        }

        return partitionIndex - 1;
    }
}
