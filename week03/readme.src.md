--- header


## 1. What's wrong with these sum functions?

--- code java code/BuggySums.java


--- onlyfor tex \newpage

## 2a. Write `middle`, which takes in array of `int`s and returns the middle element.
If no element is in the exact middle, return the one to the left of the middle. Don't overthink this.

--- code java code/ArrayExample.java

## 2b. Write `reverse`, which takes in an array of `int`s and reverses its elements in-place.

--- yield


--- onlyfor tex \newpage

## 3. Write `middle`, for `SList`s.
Hint: why are our pointers called slow and fast?  
If no element is in the exact middle, return the one to the left of the middle.

--- code java code/SList.java


--- onlyfor tex \newpage

# 4. Spot the bug! (extra time)

--- code java code/IntListBug.java

--- begin onlyfor soln
--- onlyfor soln \answerbegin
--- code java code/IntList.java (outline)
--- onlyfor soln \answerend
--- end onlyfor
