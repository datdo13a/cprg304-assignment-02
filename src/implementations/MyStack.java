package implementations;

import utilities.StackADT;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import implementations.Iterator;
import utilities.IteratorADT;

/**
 * Implementing a stack that is based on MyArrayList.java
 * @param <E> the type of element within this stack
 */

public class MyStack<E> implements StackADT<E> {

    /**
     * Array list to store elements of the stack.
     */
    private MyArrayList<E> elementArr;

    /**
     * Constructor to start an empty stack.
     */
    public MyStack() {
        this.elementArr = new MyArrayList<E>();
    };

    /**
     * Pushes(adds) an item to the top of the stack.
     * @param toAdd
     */
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Must not be a null element to push to stack.");
        }
        elementArr.add(toAdd);
    };

    /**
     * Removes the object at the top of this stack and returns that object as the value of this function.
     * @return the removed item from the stack.
     * @throws EmptyStackException
     */
    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E poppedItem = (E) elementArr.remove(elementArr.size() - 1);
        return poppedItem;
    };

    /**
     * Uses the Array List's get() function.
     * @return the object at the top of the stack.
     * @throws EmptyStackException
     */
    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (E) elementArr.get(elementArr.size() - 1);
    }

    /**
     * Uses the Array List's clear() function.
     * Clears all the items from this Stack. This method returns, unless there is an exception thrown.
     */
    public void clear() {
        elementArr.clear();
    }

    /**
     * Using the Array List's isEmpty() function to check if the stack that wraps it is also empty or not.
     * Returns <code>true</code> if this Stack contains no items.
     * @return
     */
    public boolean isEmpty() {
        return elementArr.isEmpty();
    }

    public Object[] toArray() {
        int size = elementArr.size();
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = elementArr.get(size - 1 - i); // top → bottom
        }
        return arr;
    }
    
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder == null) {
            throw new NullPointerException("The holder element must not be null.");
        }

        int size = elementArr.size();

        // holder too small, create new arr of the same type as holder
        if (holder.length < size) {
        	E[] newArray = (E[]) java.util.Arrays.copyOf(holder, size);
            for (int i = 0; i < size; i++) {
                // top to bottom
                newArray[i] = elementArr.get(size - 1 - i);
            }
            return newArray;
        } else {
            // holder is big enough top to bottom again
            for (int i = 0; i < size; i++) {
                holder[i] = elementArr.get(size - 1 - i);
            }
            if (holder.length > size) {
                holder[size] = null;
            }
            return holder;
        }
    }

    /**
     *  Returns true if this list contains the specified element. More formally,
     * 	returns true if and only if this list contains at least one element e such
     * 	that (o==null ? e==null : o.equals(e)).
     * @param toFind
     * @return
     * @throws NullPointerException
     */
    public boolean contains (E toFind) throws NullPointerException {
        if (toFind == null) {
            throw new NullPointerException("Must not be a null element.");
        }
        for ( int i = 0; i < elementArr.size(); i++) {
            // using .equals instead of == because comparing objects
            if (toFind.equals(elementArr.get(i))) {
                return true;
            }
        }
        return false;
    };

    /**
     * Returns the 1-based position where an object is on this stack. If the object
     * o occurs as an item in this stack, this method returns the distance from the
     * top of the stack of the occurrence nearest the top of the stack; the topmost
     * item on the stack is considered to be at distance 1. The equals method is
     * used to compare o to the items in this stack.
     * @param toFind the desired object.
     * @return
     */
    public int search (E toFind) {
        if (toFind == null) {
            throw new NullPointerException("Must not be a null element.");
        }
        for ( int i = elementArr.size() - 1; i >= 0; i--) {
            if (toFind.equals(elementArr.get(i))) {
                return elementArr.size() - i;
            }
        }
    return -1;
    }

    /**
     *
     * @param that
     * @return
     */
    public boolean equals ( StackADT<E> that) {
        if (that == null) {
            return false;
        }

        if (this.size() != that.size()) {
            return false;
        }

        Iterator<E> thisIterator = this.iterator();
        Iterator<E> thatIterator = (Iterator<E>) that.iterator();

        while (thisIterator.hasNext()) {
            E thisElement = thisIterator.next();
            E thatElement = thatIterator.next();

            if (thisElement == null) {
                if (thatElement != null) {
                    return false;
                }
            }
            else {
                if (!thisElement.equals(thatElement)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int size() {
        return elementArr.size();
    }

    public boolean stackOverflow() {
        return false;
    }
    
	@Override
	public Iterator<E> iterator() {
	    return new StackIterator();
	}
	
	// inside MyStack<E>
	private class StackIterator extends Iterator<E> {
	    // top to bottom
	    private int cursor;

	    public StackIterator() {
	        super(elementArr);
	        this.cursor = elementArr.size() - 1;
	    }

	    @Override
	    public boolean hasNext() {
	        return cursor >= 0;
	    }

	    @Override
	    public E next() {
	        if (!hasNext()) throw new NoSuchElementException();
	        E element = elementArr.get(cursor);
	        cursor--;
	        return element;
	    }
	}


}
