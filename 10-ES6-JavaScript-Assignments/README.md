# Activity 10: ES6 JavaScript Assignments

**Reg No:** RA2311003011556

## Task
1. Create map, filter, reduce with arrow functions:
   - map → take strings and make them uppercase
   - filter → take numbers and keep only multiples of 5
   - reduce → remove numbers from a string
2. Use the rest operator to create a maximum function that takes any number of inputs and returns the maximum value. Use the spread operator to copy an array.
3. Use an object literal to create an object and use destructuring to assign object values to variables in a single statement. Use a template literal to print the object in a user-readable format.
4. Create an object, use a default parameter in a function of the object. Use `this` in the object's function to access variables. Use constant values in the object.

## Files
| File | Assignment |
|---|---|
| `assignment1.js` / `.html` | map, filter, reduce with arrow functions |
| `assignment2.js` / `.html` | rest operator `maximum(...values)` and spread copy `[...marks]` |
| `assignment3.js` / `.html` | object literal `book`, destructuring, template literal |
| `assignment4.js` / `.html` | `bankAccount` object with default parameters, `this`, read-only constants |
| `index.html` | Runs all 4 and shows the output on the page and in the console |

## How to run
Open any `assignmentN.html` (or `index.html`) in a browser, then **Right click → Inspect → Console** to see the output.

## Output
```
----- Assignment 1: map, filter, reduce -----
Original strings: [ 'apple', 'banana', 'mango', 'orange' ]
map (uppercase): [ 'APPLE', 'BANANA', 'MANGO', 'ORANGE' ]
Original numbers: [
   3,  5, 10, 12, 15,
  22, 25, 30, 41, 50
]
filter (multiples of 5): [ 5, 10, 15, 25, 30, 50 ]
Original string: Re4ct2 J5v8Scr1pt 2026
reduce (numbers removed): Rect JvScrpt 

----- Assignment 2: rest and spread -----
maximum(4, 19, 7) = 19
maximum(45, 12, 89, 3, 67, 21) = 89
maximum(-5, -2, -9) = -2
Original array: [ 78, 92, 85, 66 ]
Copied array (after push 99): [ 78, 92, 85, 66, 99 ]
Same array in memory? false

----- Assignment 3: object literal, destructuring, template literal -----
Book Details
-------------
Title     : Clean Code
Author    : Robert C. Martin
Year      : 2008
Price     : Rs. 650
Available : Yes

----- Assignment 4: default parameter, this, const -----
State Bank of India | Account holder: Student | Balance: Rs. 5000
Deposited Rs. 2000. New balance: Rs. 7000
Deposited Rs. 500. New balance: Rs. 7500
Withdrew Rs. 3000. New balance: Rs. 4500
Withdrew Rs. 500. New balance: Rs. 4000
Cannot withdraw Rs. 4000. Minimum balance of Rs. 1000 must be kept.
After trying to change MIN_BALANCE to 0, it is still: 1000
State Bank of India | Account holder: Student | Balance: Rs. 4000

```
