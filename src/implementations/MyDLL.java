package implementations;

import java.util.NoSuchElementException;

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
	
	public boolean add(int index, E item) {
	    if (index < 0 || index > size) {
	        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	    }
	    
	    if (index == 0) {
	        addFirst(item); // insert at head
	    } else if (index == size) {
	        addLast(item);  // insert at tail
	    } else {
	        // insert somewhere in the middle
	        MyDLLNode<E> current = head;
	        for (int i = 0; i < index; i++) {
	            current = current.getNext();
	        }
	        MyDLLNode<E> newNode = new MyDLLNode<>(item);
	        MyDLLNode<E> prevNode = current.getPrev();
	        
	        prevNode.setNext(newNode);
	        newNode.setPrev(prevNode);
	        
	        newNode.setNext(current);
	        current.setPrev(newNode);
	        
	        size++;
	    }
	    
	    return true; // always returns true because the list is modified
	}
	
	public boolean add(E item) {
	    if (item == null) {
	        throw new NullPointerException("Null elements not allowed");
	    }
	    addLast(item);
	    return true;
	}
	
	/**
	 * adds all elements from another list to the end of this list
	 * @param otherList the list whose elements to add
	 * @return if the list was modified
	 * @throws NullPointerException if otherList or any of its elements are null
	 */
	public boolean addAll(MyArrayList<E> otherList) {
	    if (otherList == null) {
	        throw new NullPointerException("Input list cannot be null");
	    }

	    boolean modified = false;

	    for (int i = 0; i < otherList.size(); i++) {
	        E item = otherList.get(i);
	        if (item == null) {
	            throw new NullPointerException("Null elements not allowed");
	        }
	        add(item);
	        modified = true;
	    }

	    return modified;
	}

	
	/**
	 * adds all elements from another doubly-linked list to the end of this list
	 * @param list the MyDLL whose elements to add
	 * @return if the list was modified
	 * @throws NullPointerException if the input list is null or contains null elements
	 */
	public boolean addAll(MyDLL<E> list) {
	    if (list == null) {
	        throw new NullPointerException("Input list cannot be null");
	    }

	    boolean modified = false;

	    MyDLLNode<E> current = list.head;
	    while (current != null) {
	        E item = current.getElement();
	        if (item == null) {
	            throw new NullPointerException("Null elements not allowed");
	        }
	        add(item);
	        modified = true;
	        current = current.getNext();
	    }

	    return modified;
	}

	
	/**
	 * returns element at the specified position in the list.
	 * @param index position of the element to retrieve (0-based)
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException if index < 0 or >= size
	 */
	public E get(int index) {
	    if (index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	    }

	    MyDLLNode<E> current = head;
	    for (int i = 0; i < index; i++) {
	        current = current.getNext();
	    }

	    return current.getElement();
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
	 * remove the element at index from the list.
	 * @param index of the element to remove
	 * @return the removed element, or null if the list is empty
	 * @throws IndexOutOfBoundsException if index < 0 or >= size
	 */
	public E remove(int index) {
	    if (index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	    }

	    if (index == 0) {
	        return removeFirst(); // remove head
	    } else if (index == size - 1) {
	        return removeLast(); // remove tail
	    } else {
	        // remove in middle
	        MyDLLNode<E> current = head;
	        for (int i = 0; i < index; i++) {
	            current = current.getNext();
	        }

	        E returnValue = current.getElement();
	        MyDLLNode<E> prevNode = current.getPrev();
	        MyDLLNode<E> nextNode = current.getNext();

	        prevNode.setNext(nextNode);
	        nextNode.setPrev(prevNode);

	        // disconnect the removed node
	        current.setNext(null);
	        current.setPrev(null);

	        size--;
	        return returnValue;
	    }
	}

	/**
	 * replace the element at the given index.
	 * @param index position to replace (0-based)
	 * @param item new element
	 * @return old element
	 * @throws NullPointerException if item is null
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	public E set(int index, E item) {
	    if (item == null) {
	        throw new NullPointerException("Null elements not allowed");
	    }
	    if (index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	    }

	    MyDLLNode<E> current = head;
	    for (int i = 0; i < index; i++) {
	        current = current.getNext();
	    }

	    E oldValue = current.getElement();
	    current.setElement(item);
	    return oldValue;
	}

	
	/**
	 * Returns an array containing all elements in the list in proper sequence.
	 * @return an array containing all elements
	 */
	@SuppressWarnings("unchecked")
	public E[] toArray() {
	    E[] array = (E[]) new Object[size]; // generic array workaround
	    MyDLLNode<E> current = head;
	    int i = 0;

	    while (current != null) {
	        array[i++] = current.getElement();
	        current = current.getNext();
	    }

	    return array;
	}

	/**
	 * Returns an array with all elements. Uses the given array if large enough; 
	 * otherwise, allocates a new one of the same type.
	 * @param holder array to fill
	 * @return array containing all elements
	 * @throws NullPointerException if holder is null
	 */
	@SuppressWarnings("unchecked")
	public E[] toArray(E[] holder) {
	    if (holder == null) {
	        throw new NullPointerException("Holder array cannot be null");
	    }

	    if (holder.length < size) {
	    	holder = (E[]) java.util.Arrays.copyOf(holder, size);
	    }

	    MyDLLNode<E> current = head;
	    int i = 0;
	    while (current != null) {
	        holder[i++] = current.getElement();
	        current = current.getNext();
	    }

	    if (holder.length > size) {
	        holder[size] = null; // set next element to null as per Java convention
	    }

	    return holder;
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

	/**
	 * removes all elements from the list, making it empty.
	 */
	public void clear() {
	    MyDLLNode<E> current = head;
	    while (current != null) {
	        MyDLLNode<E> next = current.getNext();
	        current.setPrev(null);
	        current.setNext(null);
	        current = next;
	    }
	    
	    head = null;
	    tail = null;
	    size = 0;
	}

	
	/**
	 * Checks if the list contains the given element.
	 * @param item element to search for
	 * @return true if the element exists in the list, false otherwise
	 * @throws NullPointerException if item is null
	 */
	public boolean contains(E item) {
	    if (item == null) {
	        throw new NullPointerException("Null elements not allowed");
	    }

	    MyDLLNode<E> current = head;
	    while (current != null) {
	        if (current.getElement().equals(item)) {
	            return true; // element found
	        }
	        current = current.getNext();
	    }

	    return false; // element not found
	}

	public Iterator<E> iterator() {
	    return new DLLIterator();
	}

	private class DLLIterator extends Iterator<E> {
	    private MyDLLNode<E> current;

	    public DLLIterator() {
	        super(null);
	        this.current = head;
	    }

	    @Override
	    public boolean hasNext() {
	        return current != null;
	    }

	    @Override
	    public E next() {
	        if (!hasNext()) {
	            throw new NoSuchElementException();
	        }
	        E data = current.getElement();
	        current = current.getNext();
	        return data;
	    }
	}

}
