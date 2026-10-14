# Activity 11: JavaScript Assignment – Password Checker, Grade Calculator, Expense Tracker

**Reg No:** RA2311003011556

Only HTML, CSS and JavaScript are used – no libraries.

## Q1) Password validity and strength – `q1-password.html`
**Task:** A password text box with a *Check Validity* button and a strength label. A valid password has a lowercase letter, an uppercase letter, a number, a special character and at least 8 characters. Strength is *Strong* if all criteria are met, *Medium* if about half are met, *Weak* if less than half.

**Solution:**
- 5 rules are checked by `countPassedRules()`.
- `getStrength()`: 5/5 → Strong, 3–4/5 → Medium, 0–2/5 → Weak.
- The strength label updates while typing (`input` event); *Check Validity* (`click` event) shows Valid / Invalid and a ✔/✘ list of the rules.

| Input | Rules met | Strength | Validity |
|---|---|---|---|
| `abc` | 1/5 | Weak | Invalid |
| `abcD1` | 3/5 | Medium | Invalid |
| `Abcdef12` | 4/5 | Medium | Invalid |
| `Abcd@123` | 5/5 | Strong | Valid |

## Q2) Grade calculator – `q2-grade.html`
**Task:** Enter marks in a text box; *Calculate Grade* shows the grade in a text label. 91–100 → O, 81–90 → A+, 71–80 → A, 61–70 → B, 51–60 → C, ≤50 → F.

**Solution:** `calculateGrade()` (button `click` and Enter key) validates the input (0–100, numbers only) and `getGrade()` returns the grade.

| Marks | 100 | 91 | 90 | 81 | 80 | 71 | 70 | 61 | 60 | 51 | 50 | 0 |
|---|---|---|---|---|---|---|---|---|---|---|---|---|
| Grade | O | O | A+ | A+ | A | A | B | B | C | C | F | F |

## Q3) Expense tracker – `q3-expense.html`
**Task:** Input boxes for item and expense. *Add Expense* adds the item to a table, and the table shows the total expense at the bottom. Expenses can keep being added.

**Solution:** `addExpense()` validates the inputs, `addRow()` adds a table row, and `updateTotal()` updates the total in the table footer.

Example: Pen 20, Notebook 60, Lunch 120 → **Total Expense 200.00**

## How to run
Open `index.html` to see all three side by side, or open each `q*.html` file separately in a browser.
