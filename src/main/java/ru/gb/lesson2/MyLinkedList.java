package ru.gb.lesson2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;

    public MyLinkedList() {
        first = null;
        last = null;
    }

    public void addLast(T t) {
        Node<T> newNode = new Node<>(t);
        if (last != null) {
            newNode.setPrev(last);
            last.setNext(newNode);
            last = newNode;
        } else {
            first = newNode;
            last = newNode;
        }
        size++;
    }

    public void addFirst(T t) {
        Node<T> newNode = new Node<>(t);
        if (first != null) {
            newNode.setNext(first);
            first.setPrev(newNode);
            first = newNode;
        } else {
            first = newNode;
            last = newNode;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public void add(int ind, T t) {
        if (ind > size || ind < 0) {
            throw new ArrayIndexOutOfBoundsException(ind);
        }
        if (ind == 0) {
            addFirst(t);
        } else if (ind == size) {
            addLast(t);
        } else {
            Node<T> newNode = new Node<>(t);
            Node<T> cur = getNodeInd(ind);
            newNode.prev = cur.prev;
            newNode.next = cur;
            cur.prev.next = newNode;
            cur.prev = newNode;
            size++;
        }
    }

    private Node<T> getNodeInd(int ind) {
        Node<T> cur;
        if (ind <= size / 2) {
            cur = first;
            for (int i = 0; i < ind; i++) {
                cur = cur.next;
            }
        } else {
            cur = last;
            for (int i = 0; i < size - ind - 1; i++) {
                cur = cur.prev;
            }
        }
        return cur;
    }

    public T get(int ind) {
        if (ind > size - 1 || ind < 0) {
            throw new IndexOutOfBoundsException();
        }
        return getNodeInd(ind).value;
    }

    public T getFirst() {
        if (first != null) {
            return first.value;
        } else {
            throw new NoSuchElementException();
        }
    }

    public T getLast() {
        if (last != null) {
            return last.value;
        } else {
            throw new NoSuchElementException();
        }
    }

    public T remove(int ind) {
        if (ind > size - 1 || ind < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (ind == 0) {
            return removeFirst();
        } else if (ind == size - 1) {
            return removeLast();
        } else {
            Node<T> res = getNodeInd(ind);
            res.prev.setNext(res.next);
            res.next.setPrev(res.prev);
            size--;
            return res.value;
        }
    }

    public T removeFirst() {
        if (first != null) {
            T res = first.value;
            first = first.next;
            if (first != null) {
                first.setPrev(null);
            } else {
                last = null;
            }
            size--;
            return res;
        } else {
            throw new NoSuchElementException();
        }
    }

    public T removeLast() {
        if (last != null) {
            T res = last.value;
            last = last.prev;
            if (last != null) {
                last.setNext(null);
            } else {
                first = null;
            }
            size--;
            return res;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public String toString() {
        if (first == null) {
            return "MyLinkedList=[]";
        }
        StringBuilder arr = new StringBuilder();
        Node<T> cur = first;
        arr.append(cur.value);
        while (cur.next != null) {
            cur = cur.next;
            arr.append(", " + cur.value);
        }
        return "MyLinkedList=[" + arr.toString() + "]";
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<T>(first);
    }

    private static class MyIterator<T> implements Iterator<T> {
        Node<T> cur;

        private MyIterator(Node<T> cur) {
            this.cur = cur;
        }

        @Override
        public boolean hasNext() {
            return (cur != null);
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T res = cur.value;
            cur = cur.next;
            return res;
        }
    }

    private static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;

        public Node(T value) {
            this.value = value;
            prev = null;
            next = null;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
