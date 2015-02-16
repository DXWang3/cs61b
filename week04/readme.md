**Note:** This is a preliminary version of the worksheet. It it almost complete.


**[Printable PDF](http://csmberkeley.github.io/cs61b/week04/csm61b-week04.pdf)** &middot; **[Solutions PDF](http://csmberkeley.github.io/cs61b/week04/csm61b-week04-soln.pdf)**

# CSM Berkeley 61B, Spring 2015: Week 4


## 1. Bit Manipulation

File: [`BitManips.java`](code/BitManips.java)

\vspace{-0.5em}
```java
public class BitManips {

```
### 1a. Rotate a 32-bit integer by k bits.  Assume that k is less than 32.
\vspace{-0.5em}
```java
    int rotate (int x, int k) {
        
        return                                                 ;
    }

```
### 1b. Check if an integer is a multiple of 4 using only the & operator and equality checks. 
\vspace{-0.5em}
```java
    int isMultipleOfFour(int x) {

        return                                                 ;
    }

```
### 1c. Check if an integer is odd using only bit shifting and equality checks.
Assume that you do not know the number of bits in your number.
\vspace{-0.5em}
```java
    int isOdd(int x) {

        return                                                 ;
    }

```
### 1d. Write a one-line expression equivalent to `x * 35` without using `*, /, or %`.
\vspace{-0.5em}
```java
    int times35(int x) {

        return                                                 ;
    }
}
```

### 1e. What does `n & (n - 1) == 0` test? (Fall 2013 Final Exam)




## 2. Lists

### 2a. `SList`s

Write a method that, given an `SList`, an `int j`, and an `int k`, return elements `k`, `k+j`, `k+2*j`, ....

File: [`Slist.java`](code/Slist.java)

\vspace{-0.5em}
```java
public class SList {
    private SListNode head;
    public SList(SListNode head) {
        this.head = head;
    }

    public static Object multiples(SList list, int j, int k) {









    }

    public String toString() {
        String result = "";
        for (SListNode cur = head; cur != null; cur = cur.next)
            result += cur.item.toString() + " ";
        return result;
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



### 2b. Arrays

![](img/array-point-question.png)




## 3. Static and dynamic types review

```java
List l;
if (use_linked_list) {
    l = new LinkedList();
} else {
    l = new ArrayList();
}
```

**static types** = the **declared** type = checked at **compile time**  
We don't need to run the code to know that `l` is a `List`.

**dynamic type** = the **actual** type = checked at **run time**  
When we run the code, depending on the situation, `l` might either be a `LinkedList` or `ArrayList`.

```java
// What would Java do?
Collection c;
if (use_set) {
    c = new HashSet();
} else {
    c = new ArrayList();
}

// Example 1: works!
c.isEmpty();  // works because Collection.isEmpty() exists
c.size();     // works because Collection.size() exists

// Example 2: compile time error
c.sort();  // compile-time error: Collection.sort() doesn't exist
c.get(0);  // compile-time error: Collection.get(int) doesn't exist
```

Static types are like guarantees or agreements. The declaration `Collection c` means that `c` is guaranteed to have `Collection`'s methods, including `isEmpty()` and `size()`. Even though `ArrayList` has some additional methods like `sort()` and `get(int)`, there was no agreement that `c` would be an `ArrayList`, so you can't use these methods. Java does this to prevent you from calling methods that might not exist at runtime -- for example, what if `c` happens to be a `HashSet` and you called `c.sort()`?

Java follows simple rules (think: "Java is dumb"). Even when it's clear to you that `c` here is definitely an `ArrayList`, you still have to declare it as such. That is,

```java
Collection c = new ArrayList();
c.sort();
```

will still fail at compile time. This is not necessarily a bad thing! When I declare `c` to be a `Collection` here, it kind of means I'm saying "I just want a `Collection`, it'll be an `ArrayList` here but I don't want to do any `ArrayList`-specific things."

```java
// Example 3: works, but has different results
c.add(1);
c.add(1);
c.size();  // Will this equal 1 or 2?
```

Note that `Collection` has no method implementation of its own. Java knows to look at the methods for `HashSet` or `ArrayList`, depending on what the dynamic type of `c` is.




## 4. Static and dynamic types questions

### 4a. Spot the compile time errors. (There are four!)
File: [`CompileTimeErrorTest.java`](code/CompileTimeErrorTest.java)

\vspace{-0.5em}
```java
public class CompileTimeErrorTest {
    public string howOld(age) {
        if age <= 18 {
             return "Not very old";
        } else if (age > 21) {
             return "Really old";
        }
    }
}
```

### 4b. Where is the runtime error?
File: [`RuntimeErrorTest.java`](code/RuntimeErrorTest.java)

\vspace{-0.5em}
```java
public class RuntimeErrorTest {
    private Person p;

    public Test() {
         String personName = p.getName();
         int nameLength = personName.length();
         System.out.println(nameLength);
     }

     public static void main(String[] args) {
         Test t = new Test();
     }
}

class Person {
    public String getName() {}
}
```
