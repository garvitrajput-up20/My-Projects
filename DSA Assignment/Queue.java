import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Queue<T> implements Iterable<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T data = first.data;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.data;
    }

    public boolean contains(T data) {
        for (T element : this) {
            if (element.equals(data)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public T center() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int centerIndex = size / 2;
        Node<T> current = first;
        for (int i = 0; i < centerIndex; i++) {
            current = current.next;
        }
        return current.data;
    }

    @SuppressWarnings("unchecked")
    public void sort() {
        if (isEmpty()) {
            return;
        }
        Node<T> current = first;
        Node<T> index = null;
        T temp;
        while (current != null) {
            index = current.next;
            while (index != null) {
                if (((Comparable<T>) current.data).compareTo(index.data) > 0) {
                    temp = current.data;
                    current.data = index.data;
                    index.data = temp;
                }
                index = index.next;
            }
            current = current.next;
        }
    }

    public void reverse() {
        if (isEmpty()) {
            return;
        }
        Node<T> previous = null;
        Node<T> current = first;
        Node<T> next = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        last = first;
        first = previous;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Node<T> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    public void traverse() {
        for (T element : this) {
            System.out.print(element + "<->");
        }
        System.out.println();
    }

    private boolean isEmpty() {
        return size == 0;
    }

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        boolean exit = false;
        while (!exit) {
            System.out.println("\nEnter an option:");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek");
            System.out.println("4. Contains");
            System.out.println("5. Size");
            System.out.println("6. Center");
            System.out.println("7. Sort");
            System.out.println("8. Reverse");
            System.out.println("9. Traverse/Print");
            System.out.println("10. Exit");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (option) {
                case 1:
                    System.out.println("Enter one or more values separated by spaces:");
                    String input = scanner.nextLine();
                    String[] values = input.split(" ");
                    for (String value : values) {
                        queue.enqueue(Integer.parseInt(value));
                    }
                    System.out.print("Enqueued values: " + input);
                    break;
                case 2:
                    int dequeuedValue = queue.dequeue();
                    System.out.print("Dequeued value: " + dequeuedValue);
                    break;
                case 3:
                    int peekValue = queue.peek();
                    System.out.print("Peeked value: " + peekValue);
                    break;
                case 4:
                    System.out.println("Enter a value to search:");
                    int searchValue = scanner.nextInt();
                    boolean contains = queue.contains(searchValue);
                    System.out.print("Contains " + searchValue + ": " + contains);
                    break;
                case 5:
                    int size = queue.size();
                    System.out.print("Queue size: " + size);
                    break;
                case 6:
                    int center = queue.center();
                    System.out.print("Center value: " + center);
                    break;
                case 7:
                    queue.sort();
                    System.out.print("Sorted Queue: ");
                    queue.traverse();
                    break;
                case 8:
                    queue.reverse();
                    System.out.print("Queue Reversed: ");
                    queue.traverse();
                    break;
                case 9:
                    System.out.println("Print Queue: ");
                    queue.traverse();
                    break;
                case 10:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}