// Reg No: RA2311003011556
// Assignment 3: object literal, destructuring, template literal

console.log("----- Assignment 3: object literal, destructuring, template literal -----");

// object literal
const book = {
    title: "Clean Code",
    author: "Robert C. Martin",
    year: 2008,
    price: 650,
    available: true
};

// destructuring: assign object values to variables in a single statement
const { title, author, year, price, available } = book;

// template literal: print the object in a user readable format
console.log(`Book Details
-------------
Title     : ${title}
Author    : ${author}
Year      : ${year}
Price     : Rs. ${price}
Available : ${available ? "Yes" : "No"}`);
