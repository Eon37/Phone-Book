package phonebook;

public class PrintInfo {
    public static void printResult(int found, int outOf, long time) {
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n",
                found, outOf,
                time / 1000 / 60,
                time / 1000 % 60,
                time % 1000);
    }

    public static void printSortTime(long sortTime, boolean succSort) {
        System.out.printf("Sorting time: %d min. %d sec. %d ms.",
                sortTime / 1000 / 60,
                sortTime / 1000 % 60,
                sortTime % 1000);
        if(!succSort) System.out.print(" - STOPPED, moved to linear search");
        System.out.print("\n");
    }

    public static void printCreateTime(long createTime) {
        System.out.printf("Creating time: %d min. %d sec. %d ms.\n",
                createTime / 1000 / 60,
                createTime / 1000 % 60,
                createTime % 1000);
    }

    public static void printSearchTime(long searchTime) {
        System.out.printf("Searching time: %d min. %d sec. %d ms.\n",
                searchTime / 1000 / 60,
                searchTime / 1000 % 60,
                searchTime % 1000);
    }
}
