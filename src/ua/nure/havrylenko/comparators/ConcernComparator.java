package ua.nure.havrylenko.comparators;

import java.util.Comparator;
import ua.nure.havrylenko.transport.*;

public class ConcernComparator implements Comparator<Transport> {

    @Override
    public int compare(Transport t1, Transport t2) {
        return t1.getConcern().compareTo(t2.getConcern());
    }
}
