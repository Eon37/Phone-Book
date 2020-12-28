package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        List<String> phoneBook = Files.readAllLines(Path.of("D:\\Prj\\_files\\PhoneBook\\directory.txt"));
        List<String> find = Files.readAllLines(Path.of("D:\\Prj\\_files\\PhoneBook\\find.txt"));

        Sort sort;
        Search search = new LinearSearch();

        System.out.println("Start searching (linear search)...");
        List<Integer> numbers = new ArrayList<>();
        long linTime = timedSearch(search, phoneBook, find, numbers);
        PrintInfo.printResult(numbers.size(), find.size(), linTime);
        System.out.println();

        numbers.clear();
        System.out.println("Start searching (bubble sort + jump search)...");
        sort = new BubbleSort();
        search = new JumpSearch();
        SortNSearch(phoneBook, sort, linTime, search, find, numbers);
        System.out.println();

        numbers.clear();
        System.out.println("Start searching (quick sort + binary search)...");
        sort = new QuickSort();
        search = new BinarySearch();
        SortNSearch(phoneBook, sort, linTime, search, find, numbers);
        System.out.println();

        numbers.clear();
        System.out.println("Start searching (hash table)...");
        HashTable<Integer> ht = new HashTable<>(phoneBook.size());
        long createTime = fillHashTable(ht, phoneBook);
        long searchTime = searchInHashTable(ht, find, numbers);
        PrintInfo.printResult(numbers.size(), find.size(), createTime + searchTime);
        PrintInfo.printCreateTime(createTime);
        PrintInfo.printSearchTime(searchTime);

    }

    public static void SortNSearch(List<String> phoneBook, Sort sort, long compareTime, Search search, List<String> find, List<Integer> outNumbers) {
        long sortTime;
        long searchTime;
        boolean successfulSort;

        long curTime = System.currentTimeMillis();

        successfulSort = sort.timedSort(phoneBook, compareTime);

        sortTime = System.currentTimeMillis() - curTime;

        if(!successfulSort) search = new LinearSearch();

        searchTime = timedSearch(search, phoneBook, find, outNumbers);

        PrintInfo.printResult(outNumbers.size(), find.size(), sortTime + searchTime);
        PrintInfo.printSortTime(sortTime, successfulSort);
        PrintInfo.printSearchTime(searchTime);
    }

    public static long timedSearch(Search search, List<String> phoneBook, List<String> find, List<Integer> outNumbers) {
        long curTime = System.currentTimeMillis();

        for (String name : find) {
            int number = search.perform(phoneBook, name);
            if (number != 0) outNumbers.add(number);
        }

        return System.currentTimeMillis() - curTime;
    }

    public static long fillHashTable(HashTable<Integer> ht, List<String> list) {
        long start = System.currentTimeMillis();

        int substrIndex;
        for (String s : list) {
            substrIndex = s.indexOf(" ");
            ht.put(s.substring(substrIndex + 1), Integer.parseInt(s.substring(0, substrIndex)));
        }

        return System.currentTimeMillis() - start;
    }

    private static long searchInHashTable(HashTable<Integer> ht, List<String> find, List<Integer> outNumbers) {
        long start = System.currentTimeMillis();

        for(String s : find) {
            outNumbers.add(ht.get(s));
        }

        return System.currentTimeMillis() - start;
    }

}
