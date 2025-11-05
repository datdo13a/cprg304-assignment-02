package implementations;

/**
 * Author: Eric LaPierre
 * Date: Nov 5th 2025
 * MyDList is a
 * @param <E>
 */
public class MyDLL<E>
{

	private MyDLLNode<E> head;
	private MyDLLNode<E> tail;
	private int size;
	
	/**
	 * constructor for the DLL, defines the head and tail as null and the size as 0
	 */
	public MyDLL(){
		this.head = this.tail = null;
		this.size = 0;
	}
	
	/**
	 * simple function to get the head of the DLL
	 * @return head of DLL
	 */
	public MyDLLNode<E> getHead(){
		return head;
	}
	
	/**
	 * adds an element to the top of the list by connecting 
	 * it to the previous head if it exists, if not it will 
	 * add the only element to the empty list 
	 * @param item
	 */
	public void addFirst(E item) {
		MyDLLNode<E> newNode = new MyDLLNode(item);
		
		if(isEmpty()) {
			head = newNode;
			tail = newNode;
		}else {
			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
		}
		size++;
	}
	
	/**
	 * adds an element to the bottom of the list by connecting 
	 * it to the previous tail if it exists, if not it will add 
	 * the only element to the list
	 * @param item
	 */
	public void addLast(E item) {
		MyDLLNode<E> newNode = new MyDLLNode(item);
		
		if(isEmpty()) {
			head = newNode;
			tail = newNode;
		}else {
			newNode.setPrev(tail);
			tail.setNext(newNode);
			tail = newNode;
		}
		size++;	
	}
	
	/**
	 * removes the head from the list by disconnecting it from the 
	 * value before it and setting that value to the new head, if there 
	 * is only one element in the list it will remove it entirely and if 
	 * there is no list nothing happens
	 * @return previous head of the list
	 */
	public E removeFirst() {
		if(isEmpty()) {
			return null;
		}else {
			E returnValue = head.getElement();
			// if there's only one element, clear both head and tail
			if(head == tail) {
				head = null;
				tail = null;
			} else {
				head = head.getNext();
				head.setPrev(null);
			}
			size--;
			return returnValue;
		}
	}
	
	/**
	 * removes the tail from the list by disconnecting it from the 
	 * value after it and setting that value to the new tail, if there 
	 * is only one element in the list it will remove it entirely and if 
	 * there is no list nothing happens
	 * @return the previous tail of the list
	 */
	public E removeLast() {
		if(isEmpty()) {
			return null;
		}else {
			E returnValue = tail.getElement();
			// if there's only one element, clear both head and tail
			if(head == tail) {
				head = null;
				tail = null;
			} else {
				tail = tail.getPrev();
				tail.setNext(null);
			}
			size--;
			return returnValue;
		}
	}
	
	/**
	 * simple method to return the size of the list
	 * @return size of the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * checks if the list is empty
	 * @return t/f if the list is empty
	 */
	public boolean isEmpty() {
		if(head == null) {
			return true;
		}
		return false;
	}

}
