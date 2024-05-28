import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PriorityQueue implements Iterable<Integer> {
    static Scanner sc = new Scanner(System.in);
    private int[] heap;
    private int size;
    private int capacity;

    public PriorityQueue(int capacity) {
        this.heap = new int[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public void enqueue(int item) {
        if (size == capacity) {
            throw new IllegalStateException("Queue is full");
        }
        heap[size] = item;
        size++;
        int i = size - 1;
        while (i > 0 && heap[i] > heap[parent(i)]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public int dequeue() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        }

        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;

        heapify(0);

        return root;
    }

    private void heapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int largest = i;

        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }

        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        return heap[0];
    }

    public int size() {
        return size;
    }

    public boolean contains(int item) {
        for (int i = 0; i < size; i++) {
            if (heap[i] == item) {
                return true;
            }
        }
        return false;
    }

    public void reverse() {
        int[] temp = new int[size];
        for (int i = 0; i < size; i++) {
            temp[i] = heap[size - i - 1];
        }
        heap = temp;
    }

    public int center() {
        int middle = size / 2;
        if (size % 2 == 0) {
            // if there are an even number of elements, return the average of the two middle elements
            return (heap[middle - 1] + heap[middle]) / 2;
        } else {
            // if there are an odd number of elements, return the middle element
            return heap[middle];
        }
    }

    private class PriorityQueueIterator implements Iterator<Integer> {

        private int position;

        public PriorityQueueIterator() {
            this.position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            int item = heap[position];
            position++;

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // Implement the Iterable interface by returning a new iterator
    public Iterator<Integer> iterator() {
        return new PriorityQueueIterator();
    }


    public void printQueue() {
        System.out.print("Queue contents: ");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue(10);
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek");
            System.out.println("4. Contains");
            System.out.println("5: Size: ");
            System.out.println("6. Reverse");
            System.out.println("7. Center");
            System.out.println("8. Display Queue");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter item: ");
                    int item = sc.nextInt();
                    queue.enqueue(item);
                    break;
                case 2:
                    queue.dequeue();
                    break;
                case 3:
                    System.out.println(queue.peek());
                    break;
                case 4:
                    System.out.println("Enter value to be checked: ");
                    int val = sc.nextInt();
                    System.out.println(queue.contains(val));
                    break;
                case 5:
                      System.out.println("Queue size: " + queue.size());
                      break;
                case 6:
                    System.out.print("Reversed Queue: ");
                    queue.reverse();
                    queue.printQueue();
                    break;
                case 7:
                    System.out.println("Center of Queue: ");
                    System.out.println(queue.center());
                    break;
                case 8:
                     queue.printQueue();
                     break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

    }
    
}
