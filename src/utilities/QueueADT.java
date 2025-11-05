package utilities;
import exceptions.EmptyQueueException;
import implementations.Iterator;

/**
 * @author Dat, Airzy, Eric
 * @version 1.1
 *
 * <p>
 *     The <code>QueueADT</code> class is designed to be used as a basis for the queue structure that will be developed and implemented for CPRG304-Assignment2 Part-2.
 * </p>
 *
 * @param <E> object type held in the queue.
 */

public interface QueueADT<E> {
	/**
     * enqueue (add) an element to the tail (end) of the queue
     *
     * Precondition: queue instance must exist
     * Postcondition: element is added to the tail; size increases by one
     *
     * @param obj the element to add
     * @throws NullPointerException if {@code obj} is null
     */
    public void enqueue(E obj) throws NullPointerException;

    /**
     * dequeue (remove) and return the element at the head (front) of the queue
     *
     * Precondition: queue needs at least one element
     * Postcondition: head element is removed; size decreases by one
     *
     * @return the element removed from the head of the queue
     * @throws EmptyQueueException if the queue is empty
     */
    public E dequeue() throws EmptyQueueException, NullPointerException;

    /**
     * remove all elements from the queue
     *
     * Precondition: queue instance exists
     * Postcondition: queue becomes empty and size is zero
     */
    public void dequeueAll();

    /**
     * peek (inspect) the element at the head of the queue without removing it
     *
     * Precondition: queue contains at least one element
     * Postcondition: head element is returned; queue unchanged
     *
     * @return the element at the head of the queue
     * @throws EmptyQueueException if the queue is empty
     */
    public E peek() throws EmptyQueueException;

    /**
     * return the number of elements in the queue
     *
     * Postcondition: The number of elements is returned
     *
     * @return the size of the queue.
     */
    public int size();

    /**
     * is this queue to another queue to determine if they contain the same elements in the same order\
     * 
     * Precondition: two queues are available to be compared
     * Postcondition: boolean result indicating equality is returned
     *
     * @param otherQueue the other queue to compare to.
     * @return true if queues contain identical elements in the same order; false otherwise
     */
    public boolean equals(QueueADT<E> otherQueue);

    /**
     * convert the queue contents to an Object array from head to tai.
     *
     * Precondition: queue exists
     * Postcondition: An Object[] containing queue contents (head->tail) is returned
     *
     * @return an Object[] of all queue elements (head first).
     */
    public Object[] toArray();

    /**
     * convert the queue contents to an array of type E
     *
     * Precondition: array parameter is non-null.
     * Postcondition: An array containing queue contents (head->tail) is returned
     *
     * @param arr the array into which the elements of the queue are to be stored
     * @return an array containing the elements of the queue
     * @throws NullPointerException if {@code arr} is null.
     */
    public E[] toArray(E[] arr) throws NullPointerException;

    /**
     * Search for the given element and return its 1-based position in the queue
     * (head is position 1) Return -1 if not found.
     *
     * Postcondition: Index (1-based) of the first matching element is returned, or -1
     *
     * @param obj the element to search for
     * @return the 1-based index of the element if found; -1 otherwise
     */
    public int search(E obj);

    /**
     * whether the queue contains the specified element
     *
     * Postcondition: returns true if element exists in queue
     *
     * @param obj the element to test for membership
     * @return true if the element exists in the queue; false otherwise
     * @throws NullPointerException if {@code obj} is null
     */
    public boolean contains(E obj);

    /**
     * whether the queue is empty.
     *
     * Postcondition: returns true if queue has no elements
     *
     * @return true if queue is empty
     */
    public boolean isEmpty();

    /**
     * whether the queue is full
     *
     * Postcondition: returns true if queue is at capacity
     *
     * @return true if queue is full
     */
    public boolean isFull();

    /**
     * returns an iterator to iterate over the items of the queue from head to tail
     *
     * Postcondition: an iterator positioned before the head element is returned.
     *
     * @return an iterator over the queue elements (head -> tail).
     */
    public Iterator<E> iterator();
}
