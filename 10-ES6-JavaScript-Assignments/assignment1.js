// Reg No: RA2311003011556
// Assignment 1: map, filter, reduce with arrow functions

console.log("----- Assignment 1: map, filter, reduce -----");

// map -> take strings and make them uppercase
const fruits = ["apple", "banana", "mango", "orange"];
const upperFruits = fruits.map(fruit => fruit.toUpperCase());
console.log("Original strings:", fruits);
console.log("map (uppercase):", upperFruits);

// filter -> take numbers and keep only multiples of 5
const numbers = [3, 5, 10, 12, 15, 22, 25, 30, 41, 50];
const multiplesOf5 = numbers.filter(num => num % 5 === 0);
console.log("Original numbers:", numbers);
console.log("filter (multiples of 5):", multiplesOf5);

// reduce -> remove numbers from a string
const text = "Re4ct2 J5v8Scr1pt 2026";
const withoutNumbers = text
    .split("")
    .reduce((result, ch) => (ch >= "0" && ch <= "9" ? result : result + ch), "");
console.log("Original string:", text);
console.log("reduce (numbers removed):", withoutNumbers);
