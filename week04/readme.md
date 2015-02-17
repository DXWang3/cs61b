**Note:** This is a preliminary version of the worksheet. It it almost complete.


**[Printable PDF](http://csmberkeley.github.io/cs61b/week04/csm61b-week04.pdf)** &middot; **[Solutions PDF](http://csmberkeley.github.io/cs61b/week04/csm61b-week04-soln.pdf)**

# CSM Berkeley 61B, Spring 2015: Week 4


## 1. Bit Manipulation

File: [`BitManips.java`](code/BitManips.java) &middot; [solution](code-soln/BitManips.java)

```java
public class BitManips {

```
### 1a. Rotate a 32-bit integer left by `k` bits. Assume that `k` is less than 32.
```java
    int rotate (int x, int k) {
        
        return                                                 ;
    }

```
### 1b. Check if an integer is a multiple of 4 using only the `&` operator and equality checks. 
```java
    int isMultipleOfFour(int x) {

        return                                                 ;
    }

```
### 1c. Check if an integer is odd <u>using only bit shifting and equality checks</u>.
Assume that you do not know the number of bits in your number.
```java
    int isOdd(int x) {

        return                                                 ;
    }

```
### 1d. Write a one-line expression equivalent to `x * 35` without using `*, /, or %`.
```java
    int times35(int x) {

        return                                                 ;
    }
}
```

### 1e. What does `n & (n - 1) == 0` test? (Fall 2013 Final Exam)



## 2. Lists

### 2a. `SList`s

Write a method that, given an `SList`, an `int` `j`, and an `int` `k`, return an `SList` with elements `k`, `k+j`, `k+2*j`, .... **Do not change the original list.**

File: [`Slist.java`](code/Slist.java) &middot; [solution](code-soln/Slist.java)

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
File: [`CompileTimeErrorTest.java`](code/CompileTimeErrorTest.java) &middot; [solution](code-soln/CompileTimeErrorTest.java)

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

```java
public class RuntimeErrorTest {
    private Person p;

    public RuntimeErrorTest() {
         String personName = p.getName();
         int nameLength = personName.length();
         System.out.println(nameLength);
     }

     public static void main(String[] args) {
         RuntimeErrorTest t = new RuntimeErrorTest();
     }
}

class Person {
    public String getName() {}
}
```




## 5. Vroom Vroom!

To get the car rolling!

File: [`Vehicle.java`](code/Vehicle.java)

```java
import java.util.ArrayList;

public abstract class Vehicle {
    int seats;
    int wheels;
    int fuel;
    int mpg;
    int trunkSize;
    ArrayList<Object> trunk;

    public Vehicle(int seats, int wheels, int fuel, int mpg) { 
        this.seats = seats;
        this.wheels = wheels;
        this.fuel = fuel;
        this.mpg = mpg;
        this.trunk = new ArrayList<Object>();
        this.trunkSize = 0;
    }

    public void putInTrunk(Object item) {
        System.out.println("There is no room in the Trunk");
    }

    float range() {
        return fuel * mpg;
    }
}

class Car extends Vehicle {
    public Car(int fuel, int mpg) {
        super(4, 4, fuel, mpg); 
        this.trunkSize = 2;
    }      

    public void putInTrunk(Object item) {
        if (this.trunk.size() < this.trunkSize) {
            trunk.add(item);
        } else {
            super.putInTrunk(item);
        }
    } 
}


class Motorcycle extends Vehicle {
    public Motorcycle(int fuel, int mpg) {
        super(1, 2, fuel, mpg);
    }   
}



/* Fill this class in assuming that the trunkSize of a Truck is 5*/
public class Truck extends Car {
     public Truck() {

     }
}


What will happen after each of these snippets of code are compiled/run?
        //Q1
    Vehicle v1 = new Vehicle(3,4,20,10);
    System.out.println("Range of v1: " + v1.range());

    //Q2
    Vehicle v2 = new Car(20,20);
    System.out.println("Range of v2: " + v2.range());

    //Q3
    Vehicle v3 = new Motorcycle(10,40);
    System.out.println("Range of v3: " + v3.range());
    
    //Q4
    System.out.println("Number of seats of v2 " + v2.seats);
    System.out.println("Number of seats of v3 " + v3.seats);

    //Q5
    System.out.println("Number of wheels of v2" + v2.wheels);
    System.out.println("Number of wheels of v3" + v3.wheels);

    //Q6
    v2.putInTrunk("Backpack");
    v2.putInTrunk("Laptop");
    v2.putInTrunk("Shoes");

    //Q7
    v3.putInTrunk("Backpack");
    v3.putInTrunk("Laptop");
    v3.putInTrunk("Shoes");
```
