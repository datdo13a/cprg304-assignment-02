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
     * Enqueue (add) an element to the tail (end) of the queue.
     *
     * Precondition: A queue instance must exist.
     * Postcondition: The element is added to the tail; size increases by one.
     *
     * @param obj the element to add
     * @throws NullPointerException if {@code obj} is null.
     */
    public void enqueue(E obj);

    /**
     * Dequeue (remove) and return the element at the head (front) of the queue.
     *
     * Precondition: The queue contains at least one element.
     * Postcondition: The head element is removed; size decreases by one.
     *
     * @return the element removed from the head of the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    public E dequeue() throws EmptyQueueException;

    /**
     * Remove all elements from the queue.
     *
     * Precondition: A queue instance exists.
     * Postcondition: The queue becomes empty and size is zero.
     */
    public void dequeueAll();

    /**
     * Peek (inspect) the element at the head of the queue without removing it.
     *
     * Precondition: The queue contains at least one element.
     * Postcondition: The head element is returned; queue unchanged.
     *
     * @return the element at the head of the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    public E peek() throws EmptyQueueException;

    /**
     * Return the number of elements in the queue.
     *
     * Precondition: None.
     * Postcondition: The number of elements is returned.
     *
     * @return the size of the queue.
     */
    public int size();

    /**
     * Compare this queue to another queue to determine if they contain the same
     * elements in the same order.
     *
     * Precondition: Two queues are available to be compared.
     * Postcondition: A boolean result indicating equality is returned.
     *
     * @param otherQueue the other queue to compare to.
     * @return true if queues contain identical elements in the same order; false otherwise.
     */
    public boolean equals(QueueADT<E> otherQueue);

    /**
     * Convert the queue contents to an Object array from head to tail.
     *
     * Precondition: Queue exists.
     * Postcondition: An Object[] containing queue contents (head->tail) is returned.
     *
     * @return an Object[] of all queue elements (head first).
     */
    public Object[] toArray();

    /**
     * Convert the queue contents to an array of type E. If the provided array
     * has sufficient length it is filled and returned; otherwise a new array
     * of the same runtime type is created and returned.
     *
     * Precondition: Queue exists and the array parameter is non-null.
     * Postcondition: An array containing queue contents (head->tail) is returned.
     *
     * @param arr the array into which the elements of the queue are to be stored
     * @return an array containing the elements of the queue
     * @throws NullPointerException if {@code arr} is null.
     */
    public E[] toArray(E[] arr);

    /**
     * Search for the given element and return its 1-based position in the queue
     * (head is position 1). Return -1 if not found.
     *
     * Precondition: Queue exists.
     * Postcondition: Index (1-based) of the first matching element is returned, or -1.
     *
     * @param obj the element to search for
     * @return the 1-based index of the element if found; -1 otherwise.
     */
    public int search(E obj);

    /**
     * Determine whether the queue contains the specified element.
     *
     * Precondition: Queue exists.
     * Postcondition: Returns true if element exists in queue; false otherwise.
     *
     * @param obj the element to test for membership
     * @return true if the element exists in the queue; false otherwise.
     * @throws NullPointerException if {@code obj} is null.
     */
    public boolean contains(E obj);

    /**
     * Determine whether the queue is empty.
     *
     * Precondition: None.
     * Postcondition: Returns true if queue has no elements; false otherwise.
     *
     * @return true if queue is empty; false otherwise.
     */
    public boolean isEmpty();

    /**
     * Determine whether the queue is full. For this assignment implementations
     * are expected to return false (unbounded) but this method is provided for
     * completeness/compatibility with tests.
     *
     * Precondition: None.
     * Postcondition: Returns true if queue is at capacity; false otherwise.
     *
     * @return true if queue is full; false otherwise.
     */
    public boolean isFull();

    /**
     * Return an iterator to iterate over the items of the queue from head to tail.
     *
     * Precondition: Queue exists.
     * Postcondition: An iterator positioned before the head element is returned.
     *
     * @return an iterator over the queue elements (head -> tail).
     */
    public Iterator<E> iterator();
}
