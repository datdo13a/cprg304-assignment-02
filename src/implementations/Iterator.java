package implementations;

import utilities.IteratorADT;
import java.util.NoSuchElementException;

public class Iterator<E> implements IteratorADT<E> {
    private final MyArrayList<E> list;
    private int cursor = 0;
    private int lastReturned = -1;

    public Iterator(MyArrayList<E> list) {
        if (list == null) throw new NullPointerException("list is null bro");
        this.list = list;
    }

    @Override
    /**
     * if the iterator has more elements
     * @return true if there are more elements to iterate over
     */
    public boolean hasNext() {
        return cursor < list.size();
    }

    @Override
    /**
     * returns the next element in the iteration and advances the cursor
     * @return the next element in the list
     * @throws NoSuchElementException if no more elements
     */
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        lastReturned = cursor;
        cursor++;
        return list.get(lastReturned);
    }
}
