--- header


## 1. What's wrong with these sum functions?

--- code java BuggySums.java


--- onlyin tex \newpage

## 2. Write `middle`, which takes in array of `int`s and returns the middle element.
If not element is in the exact middle, return the one to the left of the middle. Don't overthink this.

--- code java ArrayExample.java

## Write `reverse`, which takes in an array of `int`s and reverses its elements in-place.

--- yield


--- onlyin tex \newpage

## 3. Write `middle`, for `SList`s.
Hint: why are our pointers called slow and fast?  
If not element is in the exact middle, return the one to the left of the middle.

--- code java SList.java


--- onlyin tex \newpage

# 4. Spot the bug! (extra time)

--- code java IntListBug.java

--- code-header java IntList.java
```java
public class IntList {
    public int head;
    public IntList tail;

    /** Constructs an IntList from a head int and tail IntList. */
    public IntList(int head, IntList tail);

    /** Constructs an IntList from the list of arguments. */
    public static IntList list(Integer... args);

    /** Returns string representation of the IntList. */
    public String toString();
}
```
