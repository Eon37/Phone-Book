package phonebook;

import java.util.List;

public class JumpSearch implements Search {

    private int substrIndex;

    @Override
    public int perform(List<String> hay, String needle) {
        if (hay == null || hay.isEmpty()) {
            return 0;
        }

        int foundIndex = jumpSearch(hay, needle);
        substrIndex = hay.get(foundIndex).indexOf(" ");
        return foundIndex == -1 ? 0 : Integer.parseInt(hay.get(foundIndex).substring(0, substrIndex));
    }

    private int jumpSearch(List<String> hay, String needle) {

        /* If list is empty, the element is not found */
        if(hay == null || hay.isEmpty()) return -1;

        int currRight = 0;
        int prevRight = 0;

        /* Check the first element */
        substrIndex = hay.get(currRight).indexOf(" ") + 1;
        if(hay.get(currRight).substring(substrIndex).equals(needle)) {
            return 0;
        }

        int blockSize = (int) Math.sqrt(hay.size());

        /* Finding a block where the element may be present */
        while(currRight < hay.size() - 1) {
            currRight = Math.min(hay.size() - 1, currRight + blockSize);

            substrIndex = hay.get(currRight).indexOf(" ") + 1;
            if(hay.get(currRight).substring(substrIndex).compareTo(needle) > 0) {
                break;
            }

            prevRight = currRight;

        }

        /* If the last block is reached and it cannot contain the target value */
        substrIndex = hay.get(currRight).indexOf(" ") + 1;
        if((currRight == hay.size() - 1) &&
                needle.compareTo(hay.get(currRight).substring(substrIndex)) > 0) {
            return -1;
        }

        return backwardSearch(hay, needle, prevRight,currRight);
    }

    private int backwardSearch(List<String> hay, String needle, int leftExcl, int rightIncl) {
        for (int i = rightIncl - 1; i > leftExcl; i--) {
            substrIndex = hay.get(i).indexOf(" ") + 1;
            if(hay.get(i).substring(substrIndex).equals(needle)) {
                return i;
            }
        }

        return -1;
    }
}
