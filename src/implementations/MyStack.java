package implementations;

import utilities.StackADT;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Iterator;

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

    /**
     * Uses the Array List's toArray() function.
     * @return an array containing all of the elements in this list in proper sequence.
     */
    public Object[] toArray() {
        return elementArr.toArray();
    }

    /**
     * To create an array when given a parameter.
     * @param holder
     * @return the array into which the elements of this stack are to be
     * stored, if it is big enough; otherwise, a new array of the same
     * runtime type is allocated for this purpose.
     * @throws NullPointerException
     */
    public E[] toArray( E[] holder ) throws NullPointerException {
        if (holder == null) {
            throw new NullPointerException("The holder element must not be null.");
        }
        if (holder.length    < elementArr.size()) {
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
                holder[i] = (E) elementArr.get(i);
            }
            if (holder.length > elementArr.size()) {
                holder[elementArr.size()] = null;
            }
            return holder;
        }
    };

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
     * Returns an iterator over the elements in this stack in proper sequence.
     * Takes methods from Iterator.java
     * @return an iterator over the elements in this stack in proper sequence.
     */
    public Iterator<E> iterator() {
        return new StackIterator();
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
        Iterator<E> thatIterator = that.iterator();

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

    /**
     * Inner class implementing java.util.Iterator
     */
    private class StackIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < elementArr.size();
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in stack");
            }
            E element = (E) elementArr.get(currentIndex);
            currentIndex++;
            return element;
        }
    }

}
