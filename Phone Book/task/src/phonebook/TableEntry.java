package phonebook;

public class TableEntry<T> {
    private final String key;
    private final T value;

    public TableEntry(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }
}