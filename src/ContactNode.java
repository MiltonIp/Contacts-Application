/**
 * @class_name ContactNode
 * @version Final
 * @author Milton Ip
 * @date 12/10/16
 * Class of a node of a doubly linked list
 */

public class ContactNode<E> {
	//Class variables
	private E item;

	private ContactNode<E> next;
	private ContactNode<E> previous;

	//Constructor
	public ContactNode(E e, ContactNode<E> n, ContactNode<E> p) {
		this.item = e;
		this.next = n;
		this.previous = p;
	}

	public ContactNode(E e) {
		this.item = e;
	}

	public E getItem() {
		return item;
	}

	public void setItem(E item) {
		this.item = item;
	}

	public ContactNode<E> getNext() {
		return next;
	}

	public void setNext(ContactNode<E> next) {
		this.next = next;
	}

	public ContactNode<E> getPrevious() {
		return previous;
	}

	public void setPrevious(ContactNode<E> previous) {
		this.previous = previous;
	}

}
