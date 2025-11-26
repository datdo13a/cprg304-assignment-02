package implementations;

import exceptions.EmptyQueueException;
import utilities.QueueADT;
import implementations.Iterator;

public class MyQueue<E> implements QueueADT<E>{
	private MyArrayList<E> elementArr;
	
	/**
	 * constructor
	 */
	public MyQueue() {
		this.elementArr = new MyArrayList<E>();
	}

	/**
	 * adds an object to the list
	 * @param object to add
	 */
	public void enqueue(E obj) throws NullPointerException {
		if(obj == null) {
			throw new NullPointerException("Must not be a null element to enqueue into the queue");
		}
		elementArr.add(obj);	
	}

	/**
	 * removes the head object from the list
	 * @return object that was removed
	 */
	public E dequeue() throws EmptyQueueException {
		if(elementArr.isEmpty()) {
			throw new EmptyQueueException();// TODO emty queue exception needs to be finished
		}
		E dequeuedItem = (E) elementArr.remove(0);
		return dequeuedItem;
	}

	/**
	 * clears the queue entirely
	 */
	public void dequeueAll() {
		elementArr.clear();
	}

	/**
	 * returns the head object without removing it
	 */
	public E peek() throws EmptyQueueException {
		if(elementArr.isEmpty()) {
			throw new EmptyQueueException();// TODO emty queue exception needs to be finished
		}
		return elementArr.get(0);
	}

	/**
	 * returns the queues size
	 */
	public int size() {
		return elementArr.size();
	}

	/**
	 * checks to see if two queues are the same
	 */
	public boolean equals(QueueADT<E> otherQueue) {
		if (this == otherQueue) return true;  // same ref
	    if (otherQueue == null) return false; // null check

	    QueueADT<?> other = (QueueADT<?>) otherQueue;

	    if (this.size() != other.size()) return false;

	    Object[] a = this.toArray();
	    Object[] b = other.toArray();

	    for (int i = 0; i < a.length; i++) {
	        if (a[i] == null) {
	            if (b[i] != null) return false;
	        } else {
	            if (!a[i].equals(b[i])) return false;
	        }
	    }
	    return true;
	}

	/**
	 * returns the queue as an array
	 */
	public Object[] toArray() {
		return elementArr.toArray();
	}

	/**
	 * creates an array within a certain parameter
	 * @param arr as the holder
	 * @return the array into which the elements of this queue are to be
     * stored, if it is big enough; otherwise, a new array of the same
     * runtime type is allocated for this purpose.
     * @throws NullPointerException
	 */
	public E[] toArray(E[] arr) throws NullPointerException {
		if(arr == null) {
			throw new NullPointerException("The holder element must not be null");
		}
		if (arr.length    < elementArr.size()) {
            // if the array size is too small
            Object[] newArray = new Object[elementArr.size()];
            for (int i = 0; i < elementArr.size(); i++) {
                newArray[i] = elementArr.get(i);
            }
            return (E[]) newArray;
        }
        else {
            // if the array is the same or bigger in size (if it is bigger, create null elements after)
            for (int i = 0; i < elementArr.size(); i++) {
                arr[i] = (E) elementArr.get(i);
            }
            if (arr.length > elementArr.size()) {
                arr[elementArr.size()] = null;
            }
            return arr;
        }
	}

	/**
	 * returns the position found where the element is equal to the input object
	 * @param obj the object you want to search the position for
	 * @return int position of the element
	 */
	public int search(E obj) {
		if(obj == null) {
			throw new NullPointerException("Must not be a null element");
		}
		for ( int i = elementArr.size() - 1; i >= 0; i--) {
            if (obj.equals(elementArr.get(i))) {
                return elementArr.size() - i;
            }
        }
		return -1;
	}

	/**
	 * returns if a specified object is found in the queue
	 * @param obj the object you want to search the position for
	 * @return
	 */
	public boolean contains(E obj) throws NullPointerException {
		if(obj == null) {
			throw new NullPointerException("Must not be a null element");
		}
		for ( int i = elementArr.size() - 1; i >= 0; i--) {
            if (obj.equals(elementArr.get(i))) {
                return true;
            }
        }
		return false;
	}

	public boolean isEmpty() {
		if(elementArr.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		return false;//TODO i dont know what to do with this
	}

	@Override
	public implementations.Iterator<E> iterator() {
		return new implementations.Iterator<E>(elementArr);
	}
}
