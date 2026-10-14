# Activity 9: React Standalone Mini Project – College Canteen Food Order

**Reg No:** RA2311003011556

## Task
Go through the standalone React samples (component, JSX, hooks, conditional rendering, tic-tac-toe) and the exercises at https://react.dev/learn, then build a mini project with React as a **standalone HTML file**, using React components, hooks, JSX, etc.

## Real-world problem
Students wait in long queues at the college canteen. This app lets a student pick items from the menu, see the bill, place the order and see a countdown until the food is ready – before reaching the counter.

## Features
- Menu table with **All / Veg / Non-Veg** filter
- Sold-out items cannot be added
- Cart with **+ / −** quantity buttons and automatic total
- Place Order → order number and a countdown until the food is ready
- Browser tab title shows the number of items in the cart

## React concepts used
| Concept | Where |
|---|---|
| Components | `Header`, `FilterButtons`, `Menu`, `MenuItem`, `Cart`, `OrderStatus`, `App` |
| JSX | All components |
| Props | e.g. `<MenuItem item={item} onAdd={onAdd} />` |
| `useState` | cart, filter, order, order number, countdown seconds |
| `useEffect` | countdown timer (with cleanup) and updating `document.title` |
| Conditional rendering | Add vs Sold out, empty cart message, waiting vs ready |
| Lists and keys | menu rows and cart rows with `map()` |
| Event handling | `onClick` on Add, +, −, filters, Place Order, New Order |
| Sharing state | cart state lives in `App` and is passed to `Menu` and `Cart` |

## How to run
Open `CanteenOrder.html` in a browser (an internet connection is needed to load React and Babel from the CDN, same as the samples).

## Output
1. Menu with 7 items (Masala Dosa Rs. 50, Idli Rs. 30, Veg Fried Rice Rs. 70, Chicken Biryani Rs. 120, Egg Puff – Sold out, Tea Rs. 15, Cold Coffee Rs. 40) and an empty cart.
2. After adding items: the cart shows each item, quantity and amount, with the total.
3. After Place Order: "Order #101 placed!", the total paid, a countdown, then "Your order is ready. Please collect it at the counter."
