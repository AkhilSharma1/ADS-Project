package com.company.abc.heaps;


import java.util.Arrays;

/**
 * Created by hfire on 3/30/17.
 */
public class FourWayHeap implements PriorityQueue {

    private Node[] array = new Node[10];
    private int size;            // keeps a count of number of elements in the heap
    private int numWays = 4;            // 4-way heap, number of children a each node has

    @Override
    public void add(Node newNode) {
        if (size >= array.length - 1) {
            array = this.resize();
        }
        array[size] = newNode;
        heapUp(size);
        size++;
    }

    public void heapUp(int i) {
        Node child = array[i];
        int parentIndex = (i-1)/numWays;
        Node parent = array[parentIndex];
        while ((i > 0) && (child.compareTo(parent) < 0)) {
            array[i] = array[parentIndex];
            i = parentIndex;
            parentIndex = (parentIndex-1)/numWays;
        }
        array[i] = child;
    }

    @Override
    public Node removeMin() {

        Node min = array[0];
        size--;
        // set the last element as root
        array[0] = array[size];
        heapDown(0);
        return min;
    }

    // heapDown() method, this method compares the replacement element with the max of children,
    // if the largest child is larger than this replacement element, we move the child up, we continue doing this until heap order is correct and put the replacement element in correct index.
    public void heapDown(int ix) {
        int minChild = findMin(ix*numWays+1,ix*numWays+numWays);
       Node tempRoot = array[ix];       // save root in temp variable
        while ((minChild < size) && (array[minChild].compareTo( tempRoot)<0)){
            array[ix] = array[minChild];
            ix = minChild;
            minChild = findMin(minChild*numWays+1, minChild*numWays+numWays);
        }
        array[ix] = tempRoot;
    }

    // find minimum of the children
    private int findMin(int from, int to) {
        int minChild = from;
        for (int i = from+1; (i<=to && i< size); i++){
            if ((array[minChild].compareTo (array[i])>0))
                minChild = i;
        }
        return minChild;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Node peek() {
        return null;
    }


    protected Node[] resize() {
        return Arrays.copyOf(array, array.length * 2);
    }


}
