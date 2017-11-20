package ua.nure.havrylenko.list;

import ua.nure.havrylenko.transport.Transport;

public interface MyList extends Iterable<Transport> {
    // appends the specified element to the end of this list
    void add(Transport e);

    // removes all of the elements from this list
    void clear();

    // removes the first occurrence of the specified element
    // from this list
    boolean remove(Transport o);

    // returns an array containing all of the elements
    // in this list in proper sequence
    Transport[] toArray();

    // returns the number of elements in this list
    int size();

    // returns true if this list contains the specified element
    boolean contains(Transport o);

    // returns true if this list contains all of the elements
    // of the specified list
    boolean containsAll(MyList c);
}
