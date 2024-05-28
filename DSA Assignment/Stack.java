import java.util.*;

public class Stack<T> implements Iterable<T> {
static  Scanner sc = new Scanner(System.in);
    private Node<T> top; // top node of the stack
    private int size; // size of the stack

    // Node class for a linked list implementation of the stack
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    // Push operation: add an element to the top of the stack
    public void push(T data) {
        Node<T> newNode = new Node<T>(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Pop operation: remove and return the element at the top of the stack
    public T pop() {
        if (top == null) {
            throw new EmptyStackException();
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    // Peek operation: return the element at the top of the stack without removing it
    public T peek() {
        if (top == null) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    // Contains operation: check if the stack contains a given element
    public boolean contains(T data) {
        Node<T> current = top;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Size operation: return the size of the stack
    public int size() {
        return size;
    }

    // Center operation: return the element in the center of the stack
    public T center() {
        if (top == null) {
            throw new EmptyStackException();
        }
        Node<T> slow = top;
        Node<T> fast = top;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }

    // Sort operation: sort the elements in the stack in ascending order
   

    @SuppressWarnings("unchecked")
	public void sort() {
        Stack<T> tempStack = new Stack<T>();
        while (!this.isEmpty()) {
            T temp = this.pop();
            while (!tempStack.isEmpty() && ((Comparable<String>) tempStack.peek()).compareTo((String) temp) > 0) {
                this.push(tempStack.pop());
            }
            tempStack.push(temp);
        }
        while (!tempStack.isEmpty()) {
            this.push(tempStack.pop());
        }
    }

    private boolean isEmpty() {
		return false;
	}

	// Reverse operation: reverse the order of the elements in the stack
    public void reverse() {
        Node<T> prev = null;
        Node<T> current = top;
        Node<T> next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        top = prev;
    }

    // Iterator operation: return an iterator for the stack
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = top;

            public boolean hasNext() {
                return current != null;
            }

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

    // Traverse/Print operation: print the elements of the stack in order
    public void traverse() {
        Node<T> current = top;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        boolean exit = false;
        while(!exit){
            int choice;
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Contains");
            System.out.println("5. Size");
            System.out.println("6. Center");
            System.out.println("7. Sort");
            System.out.println("8. Reverse");
            System.out.println("9. Traverse");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Enter data: ");
                    int data = sc.nextInt();
                    stack.push(data);
                    break;
                case 2:
                    System.out.print("POP Operation");
                    System.out.print(stack.pop());
                    break;
                case 3:
                    System.out.print("Peeked Element: ");
                    System.out.print(stack.peek());
                    break;
                case 4:
                    System.out.println("Enter Value to search in stack: ");
                    int val = sc.nextInt();
                    System.out.println(stack.contains(val));
                    break;
                case 5:
                    System.out.println("Size of Stack: "+stack.size());
                    break;
                case 6:
                    System.out.println("Center of Stack: "+stack.center());
                    break;
                case 7:
                    System.out.println("Sorted Stack: ");
                    stack.sort();
                    break;
                case 8:
                    System.out.println("Reversed Stack: ");
                    stack.reverse();
                    break;
                case 9:
                    System.out.println("Final Stack");
                    stack.traverse();
                    break;
                case 10:
                    exit = true;
                    break;
                default:
                System.out.println("Invalid choice");
            }
        }
    }
}