package implementations;

import java.util.Arrays; // only used for the exceptions of Arrays.copyOf() or System.arraycopy()
import utilities.ListADT;
import implementations.Iterator;

/**
 * Implmentation of MyArrayList. This will be the basis for creating the Stack.
 * @param <E>
 */
@SuppressWarnings("unused")
public class MyArrayList<E> implements ListADT<E> {
    private int size = 0;
    private static int INIT_SIZE = 1;
    private E[] elementArr;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        elementArr = (E[]) new Object[INIT_SIZE];
    }

    @SuppressWarnings("unchecked")
    // helper for resizing it
    private void resize(int newCapacity) {
        Object[] newArray = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = elementArr[i];
        }
        elementArr = (E[]) newArray;
    }

    
    @Override
    /**
     * @return size of the arraylist
     */
    public int size() {
        return size;
    }

    @Override
    /**
     * clears the arraylist
     */
    public void clear() {
        int arrCapacity = elementArr.length;
        for (int i = 0; i < arrCapacity; i++) {
            elementArr[i] = null;
        }
        size = 0;
    }

    @Override
    /**
     * adds element on the given index
     * @param index index to add
     * @param toAdd element to add
     * @return true if added
     * @throws NullPointerException if toAdd is null
     * @throws IndexOutOfBoundsException if the index is negative or greater than the current size
     */
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (index > size) {
            throw new IndexOutOfBoundsException("index biger than size");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("index less than zero");
        }
        if (toAdd == null) {
            throw new NullPointerException("toAdd is null bro");
        }

        int capacity = elementArr.length;
        if (size == capacity) {
            resize(Math.max(1, capacity * 2));
        }

        // shift right
        for (int i = size - 1; i >= index; i--) {
            elementArr[i + 1] = elementArr[i];
        }
        elementArr[index] = toAdd;
        size++;
        return true;
    }

    @Override
    /**
     * adds element to the end of the list
     * @param toAdd the element to add
     * @return true if the element was added successfully
     * @throws NullPointerException if toAdd is null
     */
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("toAdd is null bro");
        }
        int capacity = elementArr.length;
        if (size == capacity) {
            resize(Math.max(1, capacity * 2));
        }
        elementArr[size++] = toAdd;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    /**
     * adds all elements from another ListADT to this list.
     * @param toAdd the list of elements to add
     * @return true if at least one element was added
     * @throws NullPointerException if toAdd is null or contains null elements
     */
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("toAdd is null bro");
        }
        int addSize = toAdd.size();
        if (addSize == 0) {
            return false;
        }
        
        Object[] toAddArr;
        if (toAdd == this) {
            toAddArr = this.toArray();
        } else {
            toAddArr = toAdd.toArray();
        }


        // checks for null elements in the thing
        for (int i = 0; i < toAddArr.length; i++) {
            if (toAddArr[i] == null) {
                throw new NullPointerException("toAdd null element at " + i);
            }
        }

        int required = size + addSize;
        int newCap = elementArr.length;
        
        if (newCap == 0) {
        	newCap = 1;
        }
        while (newCap < required) {
        	newCap *= 2;
        }
        if (newCap != elementArr.length) {
        	resize(newCap);
        }

        // append elements
        for (int i = 0; i < toAddArr.length; i++) {
            elementArr[size++] = (E) toAddArr[i];
        }
        return true;
    }

    @Override
    /**
     * returns element at specified index
     * @param index the position of the element
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is negative or greater than or equal to size
     */
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        return elementArr[index];
    }

    @Override
    /**
     * removes and returns the element at specified index
     * @param index the position of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is negative or greater than or equal to size
     */
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        E removed = elementArr[index];
        // shift left
        for (int i = index; i < size - 1; i++) {
            elementArr[i] = elementArr[i + 1];
        }
        elementArr[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    /**
     * removes the specified element
     * @param toRemove the element to remove
     * @return the removed element, or null if not found
     * @throws NullPointerException if toRemove is null
     */
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) {
            throw new NullPointerException("toRemove is null");
        }
        for (int i = 0; i < size; i++) {
            if (toRemove.equals(elementArr[i])) {
                return remove(i);
            }
        }
        return null;
    }

    @Override
    /**
     * replaces element at given index with given element
     * @param index the position of the element to replace
     * @param toChange the new element
     * @return the element previously at the specified position
     * @throws NullPointerException if toChange is null
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) {
            throw new NullPointerException("toChange is null");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        E old = elementArr[index];
        elementArr[index] = toChange;
        return old;
    }

    @Override
    /**
     * if the list is empty
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    /**
     * if the list contains the specified element
     * @param toFind the element to search for
     * @return true if the element exists in the list
     * @throws NullPointerException if toFind is null
     */
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) {
            throw new NullPointerException("toFind is null");
        }
        for (int i = 0; i < size; i++) {
            if (toFind.equals(elementArr[i])) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    /**
     * copies elements into the provided array, resizes if needs to fit
     * @param toHold the array to store elements
     * @return the array containing the list elements
     * @throws NullPointerException if toHold is null
     */
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) {
            throw new NullPointerException("toHold is null");
        }

        if (toHold.length < size) {
        	// TODO we allowed to use copyOf?
            return (E[]) Arrays.copyOf(elementArr, size, toHold.getClass());
        }

        // TODO we allowed to use System.arraycopy?
        System.arraycopy(elementArr, 0, toHold, 0, size);

        if (toHold.length > size) {
            toHold[size] = null;
        }
        return toHold;
    }

    @Override
    /**
     * returns an Object array containing all elements of the list.
     * @return an array containing all elements
     */
    public Object[] toArray() {
        Object[] ret = new Object[size];
        for (int i = 0; i < size; i++) {
            ret[i] = elementArr[i];
        }
        return ret;
    }

    /**
     * adds all elements from a MyDLL list to this ArrayList
     * @param list the MyDLL list to add elements from
     * @return true if at least one element was added
     * @throws NullPointerException if the list is null or somehow contains null elements
     */
    public boolean addAll(MyDLL<E> list) throws NullPointerException {
        if (list == null) {
            throw new NullPointerException("list is null");
        }

        int addSize = list.size();
        if (addSize == 0) {
            return false;
        }

        E[] toAddArr = list.toArray();
        for (int i = 0; i < toAddArr.length; i++) {
            if (toAddArr[i] == null) {
                throw new NullPointerException("list has null element at " + i);
            }
        }

        int required = size + addSize;
        int newCap = elementArr.length;
        
        if (newCap == 0) {
        	newCap = 1;
        }
        
        while (newCap < required) {
        	newCap *= 2;
        }
        
        if (newCap != elementArr.length) {
            resize(newCap);
        }

        for (int i = 0; i < toAddArr.length; i++) {
            elementArr[size++] = (E) toAddArr[i];
        }
        return true;
    }


    @Override
    /**
     * returns an iterator over the elements in the list
     * @return an Iterator for the list
     */
    public Iterator<E> iterator() {
        return new Iterator<>(this);
    }
}
