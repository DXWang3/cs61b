--- header

## 1. What's wrong with this picture?

--- code java Swap.java
--- begin soln
`a`, `b`, and `temp` are local variables that exist only inside `swap`. The code inside `swap` only reassigns these names, which will not affect `x` or `y`. Drawing an environment diagram might help here.

In addition, since `int` is a [primitive data type], a complete copy of the variable is made when it is reassigned, so `x` and `a` store two completely independent copies of the data `2`.

[primitive data type]: http://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
--- end soln

--- newpage soln

## 2. What's different here?

--- code java Point.java
--- code java Swap2.java

--- begin soln
Here, `Point` is an object and not a primitive data type, so assignment (the `=` operator) will not create more copies of a `Point`, but only new *references* or *pointers* to an existing `Point`. (Only the constructor, `Point()`, will create new `Point`s.)

Here, `x` in `main` and `p` in `swap` are references to the same object, so changing `p.x` in `swap` will change `x.x` in `main`.
--- end soln


--- newpage

## 3. Write `skip`
`skip` takes in an IntList and destructively removes every other IntList node, starting at the *second* node. (So for example, 1, 2, 3, becomes 1, 3.)  
**Do NOT use recursion.**

--- code java IntList.java


--- newpage

## 4. Extra Practice: What does `mystery` do?
Hint: draw box and pointers.

--- code java IntList2.java
--- soln `mystery` reverses the list.
--- onlyfor soln yield
