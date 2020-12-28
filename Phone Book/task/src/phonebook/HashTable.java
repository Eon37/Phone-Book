package phonebook;

public class HashTable<T> {
    private final int size;
    private TableEntry[] table;

    public HashTable(int size) {
        this.size = size;
        table = new TableEntry[size];
    }

    public int getSize() {
        return size;
    }

    private int findEntryIndex(String key) { //hash function I suppose
        int hash = (key.hashCode() & 0x7FFFFFFF) % size;

        while(table[hash] != null && !table[hash].getKey().equals(key)) {
            hash = (hash + 1) % size;

            if(hash == ((key.hashCode() & 0x7FFFFFFF) % size)){ //if walks through the hole table and returns to the beginning hash
                return -1;
            }
        }
        return hash;
    }

    public boolean put(String key, T value) {
        int idx = findEntryIndex(key);
        if (idx == -1) return false;

        table[idx] = new TableEntry<T>(key, value);
        return true;
    }

    public T get(String key) {
        int idx = findEntryIndex(key);
        if (idx == -1) return null;

        return (T) table[idx].getValue();
    }
}
