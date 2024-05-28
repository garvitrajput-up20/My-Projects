import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Node{
	int data;
	Node next;
	public Node(int data) {
		this.data = data;
		this.next = null;
	}
}
public class LinkedList implements Iterator<Integer>{
	static Scanner sc = new Scanner(System.in);
	Node head;
	public LinkedList() {
		head = null;
	}

	//Inserting at beginning of List
	public void addFirst(int data) {
		Node node = new Node(data);
		if(head == null) {
			head = node;
			return;
		}
		node.next = head;
		head = node;
	}

	//Insert at End
	public void addNode(int data) {
		Node node = new Node(data);
		if (head == null) {
			head = node;
			return;
		} else {
			Node currentNode = head;
			while (currentNode.next != null) {
				currentNode = currentNode.next;
			}
			currentNode.next = node;
		}
	}
	//Insert at any Position of List
	public void insertAtPosition(int pos, int data) {
		Node node = new Node(data);
		if(head == null) {
			head = node;
			return;
		}

		if(pos == 0) {
			node.next = head;
			head = node;
			return;
		}
		Node currentNode = head;
		Node lastNode = null;
		int count = 0; 
		while (currentNode != null && count < pos) {
			lastNode = currentNode;
			currentNode = currentNode.next;
			count++;
		}
		lastNode.next = node;
		node.next = currentNode;
	}
	//Delete Node
	public void deleteItem(int data) {
		if(head == null) {
			System.out.println("List is empty");
			return;
		}
		if(head.data == data) {
			head = head.next;
			return;
		}
		Node curr_node = head;
		Node prev_node = null;
		while (curr_node != null && curr_node.data != data) {
			prev_node = curr_node;
			curr_node = curr_node.next;
		}
		if (curr_node != null) {
			prev_node.next = curr_node.next;
		}
	}

	//Delete from specific position
	public void deleteAtPosition(int pos) {
		if (head == null) {
			return;
		}
		if (pos == 0) {
			head = head.next;
			return;
		}
		Node curr_node = head;
		Node prev_node = null;
		int count = 0;
		while (curr_node != null && count < pos) {
			prev_node = curr_node;
			curr_node = curr_node.next;
			count++;
		}
		if (curr_node != null) {
			prev_node.next = curr_node.next;
		}
	}
	
	//Finding Center of List
	public Node center() {
		if (head == null) {
			return null;
		}

		Node slow = head;
		Node fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	
	//Sorting List using Insertion sort
	public void insertionSort() {
		head = insertionSort(head);
	}
	private Node insertionSort(Node head) {
		if (head == null || head.next == null) { 
			return head;
		}
		Node dummy = new Node(0); 
		dummy.next = head;
		Node prev = dummy;
		Node current = head;
		while (current != null) {
			if (current.next != null && current.data > current.next.data) {
				while (prev.next != null && prev.next.data < current.next.data) {
					prev = prev.next;
				}
				Node temp = prev.next;
				prev.next = current.next;
				current.next = current.next.next;
				prev.next.next = temp;
				prev = dummy;
			} else {
				current = current.next;
			}
		}
		return dummy.next;
	}
	
	// Reverse Linked List
	public void reverseList() {
		if(head == null) {
			return;
		}
		Node prev = null;
		Node current = head;
		Node next = null;

		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		head = prev;
	}

    // Find Size
	public int size() {
		int count = 0;
		Node current = head;

		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
	}

    // Printing List
	public void printList() {
		Node current = this.head;
		while (current != null) {
			System.out.print(current.data + "->");
			current = current.next;
		}
		System.out.println();
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Iterator<Integer> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Integer> {
        Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int data = current.data;
            current = current.next;
            return data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 LinkedList list = new LinkedList();
	        System.out.println("How many items you want to insert: ");
	        int x = sc.nextInt();
	        System.out.println("Enter "+x+" items:");
	        for (int i = 0; i < x; i++) {
	            int data = sc.nextInt();
	            list.addNode(data);
	        }
	        System.out.println("Initial List");
	        list.printList();
	  
	        //Insert at position
	        System.out.println("Enter Position at which you want to insert:");
	        int pos = sc.nextInt();
	        System.out.println("Enter Item: ");
	        int data = sc.nextInt();
	        list.insertAtPosition(pos, data);
	        System.out.println("List Printing after inserting "+data+" at "+pos+":");
	        list.printList();

	        //Deleting an Item
	        System.out.println("Enter the item to be deleted: ");
	        data = sc.nextInt();
	        list.deleteItem(data);
	        System.out.println("List Printing After Deleting "+data+":");
	        list.printList();

	        //Deleting an Item at specific position
	        System.out.println("Enter the position to be deleted: ");
	        pos = sc.nextInt();
	        list.deleteAtPosition(pos);
	        System.out.println("List Printing After Deleting from "+pos+":");
	        list.printList();

	        //Printing Center of Linked List
	        System.out.print("Center of Linked List : ");
	        Node center = list.center();
	        if(center != null){
	            System.out.println(center.data);
	        }else{
	            System.out.println("List is empty");
	        }

	        //Print Sorted List
	        list.insertionSort();
	        System.out.print("Sorted list: ");
	        list.printList();
	        
	        System.out.println("List using iterator:");
	        Iterator<Integer> it = list.iterator();
	        while (it.hasNext()) {
	        	System.out.print(it.next() + " ");
	        }
	        System.out.println();
	        //Print Reversed List
	        list.reverseList();
	        System.out.print("Reversed List: ");
	        list.printList();
	        //Printing Size of List
	        System.out.println("Size of the list: " + list.size());
	}
}