import java.util.*;
public class HashTable {
    static Scanner sc = new Scanner(System.in);
    private final int size;
    private final Entry[] table;

    private static class Entry {
        private final int key;
        private int value;
        private Entry next;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }
    }

    // Constructor to initialize the hashtable with a given size
    public HashTable(int size) {
        this.size = size;
        this.table = new Entry[size];
    }

    // Compute the hash function for a given key
    private int hashFunction(int key) {
        return key % size;
    }

    // Insert a key-value pair into the hashtable
    public void insert(int key, int value) {
        int index = hashFunction(key);
        Entry entry = table[index];
        while (entry != null) {
            if (entry.getKey() == key) {
                entry.value = value;
                return;
            }
            entry = entry.getNext();
        }
        Entry newEntry = new Entry(key, value);
        newEntry.setNext(table[index]);
        table[index] = newEntry;
    }

    // Retrieve the value associated with a given key
    public int get(int key) {
        int index = hashFunction(key);
        Entry entry = table[index];
        while (entry != null) {
            if (entry.getKey() == key) {
                return entry.getValue();
            }
            entry = entry.getNext();
        }
        return 1;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            Entry entry = table[i];
            while (entry != null) {
                System.out.println("(Key: " + entry.getKey() + ", Value: " + entry.getValue() + ") ");
                entry = entry.getNext();
            }
        }
    }
    
    // Delete a key-value pair from the hashtable
    public void remove(int key) {
        int index = hashFunction(key);
        Entry entry = table[index];
        Entry previous = null;
        while (entry != null) {
            if (entry.getKey() == key) {
                if (previous == null) {
                    table[index] = entry.getNext();
                } else {
                    previous.setNext(entry.getNext());
                }
                return;
            }
            previous = entry;
            entry = entry.getNext();
        }
    }
    
    public int size() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            Entry entry = table[i];
            while (entry != null) {
                count++;
                entry = entry.getNext();
            }
        }
        return count;
    }
    


    public static void main(String[] args) {
        HashTable hashtable = new HashTable(10);
        boolean exit = false;
        int key, value;
        while(!exit) {
            System.out.println("1. Insertion");
            System.out.println("2. Deletion");
            System.out.println("3. Get Value");
            System.out.println("4. Size");
            System.out.println("5. Display HashTable");
            System.out.println("6. Exit");
            System.out.println("Enter Choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Key: ");
                    key = sc.nextInt();
                    System.out.println("Enter Value: ");
                    value = sc.nextInt();
                    hashtable.insert(key, value);
                    break;
                case 2:
                    System.out.println("Enter Key Whose Value to be deleted: ");
                    key = sc.nextInt();
                    hashtable.remove(key);
                    break;
                case 3:
                    System.out.println("Get Value by Key");
                    key = sc.nextInt();
                    value = hashtable.get(key);
                    System.out.println("Key: "+key+" Value: " + value);
                    break;
                case 4:
                    System.out.println("Size: ");
                    hashtable.size();
                    break;
                case 5:
                    System.out.println("Hashtable: ");
                    hashtable.display();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }
}
