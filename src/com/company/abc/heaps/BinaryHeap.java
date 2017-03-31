package com.company.abc.heaps;


import java.util.Arrays;

/**
 * Created by hfire on 3/30/17.
 */
public class BinaryHeap implements PriorityQueue {
    private static final int DEFAULT_CAPACITY = 10;
    protected Node[] array;
    protected int size;


    public BinaryHeap(){
        array = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(Node newNode) {
        // grow array if needed
        if (size >= array.length - 1) {
            array = this.resize();
        }

        // place element into heap at bottom
        size++;
        int index = size;
        array[index] = (Node) newNode;

        bubbleUp();
    }

    @Override
    public Node removeMin() {
        // what do want return?
        Node result = peek();

        // get rid of the last leaf/decrement
        array[1] = array[size];
        array[size] = null;
        size--;

        bubbleDown();
        return result;
    }


    /**
     * Performs the "bubble down" operation to place the element that is at the
     * root of the heap in its correct place so that the heap maintains the
     * min-heap order property.
     */
    protected void bubbleDown() {
        int index = 1;

        // bubble down
        while (hasLeftChild(index)) {
            // which of my children is smaller?
            int smallerChild = leftIndex(index);

            // bubble with the smaller child, if I have a smaller child
            if (hasRightChild(index)
                    && array[leftIndex(index)].compareTo(array[rightIndex(index)]) > 0) {
                smallerChild = rightIndex(index);
            }

            if (array[index].compareTo(array[smallerChild]) > 0) {
                swap(index, smallerChild);
            } else {
                // otherwise, get outta here!
                break;
            }

            // make sure to update loop counter/index of where last el is put
            index = smallerChild;
        }
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
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        return array[1];
    }
    protected boolean hasParent(int i) {
        return i > 1;
    }


    protected int leftIndex(int i) {
        return i * 2;
    }


    protected int rightIndex(int i) {
        return i * 2 + 1;
    }


    protected boolean hasLeftChild(int i) {
        return leftIndex(i) <= size;
    }


    protected boolean hasRightChild(int i) {
        return rightIndex(i) <= size;
    }


    protected Node parent(int i) {
        return array[parentIndex(i)];
    }


    protected int parentIndex(int i) {
        return i / 2;
    }


    protected Node[] resize() {
        return Arrays.copyOf(array, array.length * 2);
    }
    protected void swap(int index1, int index2) {
        Node tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    /**
     * Performs the "bubble up" operation to place a newly inserted element
     * (i.e. the element that is at the size index) in its correct place so
     * that the heap maintains the min-heap order property.
     */
    protected void bubbleUp() {
        int index = this.size;

        while (hasParent(index)
                && (parent(index).compareTo(array[index]) > 0)) {
            // parent/child are out of order; swap them
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }

}
