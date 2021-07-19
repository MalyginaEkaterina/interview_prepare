package ru.gb.lesson2;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> arr = new MyArrayList<>(3);
        System.out.println(arr.isEmpty());
        System.out.println(arr.size());
        arr.add(3);
        arr.add(6);
        arr.add(7);
        System.out.println(arr);
        arr.add(0, 16);
        System.out.println("iterator works:");
        for (Integer i : arr) {
            System.out.println(i);
        }
        System.out.println(arr);
        System.out.println(arr.get(0));
        System.out.println(arr.get(1));
        System.out.println(arr.isEmpty());
        System.out.println(arr.size());
        arr.remove(0);
        System.out.println(arr);
        arr.remove((Integer) 6);
        System.out.println(arr);
        System.out.println(arr.remove((Integer) 8));
        System.out.println(arr.indexOf(7));
       // System.out.println(arr.get(4));


        MyLinkedList<String> sArr = new MyLinkedList<>();
        sArr.addLast("one");
        sArr.addLast("two");
        sArr.addLast("three");
        sArr.addLast("four");
        sArr.addFirst("zero");
        System.out.println(sArr);
        sArr.add(4, "new");
        System.out.println(sArr);
        System.out.println(sArr.size());
        System.out.println(sArr.get(0));
        System.out.println(sArr.get(1));
        System.out.println(sArr.get(4));
        System.out.println(sArr.get(5));
        //System.out.println(sArr.get(6));
        System.out.println(sArr.getFirst());
        System.out.println(sArr.getLast());
        System.out.println(sArr);
        System.out.println(sArr.remove(4));
        System.out.println(sArr);
        System.out.println("iterator works");
        for (String s : sArr) {
            System.out.println(s);
        }
        System.out.println("iterator stop");
        sArr.removeLast();
        System.out.println(sArr);
        sArr.removeFirst();
        sArr.removeLast();
        sArr.removeFirst();
        System.out.println(sArr);
        sArr.removeLast();
        System.out.println(sArr);

    }
}
