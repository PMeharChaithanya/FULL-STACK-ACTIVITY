# Student Expense Tracker (React Mini Project)

**Reg No:** RA2311003011556

## Real-world problem
Students often lose track of small daily expenses and run out of money before the month ends.
This app lets a student record expenses, see how much of the monthly budget is used, get a warning
at 80% and an alert when the budget is exceeded, and filter expenses by category.

## React concepts used
| Concept | Where |
|---|---|
| Components (function components) | Header, BudgetSummary, ExpenseForm, ExpenseList, ExpenseItem |
| JSX | All components (expressions `{}`, `map()` lists with `key`, conditional rendering `&&` / `? :`) |
| Props | Data and callbacks passed from App to child components (e.g. `onAdd`, `onDelete`) |
| `useState` | Form fields and category filter |
| `useEffect` | `useLocalStorage` hook saves data to localStorage whenever it changes |
| `useMemo` | Total spent |
| `useRef` | Re-focus the title input after adding an expense |
| Custom hook | `useLocalStorage` (src/hooks/useLocalStorage.js) |
| Events & forms | Controlled inputs, `onSubmit`, `onChange`, `onClick`, validation |
| Lifting state up | Expenses are kept in App and shared with the list and summary |

## Run
```bash
npm install
npm run dev
```
Open http://localhost:5173
