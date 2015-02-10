**[Printable PDF](http://csmberkeley.github.io/cs61b/week03/csm61b-week03.pdf)** &middot; **[Solutions PDF](http://csmberkeley.github.io/cs61b/week03/csm61b-week03-soln.pdf)**

# CSM Berkeley 61B, Spring 2015: Week 3



## 1. What's wrong with these sum functions?

File: [`BuggySums.java`](code/BuggySums.java) &middot; [solution](code-soln/BuggySums.java)

```java
class BuggySums {



    public static int buggySum1(int[] a) {
        int total = 0;
        for (int i = 1; i < a.length; i++) { 
            total += a[i];
        }
        return total;
    }




    public static int buggySum2(int[] a) {
        int total = 0;
        for (int i = 0; i <= a.length; i++) {
            total += a[i];
        }
        return total;
    }




    public static int buggySum3(int[] a) {
        int i = 0;
        int total = 0;
        while (i < a.length) {
            total += a[i];
        }
        return total;
    }



}
```



## 2a. Write `middle`, which takes in array of `int`s and returns the middle element.
If no element is in the exact middle, return the one to the left of the middle.  
Don't overthink this! :)

File: [`ArrayExample.java`](code/ArrayExample.java) &middot; [solution](code-soln/ArrayExample.java)

```java
import java.util.Arrays;

class ArrayExample {
    public static int middle(int[] arr) {






    }

```


## 2b. Write `reverse`, which takes in an array of `int`s and reverses its elements in-place.

```java
    public static void reverse(int[] arr) {

















    }

    public static void main(String[] args) {
        int[] test1 = {1, 3, 3, 7, 42};
        System.out.println(Arrays.toString(test1));
        reverse(test1);
        System.out.println(Arrays.toString(test1));
    }
}
```



## 3. Write `middle`, for `SList`s.
Hint: why are our pointers called slow and fast?  
If no element is in the exact middle, return the one to the left of the middle.

File: [`SList.java`](code/SList.java) &middot; [solution](code-soln/SList.java)

```java
public class SList {
    private SListNode head;
    public SList(SListNode head) {
        this.head = head;
    }
    public SList() {
        this(null);
    }

    public static Object middle(SList list) {

        SListNode slow = list.head;

        SListNode fast = list.head;

        while (                        ) {






        }
        return                             ;
    }

    public String toString() {
        String result = "";
        for (SListNode cur = head; cur != null; cur = cur.next)
            result += cur.item.toString() + " ";
        return result;
    }

    public static void main(String[] args) {
        SList l = new SList(new SListNode(1, new SListNode(2, new SListNode(3))));
        System.out.println("l = " + l);
        System.out.println("l middle: " + middle(l));
    }
}

class SListNode {
    Object item; SListNode next;
    SListNode(Object item, SListNode next) {
        this.item = item; this.next = next;
    }
    SListNode(Object item) {
        this(item, null);
    }
}
```



# 4. Spot the bug! (extra time)

File: [`IntListBug.java`](code/IntListBug.java) &middot; [solution](code-soln/IntListBug.java)

```java
class IntListBug {

    /**
      * Returns a list consisting of the elements of A followed by the
      * elements of B. May NOT modify items of A.
      */
    public static IntList buggyCatenate(IntList A, IntList B) {

        IntList C = new IntList(A.head, A.tail);

        IntList list2 = C;

        while (list2.tail != null) {

            list2 = list2.tail;

        }

        list2.tail = B;

        return C;
    }

    public static void main(String[] args) {
        IntList A = IntList.list(1, 2, 3);
        IntList B = IntList.list(4, 5, 6);

        System.out.println(A);    // 1 2 3
        IntList C = buggyCatenate(A, B);
        System.out.println(C);    // 1 2 3 4 5 6  this seems to work
        System.out.println(A);    // 1 2 3 4 5 6  oh no!
    }
}
```

File: [`IntList.java`](code/IntList.java)

```java
public class IntList {
    public int head;
    public IntList tail;

    /** Constructs an IntList from a head int and tail IntList. */
    public IntList(int head, IntList tail) { ... }

    /** Constructs an IntList from the list of arguments. */
    public static IntList list(Integer... args) { ... }

    /** Returns string representation of the IntList. */
    public String toString() { ... }
}
```
