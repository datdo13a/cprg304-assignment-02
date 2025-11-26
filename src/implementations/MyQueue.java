package implementations;

import exceptions.EmptyQueueException;
import utilities.QueueADT;
import implementations.Iterator;

/**
 * Implementing a queue that is based on MyDLL.java
 * @param <E> the type of element within this Queue
 */

public class MyQueue<E> implements QueueADT<E> {
    private MyDLL<E> elementList;

    public MyQueue() {
        this.elementList = new MyDLL<>();
    }

    /**
     * Enqueue will place the added item at the last position in the queue. This
     * method will not allow <code>null</code> values to be added to the Queue.
     *
     * @param toAdd the item to be added to the Queue.
     * @throws NullPointerException raised when a <code>null</code> object is placed
     *                              in the Queue.
     */
    public void enqueue(E obj) {
        if (obj == null) {
            throw new NullPointerException("Must not be a null element to enqueue");
        }
        elementList.add(obj); // add to the end
    }

    /**
     * Dequeue will remove the first item that was placed in the Queue.
     *
     * @return the first item in the Queue.
     * @throws EmptyQueueException raised when the queue's length is zero (0).
     */
    public E dequeue() throws EmptyQueueException {
        if (elementList.isEmpty()) {
            throw new EmptyQueueException();
        }
        return elementList.removeFirst(); // remove from front
    }

    /**
     * dequeueAll removes all items in the queue.
     */
    public void dequeueAll() {
        elementList.clear();
    }

    /**
     * Peek provides a reference to the first item in the queue without removing
     * from the queue.
     *
     * @return the first item in the queue.
     * @throws EmptyQueueException raised when the queue's length is zero (0).
     */
    public E peek() throws EmptyQueueException {
        if (elementList.isEmpty()) {
            throw new EmptyQueueException();
        }
        return elementList.get(0);
    }

    /**
     * Returns the length of the current queue as an integer value.
     *
     * @return the current size to the queue as an integer.
     */
    public int size() {
        return elementList.size();
    }

    /**
     * Used to compare two Queue ADT's. To be equal two queues must contain equal
     * items appearing in the same order.
     *
     * @param that the Queue ADT to be compared to this queue.
     * @return <code>true</code> if the queues are equal.
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
     * Returns an array containing all of the elements in this list in proper
     * sequence. Obeys the general contract of the Collection.toArray method.
     *
     * @return an array containing all of the elements in this list in proper
     *         sequence.
     */
    public Object[] toArray() {
        return elementList.toArray();
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence; the runtime type of the returned array is that of the specified
     * array. Obeys the general contract of the Collection.toArray(Object[]) method.
     *
     * @param arr the array into which the elements of this queue are to be
     *               stored, if it is big enough; otherwise, a new array of the same
     *               runtime type is allocated for this purpose.
     * @return an array containing the elements of this queue.
     * @throws NullPointerException if the specified array is null.
     */
    public E[] toArray(E[] arr) {
        return elementList.toArray(arr);
    }

    /**
     * Returns the 1-based position where an object is on this queue. If the
     * object o occurs as an item in this queue, this method returns the
     * distance from the front of the queue of the occurrence nearest the front of
     * the queue; the first item on the stack is considered to be at distance
     * 1. The equals method is used to compare o to the items in this queue.
     *
     * @param toFind
     *            the desired object.
     * @return the 1-based position from the top of the queue where the object
     *         is located; the return value -1 indicates that the object is not
     *         on the queue.
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
     * Returns true if this list contains the specified element. More formally,
     * returns true if and only if this list contains at least one element e
     * such that (o==null ? e==null : o.equals(e)).
     *
     * @param obj
     *            element whose presence in this list is to be tested.
     * @return true if this list contains the specified element.
     * @throws NullPointerException
     *             if the specified element is null and this list does not
     *             support null elements.
     */
    public boolean contains(E obj) {
        if (obj == null) {
            throw new NullPointerException("Must not be a null element");
        }
        return elementList.contains(obj);
    }

    /**
     * Returns <code>true</code> when the queue contains no items.
     *
     * @return <code>true</code> when queue length is zero (0).
     */
    public boolean isEmpty() {
        return elementList.isEmpty();
    }

    @Override
    /**
     * Returns true if the number of items in the queue equals the
     * length. This operation is only implement when a fixed length queue is
     * required.
     *
     * @return <code>true</code> if queue is at capacity.
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
