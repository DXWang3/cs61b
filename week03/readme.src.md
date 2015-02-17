--- header


## 1. What's wrong with these sum functions?

--- code java BuggySums.java
--- soln `i` starts at 1, and so the the 0th iteration is skipped.
--- onlyfor soln yield
--- soln `i` goes up to `a.length`, but the last item of `a` is at `a[a.length - 1]`.
--- onlyfor soln yield
--- soln `i` is never incremented, so this is stuck in an infinite loop.
--- onlyfor soln yield


--- newpage

## 2a. Write `middle`, which takes in array of `int`s and returns the middle element.
If no element is in the exact middle, return the one to the left of the middle.  
Don't over-think this! :)

--- code java ArrayExample.java


## 2b. Write `reverse`, which takes in an array of `int`s and reverses its elements in-place.

--- yield


--- newpage

## 3. Write `middle`, for `SList`s.
Hint: why are our pointers called slow and fast?  
If no element is in the exact middle, return the one to the left of the middle.

--- code java SList.java


--- newpage

# 4. Spot the bug! (extra time)

--- code java IntListBug.java
--- begin soln
This step attempts to copy `A` to `C`, but actually only copies over the first node of `A`. The rest, `A.tail`, contains the same nodes as `A`. The way to fix this would be to use a loop to copy over *all* the nodes in `A`. For example:
--- onlyfor soln yield
--- end soln
--- onlyfor soln yield

--- newpage soln
--- code java IntList.java (outline)
