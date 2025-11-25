package implementations;

import java.util.Arrays;
import utilities.ListADT;
import implementations.Iterator;

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
    private void resize(int newCapacity) {
        Object[] newArray = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = elementArr[i];
        }
        elementArr = (E[]) newArray;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        int arrCapacity = elementArr.length;
        for (int i = 0; i < arrCapacity; i++) {
            elementArr[i] = null;
        }
        size = 0;
    }

    @Override
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
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        return elementArr[index];
    }

    @Override
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
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
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
    public Object[] toArray() {
        Object[] ret = new Object[size];
        for (int i = 0; i < size; i++) {
            ret[i] = elementArr[i];
        }
        return ret;
    }

    @Override
    public implementations.Iterator<E> iterator() {
        return new Iterator<>(this);
    }
}
