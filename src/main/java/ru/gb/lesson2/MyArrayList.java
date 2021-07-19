package ru.gb.lesson2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements Iterable<T> {
    private int size;
    private int capacity;
    private Object[] array;

    public MyArrayList() {
        size = 0;
        capacity = 1;
        array = new Object[capacity];
    }

    public MyArrayList(int capacity) {
        size = 0;
        this.capacity = capacity;
        array = new Object[capacity];
    }

    public boolean add(T t) {
        if (size < capacity) {
            array[size] = t;
            size++;
        } else {
            capacity = capacity * 2;
            Object[] newArray = new Object[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
            array[size] = t;
            size++;
        }
        return true;
    }

    public void add(int ind, T t) {
        if (ind > size) {
            throw new ArrayIndexOutOfBoundsException(ind);
        }
        if (size < capacity) {
            System.arraycopy(array, ind, array, ind + 1, size - ind);
            array[ind] = t;
            size++;
        } else {
            capacity = capacity * 2;
            Object[] newArray = new Object[capacity];
            System.arraycopy(array, 0, newArray, 0, ind);
            System.arraycopy(array, ind, newArray, ind + 1, size - ind);
            array = newArray;
            array[ind] = t;
            size++;
        }
    }

    public T get(int ind) {
        if (ind > size - 1) {
            throw new ArrayIndexOutOfBoundsException(ind);
        }
        return (T) array[ind];
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public T remove(int ind) {
        if (ind > size - 1) {
            throw new ArrayIndexOutOfBoundsException(ind);
        }
        T res = (T) array[ind];
        System.arraycopy(array, ind + 1, array, ind, size - ind - 1);
        size--;
        return res;
    }

    public boolean remove(T t) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(t)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(T t) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder arr = new StringBuilder();
        for (int i = 0; i < size - 1; i++) {
            arr.append(array[i] + ", ");
        }
        arr.append(array[size - 1]);
        return "MyArrayList=[" + arr.toString() + "]";
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator(0);
    }

    private class MyIterator implements Iterator<T> {
        int cur;

        private MyIterator(int cur) {
            this.cur = cur;
        }

        @Override
        public boolean hasNext() {
            return (cur < size);
        }

        @Override
        public T next() {
            if (cur >= size) {
                throw new NoSuchElementException();
            }
            T res = (T) array[cur];
            cur++;
            return res;
        }
    }
}
