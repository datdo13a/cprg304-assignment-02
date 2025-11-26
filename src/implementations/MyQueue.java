package implementations;

import exceptions.EmptyQueueException;
import utilities.QueueADT;
import implementations.Iterator;

public class MyQueue<E> implements QueueADT<E> {
    private MyDLL<E> elementList;

    public MyQueue() {
        this.elementList = new MyDLL<>();
    }

    /**
     * add to the end
     */
    public void enqueue(E obj) {
        if (obj == null) {
            throw new NullPointerException("Must not be a null element to enqueue");
        }
        elementList.add(obj); // add to the end
    }

    /**
     * removes the head object from the queue
     * @return E the removed element
     */
    public E dequeue() throws EmptyQueueException {
        if (elementList.isEmpty()) {
            throw new EmptyQueueException();
        }
        return elementList.removeFirst(); // remove from front
    }

    /**
     * clears the queue
     */
    public void dequeueAll() {
        elementList.clear();
    }

    /**
     * returns the head object without removing it
     * @return E head
     */
    public E peek() throws EmptyQueueException {
        if (elementList.isEmpty()) {
            throw new EmptyQueueException();
        }
        return elementList.get(0);
    }

    /**
     * returns the queue size
     * @return size of queue, #of stuff in list
     */
    public int size() {
        return elementList.size();
    }

    /**
     * checks if two queues are equal
     * @return if queues are equal
     */
    public boolean equals(QueueADT<E> otherQueue) {
        if (this == otherQueue) return true;
        if (otherQueue == null) return false;

        Object[] a = this.toArray();
        Object[] b = otherQueue.toArray();

        if (a.length != b.length) return false;

        for (int i = 0; i < a.length; i++) {
            if (a[i] == null) {
                if (b[i] != null) return false;
            } else if (!a[i].equals(b[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return elements in the queue as array
     */
    public Object[] toArray() {
        return elementList.toArray();
    }

    /**
     * @return the queue as an array using a provided holder
     */
    public E[] toArray(E[] arr) {
        return elementList.toArray(arr);
    }

    /**
     * @return the index of the element from the queue front, -1 if not found
     */
    public int search(E obj) {
        if (obj == null) {
            throw new NullPointerException("Must not be a null element");
        }
        for (int i = 0; i < elementList.size(); i++) {
            if (obj.equals(elementList.get(i))) {
                return i + 1; // 1-based index
            }
        }
        return -1;
    }

    /**
     * @return if a specified object is found in the queue
     */
    public boolean contains(E obj) {
        if (obj == null) {
            throw new NullPointerException("Must not be a null element");
        }
        return elementList.contains(obj);
    }

    /**
     * @return if queue is empty
     */
    public boolean isEmpty() {
        return elementList.isEmpty();
    }

    @Override
    /**
     * @return this is never full??
     */
    public boolean isFull() {
        return false;
    }

    @Override
	/**
	 * @return the iterator
	 */
    public Iterator<E> iterator() {
        return elementList.iterator(); // use DLL's iterator
    }
}
