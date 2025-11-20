package implementations;
import utilities.ListADT;

@SuppressWarnings("rawtypes")
public class MyArrayList<E> implements ListADT {
	private int size = 0;
	private static int initSize = 1;
	E[] elementArr;
	
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		elementArr = (E[]) new Object[initSize];
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int newCapacity) {
		Object[] newArray = new Object[newCapacity];
		for(int i = 0; i < size(); i++) {
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

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(int index, Object toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if (index > size()) {
			throw new IndexOutOfBoundsException("index bigger than size");
		}
		if (index < 0) {
			throw new IndexOutOfBoundsException("index less than zero");
		}
		if (toAdd == null) {
			throw new NullPointerException("toAdd is null");
		}

		int capacity = elementArr.length;
		if (size == capacity) {
			resize(capacity * 2);
		}

		// shift??
		for (int i = size - 1; i >= index; i--) {
			elementArr[i + 1] = elementArr[i];
		}
		elementArr[index] = (E) toAdd;
		size++;
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(Object toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException("toAdd is null");
		}
		int capacity = elementArr.length;
		if (size == capacity) {
			resize(Math.max(1, capacity * 2));
		}
		elementArr[size++] = (E) toAdd;
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(ListADT toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException("toAdd is null");
		}
		int addSize = toAdd.size();
		if (addSize == 0) {
			return false;
		}
		// If the source is this list, copy elements first to avoid concurrent modification
		Object[] toAddArr = toAdd == this ? this.toArray() : toAdd.toArray();
		// check for null elements in provided collection
		for (int i = 0; i < toAddArr.length; i++) {
			if (toAddArr[i] == null) {
				throw new NullPointerException("toAdd contains null element at index " + i);
			}
		}
		// ensure capacity in bulk
		int required = size + addSize;
		int newCap = elementArr.length;
		if (newCap == 0) newCap = 1;
		while (newCap < required) newCap *= 2;
		if (newCap != elementArr.length) resize(newCap);
		// append elements
		for (int i = 0; i < toAddArr.length; i++) {
			elementArr[size++] = (E) toAddArr[i];
		}
		return true;
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index out of bounds");
		}
		return elementArr[index];
	}

	@Override
	public Object remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index out of bounds");
		}
		Object removed = elementArr[index];
		// shift left
		for (int i = index; i < size - 1; i++) {
			elementArr[i] = elementArr[i + 1];
		}
		elementArr[size - 1] = null;
		size--;
		return removed;
	}

	@Override
	public Object remove(Object toRemove) throws NullPointerException {
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

	@SuppressWarnings("unchecked")
	@Override
	public Object set(int index, Object toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (toChange == null) {
			throw new NullPointerException("toChange is null");
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index out of bounds");
		}
		Object old = elementArr[index];
		elementArr[index] = (E) toChange;
		return old;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object toFind) throws NullPointerException {
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

	@Override
	public Object[] toArray(Object[] toHold) throws NullPointerException {
		if (toHold == null) {
			throw new NullPointerException("toHold is null");
		}
		if (toHold.length < size) {
			Object[] ret = new Object[size];
			for (int i = 0; i < size; i++) ret[i] = elementArr[i];
			return ret;
		} else {
			for (int i = 0; i < size; i++) toHold[i] = elementArr[i];
			if (toHold.length > size) toHold[size] = null;
			return toHold;
		}
	}

	@Override
	public Object[] toArray() {
		Object[] ret = new Object[size];
		for (int i = 0; i < size; i++) {
			ret[i] = elementArr[i];
		}
		return ret;
	}

}
