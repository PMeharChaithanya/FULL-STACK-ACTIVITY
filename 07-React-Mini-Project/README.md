# Activity 7: React Mini Project – Student Expense Tracker

**Reg No:** RA2311003011556

## Task
Create a React application with React components, JSX, hooks, etc. on your own, reflecting a real-world problem.

## Real-world problem
Students often lose track of small daily expenses and run out of money before the month ends. This app lets a student record expenses, see how much of the monthly budget is used, get a warning at 80% and an alert when the budget is exceeded, and filter expenses by category.

## Features
- Add an expense (title, amount, category, date) with validation
- Budget box: monthly budget (editable), total spent, remaining, % used
- Warning at 80% and alert at 100% of the budget
- Filter expenses by category
- Delete expenses
- Data is saved in the browser (localStorage), so it stays after refresh

## React concepts used
| Concept | Where |
|---|---|
| Function components | `Header`, `BudgetSummary`, `ExpenseForm`, `ExpenseList`, `ExpenseItem` |
| JSX | All components – expressions, lists with `map()` and `key`, conditional rendering |
| Props | Data and callbacks from `App` to children (`onAdd`, `onDelete`, `setBudget`) |
| `useState` | Form fields and category filter |
| `useEffect` | Saving data to localStorage inside the custom hook |
| `useMemo` | Total spent |
| `useRef` | Focus the title box again after adding an expense |
| Custom hook | `useLocalStorage` (`src/hooks/useLocalStorage.js`) |
| Lifting state up | Expenses kept in `App` and shared by the list and summary |

## Project structure
```
expense-tracker-react/
├── index.html
├── package.json
├── vite.config.js
└── src
    ├── main.jsx
    ├── App.jsx
    ├── App.css
    ├── hooks/useLocalStorage.js
    └── components/
        ├── Header.jsx
        ├── BudgetSummary.jsx
        ├── ExpenseForm.jsx
        ├── ExpenseList.jsx
        └── ExpenseItem.jsx
```

## How to run
```bash
cd expense-tracker-react
npm install
npm run dev
```
Open http://localhost:5173

## Output
The page shows the title with Reg No, the Budget box (Budget Rs. 8000, Total spent Rs. 5370, Remaining Rs. 2630, Used 67%), the Add Expense form, a category filter, and the table of expenses with Delete buttons. Adding an expense of about Rs. 1500 takes usage above 80% and shows the warning message.
