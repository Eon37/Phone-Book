package phonebook;

import java.util.Collections;
import java.util.List;

public class BubbleSort extends TimedSort {

    @Override
    public boolean timedSort(List<String> list, long compareTime) {
        if (list == null || list.isEmpty()) {
            return true;
        }

        this.compareTime = compareTime;
        startTime = System.currentTimeMillis();

        return timedBubbleSort(list, compareTime);
    }

    private boolean timedBubbleSort(List<String> list, long compareTime) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 1; j < list.size() - i; j++) {
                substrIndex = list.get(j).indexOf(" ") + 1;
                if (list.get(j).substring(substrIndex)
                        .compareTo(list.get(j - 1).substring(substrIndex)) < 0) {
                    Collections.swap(list, j, j - 1);
                }
            }
            if (System.currentTimeMillis() - startTime > compareTime * 10) return false;
        }
        return true;
    }
}
