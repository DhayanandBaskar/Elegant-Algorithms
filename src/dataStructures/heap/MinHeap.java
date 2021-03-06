package dataStructures.heap;

import java.util.Arrays;

import static utils.Util.swap;

/**
 * Binary Heap
 * Time complexity - getMin : O(1), pop : O(log n), add :  O(log n)
 */

public class MinHeap {
    int index = 0;
    int[] store;

    public MinHeap(int n) {
        store = new int[n + 1];
    }

    public MinHeap(MinHeap copy) {
        index = copy.size();
        store = copy.getAll();
    }

    public void add(int item) {
        store[++index] = item;
        swim(index);
    }

    public int pop() {
        swap(store, 1, index--);
        sink(1);
        return store[index + 1];
    }

    public int getMin() {
        return store[1];
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public int peek() {
        if (isEmpty()) throw new Empty();
        return store[1];
    }

    public int size() {
        return index;
    }

    public int[] getAll() {
        return Arrays.copyOfRange(store, 0, index + 1);
    }

    private void sink(int current) {
        while (current * 2 <= index) {
            int child = current * 2;
            if (child < index && store[child] > store[child + 1]) child++;
            if (store[current] < store[child]) break;
            swap(store, current, child);
            current = child;
        }
    }

    private void swim(int current) {
        while (current / 2 != 0 && store[current] < store[current / 2]) {
            swap(store, current, current / 2);
            current = current / 2;
        }
    }

    public static class Empty extends RuntimeException {
    }
}