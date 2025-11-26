package implementations;

/**
 * node used for linked list
 * @param <E> type of element for node
 */
public class MyDLLNode<E>
{

	private E element;
	private MyDLLNode<E> next;
	private MyDLLNode<E> prev;
	
	/**
	 * constructor
	 * @param element
	 */
	public MyDLLNode(E element){
		this.element = element;
		this.setNext(null);
		this.setPrev(null);
	}
	
	/**
	 * gets element of node
	 * @return element of the node
	 */
	public E getElement() {
		return element;
	}
	
	public void setElement(E element) {
		this.element = element;
	}

	/**
	 * gets previous node
	 * @return previous node
	 */
	public MyDLLNode<E> getPrev() {
		return prev;
	}

	/**
	 * sets previous node
	 * @param prev node to set as the previous
	 */
	public void setPrev(MyDLLNode<E> prev) {
		this.prev = prev;
	}

	/**
	 * gets the next node
	 * @return next node
	 */
	public MyDLLNode<E> getNext() {
		return next;
	}

	/**
	 * sets the next node
	 * @param next node to set as the next
	 */
	public void setNext(MyDLLNode<E> next) {
		this.next = next;
	}

}
