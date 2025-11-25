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
    public boolean hasNext() {
        return cursor < list.size();
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        lastReturned = cursor;
        cursor++;
        return list.get(lastReturned);
    }
    
    // TODO
}
