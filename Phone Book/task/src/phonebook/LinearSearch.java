package phonebook;

import java.util.List;

public class LinearSearch implements Search{

    @Override
    public int perform(List<String> hay, String needle) {

        if (hay == null || hay.isEmpty()) {
            return 0;
        }


        for (String line : hay) {
            int index = line.indexOf(" ");

            if (line.substring(index + 1).equals(needle)) {
                return Integer.parseInt(line.substring(0, index));
            }
        }

        return 0;
    }
}
