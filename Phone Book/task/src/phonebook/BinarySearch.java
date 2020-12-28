package phonebook;

import java.util.List;

public class BinarySearch implements Search {

    private int substrIndex;

    @Override
    public int perform(List<String> hay, String needle) {
        if (hay == null || hay.isEmpty()) {
            return 0;
        }

        int foundIndex = binarySearch(hay, needle, 0, hay.size() - 1);
        substrIndex = hay.get(foundIndex).indexOf(" ");
        return foundIndex == -1 ? 0 : Integer.parseInt(hay.get(foundIndex).substring(0, substrIndex));
    }

    private int binarySearch(List<String> hay, String needle, int left, int right) {
        if (left > right) return -1;

        int mid = left + (right - left)/2;

        substrIndex = hay.get(mid).indexOf(" ") + 1;
        String midElem = hay.get(mid).substring(substrIndex);

        if (midElem.equals(needle)) return mid;
        else if (midElem.compareTo(needle) > 0) return binarySearch(hay, needle, left, mid - 1);
        else return binarySearch(hay, needle, mid + 1, right);
    }
}
