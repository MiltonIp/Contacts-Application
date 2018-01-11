/**
 * @class_name ContactsList
 * @version Final
 * @author Milton Ip
 * @date 12/10/16
 * Class of doubly linked lists
 */

public class ContactsList<T> {
	//Class variables
	private ContactNode<T> head;
	private ContactNode<T> tail;

	//Constructor
	public ContactsList() {
		this.head = null;
		this.setTail(null);
	}
	
	public ContactNode<T> getHead() {
		return this.head;
	}

	public ContactNode<T> getTail() {
		return tail;
	}

	public void setTail(ContactNode<T> tail) {
		this.tail = tail;
	}

	/**
	 * add
	 * This method adds a node to the doubly linked list
	 * @param T - The node to be added
	 * @return boolean - True if the node has been added
	 */
	public boolean add(T item) {
		ContactNode<T> temp = head;

		//If doubly linked list is empty
		if (head == null) {
			//Adds node
			head = new ContactNode<T>(item, null, null);
			//Updates tail
			setTail(head);
			return true;
		}

		//Traverses to last node in doubly linked list
		while (temp.getNext() != null) {
			temp = temp.getNext();
		}

		//Adds node
		temp.setNext(new ContactNode<T>(item, null, temp));

		//Updates tail
		setTail(temp.getNext());

		return true;
	}

	/**
	 * isEmpty
	 * This checks if list is empty or not
	 * @param null
	 * @return boolean - True if list is empty, false if not
	 */
	public boolean isEmpty() {
		ContactNode<T> temp = head;

		//List is empty
		if (temp != null) {
			return false;
		}
		return true;
	}

	/**
	 * indexOf
	 * This method finds the first occurrence of an item in the list and returns the index
	 * @param T - The item to be searched for
	 * @return int - The index of the item
	 */
	public int indexOf(T item) {
		//Index counter
		int i = 0;
		ContactNode<T> temp = head;

		//Searches list
		while (temp != null) {
			if (temp.getItem().equals(item)) {	//Trying to find matching items
				return i;
			}
			i++;
			temp = temp.getNext();
		}
		return -1;
	}

	/**
	 * contains
	 * This method checks if the list contains an item or not
	 * @param T - The item to be searched for
	 * @return boolean - True if the item is in the list, false if not
	 */
	public boolean contains(T item) {
		ContactNode<T> temp = head;

		//Traverses list
		while (temp != null) {
			if (temp.getItem().equals(item)) {	//Trying to find the item
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}

	/**
	 * get
	 * This method gets the item in a list at the requested index
	 * @param int - The index of the item to be returned
	 * @return T - The item found at index
	 */
	public T get(int index) {
		ContactNode<T> temp = head;
		T itemReturn = null;
		
		//List is empty or invalid index
		if (temp == null || index < 0) {
			return itemReturn;
		}

		//Traverses through list
		for (int i = 0; temp != null; i++) {
			if (i == index) {	//Trying to find item at requested index
				itemReturn = temp.getItem();
				break;
			}
			temp = temp.getNext();
		}
		return itemReturn;
	}

	/**
	 * remove
	 * This method removes a node to the doubly linked list
	 * @param int - The index of the item to be removed
	 * @return T - The item removed
	 */
	public T remove(int index) {
		ContactNode<T> prev = head;	//Trailing pointer
		ContactNode<T> curr = head;	//Current pointer
		T item = null;
		int counter = 0;

		//Removes head of list
		if (index == 0) {
			item = head.getItem();
			head = head.getNext();
			return item;
		}

		curr = head.getNext();

		//Traverses through list
		for (int i = 0; curr.getNext() != null; i++) {
			if (i == (index - 1)) {	//Trying to find item to be removed
				item = curr.getItem();
				//Removes item 
				prev.setNext(curr.getNext());
				curr.getNext().setPrevious(prev);
				return item;
			} else {	//Item not found yet
				prev = prev.getNext();
				curr = curr.getNext();
			}
			counter++;
		}

		//Removes the tail of the list
		if (curr.getNext() == null && counter == (index - 1)) {
			item = prev.getNext().getItem();
			setTail(prev);
			prev.setNext(null);
		}

		return item;
	}

	/**
	 * size
	 * This method returns the size of the list
	 * @param null
	 * @return int - The size of the list
	 */
	public int size() {
		ContactNode<T> temp = head;
		//Counts number of items in list
		int counter = 0;

		//Traverses list
		while (temp != null) {
			temp = temp.getNext();
			counter++;
		}
		return counter;
	}

}
