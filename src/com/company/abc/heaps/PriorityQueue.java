package com.company.abc.heaps;

/**
 * Created by hfire on 3/30/17.
 */
public interface PriorityQueue<T> {
    void add(Node newNode);
    Node removeMin();

    int getSize();
    boolean isEmpty();
    Node peek();

}
