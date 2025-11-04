package utilities;

/**
 * @author Dat / Airzy / Eric
 * @version 1.0
 *
 * <p>
 *     The <code>StackADT</code> class is designed to be used as a basis for the stack structure that will be developed and implemented for CPRG304-Assignment2 Part-2.
 * </p>
 *
 * @param <E> The object that is held in the stack.
 */

public interface StackADT<E> {

    /**
     * Method to pop(remove) the top(last) value of the stack.
     *
     * Precondition: Have at least one value in the stack.
     *
     * Postcondition: The last value of the stack is removed from the stack.
     */
    public E pop();

    /**
     * Method to push(add) a value to the top of the stack.
     *
     * Precondition: An existing stack must be present to be pushed to.
     *
     * Postcondition: The stack gains an additional value at the end of the stack.
     *
     * @param obj: the obj is added to the end of the stack
     */
    public void push(E obj);

    /**
     * Method to get the value at the top of the stack without altering the stack.
     *
     * Precondition: An existing stack must be present to be peeked at.
     *
     * Postcondition: The value at the top of the stack is determined.
     */
    public E peek();

    /**
     * Method to determine the size (how many values) of the stack.
     *
     * Precondition: None
     *
     * Postcondition: The size and the number of values inside the stack is determined.
     */
    public int size();

    /**
     * Method to compare two stacks and determine if they have the same exact values in the same order.
     *
     * Precondition: Two stacks are available to be compared.
     *
     * Postcondition: The result if the stacks are the same or not.
     *
     * @param  otherStack: The other stack to be compared to.
     * @return True if the stacks are the same. False if the stacks are different.
     */
    public boolean equals(StackADT<E> otherStack);

    /**
     * Method to convert the stack into an array list.
     *
     * Precondition: An existing stack must be present.
     *
     * Postcondition: An array list of all the contents from the stack is created.
     *
     * @return the array list that has the same components as the stack it was based on.
     */
    public E[] toArray();

    /**
     * Method to search where the value is in the stack.
     *
     * Precondition: A stack must already be present.
     *
     * Postcondition: The position of the value is stated.
     *
     * @param obj: the specific value to be searched for within the stack.
     *
     * @return the int position of where it is located within the stack.
     */
    public int search(E obj);

    /**
     * Method to determine if a specific value exists within the stack.
     *
     * Precondition: A stack must already be present.
     *
     * Postcondition: Determine if a value exists or not.
     *
     * @param obj: the specific value to searched for to determine whether or not it exists within the stack.
     * @return True if the value exists within the stack. False if it does not.
     */
    boolean contains(E obj);

    /**
     * Method to check if the stack is empty or not.
     *
     * Precondition: None
     *
     * Postcondition: Determine if the stack is empty or not.
     *
     * @return True if the stack is empty. False if not.
     */
    public boolean isEmpty();

    /**
     * Method to clear the entire stack of all its contents.
     *
     * Precondition: None
     *
     * Postcondition: The stack is now empty without any values within the stack.
     */
    public void clear();
}
