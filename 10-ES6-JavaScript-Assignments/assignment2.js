// Reg No: RA2311003011556
// Assignment 2: rest operator and spread operator

console.log("----- Assignment 2: rest and spread -----");

// rest operator: maximum function that takes any number of inputs
const maximum = (...values) => {
    let max = values[0];
    for (let value of values) {
        if (value > max) {
            max = value;
        }
    }
    return max;
};

console.log("maximum(4, 19, 7) =", maximum(4, 19, 7));
console.log("maximum(45, 12, 89, 3, 67, 21) =", maximum(45, 12, 89, 3, 67, 21));
console.log("maximum(-5, -2, -9) =", maximum(-5, -2, -9));

// spread operator: copy an array
const marks = [78, 92, 85, 66];
const marksCopy = [...marks];

marksCopy.push(99); // changing the copy does not change the original

console.log("Original array:", marks);
console.log("Copied array (after push 99):", marksCopy);
console.log("Same array in memory?", marks === marksCopy);
