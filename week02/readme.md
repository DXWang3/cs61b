## What’s wrong with this picture?

[Swap.java](Swap.java)

```java
class Swap {
    public static void swap(int a, int b) {
        int temp = a;
        b = a;
        a = temp;
    }

    public static void main(String[] args) {
        int x = 2;
        int y = 5;
        System.out.println("x: " + x + ", " + "y: " + y);
        swap(x, y);
        System.out.println("x: " + x + ", " + "y: " + y);
    }
}
```


## What’s different here?

[Swap2.java](Swap2.java)

```java
class Swap2 {
    public static void swap(Point p) {
        int temp = p.x;
        p.x = p.y;
        p.y = temp;
    }

    public static void main(String[] args) {
        Point x = new Point(2, 5);
        System.out.println("x: " + x.x + ", " + "y: " + x.y);
        swap(x);
        System.out.println("x: " + x.x + ", " + "y: " + x.y);
    }
}
```


## Write `skip`
`skip` takes in an IntList and destructively removes every other IntList node,
starting at the *second* node. (So for example, 1, 2, 3, becomes 1, 3.)  
**Do NOT use recursion.**

[IntList.java](IntList.java)

```java
public class IntList {
    public int head;
    public IntList tail;

    public IntList(int head0, IntList tail0) {
        head = head0; tail = tail0;
    }

    public static void skip (IntList L) {









    }

}
```


## Extra Practice: What does mystery do?
Hint: draw box and pointers.

[IntList2.java](IntList2.java)

```java
public class IntList2 {
    public int head;
    public IntList tail;

    public IntList(int head0, IntList tail0) {
        head = head0; tail = tail0;
    }

    public static IntList mystery(IntList L) {
        if (L == null || L.tail == null) {
            return L;
        } else {
            IntList x = mystery(L.tail);
            L.tail.tail = L;
            L.tail = null;
            return x;
        }
    }

    public String toString() {
        String result = "";
        IntList y = this;
        while (y != null) {
            result = result + y.head + " ";
            y = y.tail;
        }
        return result;
    }

    public static void main(String[] args) {
        IntList x = new IntList(2, new IntList(3, new IntList(4, new IntList(5, null))));
        System.out.println(x);
        IntList y = mystery(x);
        System.out.println(y);
    }
}
```
