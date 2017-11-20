package ua.nure.havrylenko.list;

import ua.nure.havrylenko.transport.Transport;

import java.util.Iterator;

public class MyListImpl implements MyList {

    Transport[] array = new Transport[0];

    private class IteratorImpl implements Iterator<Transport> {

        int currentPos = -1;
        boolean lastCallNext = false;
        boolean lastCallHasNext = false;

        // returns true if the iteration has more elements
        public boolean hasNext() {
            if (!lastCallHasNext) {
                currentPos++;
                lastCallHasNext = true;
            }
            return currentPos < array.length;
        }

        // returns the next element in the iteration
        public Transport next() {
            if (!hasNext()) {
                lastCallHasNext = false;
                return null;
            } else {
                lastCallHasNext = false;
                lastCallNext = true;
                return array[currentPos];
            }
        }

        // removes from the underlying collection the last element
        //returned by this iterator
        public void remove() {
            if (lastCallNext) {
                if (currentPos == array.length - 1) {
                    MyListImpl.this.remove(array[currentPos - 1]);
                } else {
                    MyListImpl.this.remove(array[currentPos]);
                }
                currentPos--;
                lastCallNext = false;
            } else {
                throw new IllegalStateException();
            }
        }
    }


    public void add(Transport t) {
        Transport[] newArray = new Transport[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[newArray.length - 1] = t;
        array = newArray;
    }

    public void clear() {
        array = new Transport[0];
    }

    public boolean remove(Transport t) {
        Transport[] newArray = new Transport[array.length - 1];
        for (int i = 0; i < array.length; i++) {
            if (t.equals(array[i])) {
                for (int j = i; j < newArray.length; j++) {
                    newArray[j] = array[j + 1];
                }
                array = newArray;
                return true;
            }
            if (i != array.length - 1) {
                newArray[i] = array[i];
            }
        }
        return false;
    }

    public Transport[] toArray() {
        return array;
    }

    public int size() {
        return array.length;
    }

    public boolean contains(Transport t) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(t)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(MyList c) {
        Transport[] anotherArray = c.toArray();
        for (int i = 0; i < anotherArray.length; i++) {
            if (!contains(anotherArray[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String res = "[";
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1) {
                res += array[i] + ", ";
            } else {
                res += array[i];
            }
        }
        res += "]";
        return res;
    }

    public Iterator<Transport> iterator() {
        return new IteratorImpl();
    }
}