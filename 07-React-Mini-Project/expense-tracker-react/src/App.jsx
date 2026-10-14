import { useMemo, useState } from 'react'
import Header from './components/Header.jsx'
import BudgetSummary from './components/BudgetSummary.jsx'
import ExpenseForm from './components/ExpenseForm.jsx'
import ExpenseList from './components/ExpenseList.jsx'
import { useLocalStorage } from './hooks/useLocalStorage.js'

const CATEGORIES = ['Food', 'Travel', 'Books', 'Bills', 'Other']

const SAMPLE_EXPENSES = [
  { id: 1, title: 'Hostel mess fee', amount: 3500, category: 'Bills', date: '2026-10-01' },
  { id: 2, title: 'Bus pass', amount: 600, category: 'Travel', date: '2026-10-03' },
  { id: 3, title: 'Data Structures textbook', amount: 850, category: 'Books', date: '2026-10-05' },
  { id: 4, title: 'Lunch with friends', amount: 420, category: 'Food', date: '2026-10-08' },
]

function App() {
  const [expenses, setExpenses] = useLocalStorage('expenses', SAMPLE_EXPENSES)
  const [budget, setBudget] = useLocalStorage('budget', 8000)
  const [filter, setFilter] = useState('All')

  const addExpense = (expense) => {
    setExpenses([...expenses, { ...expense, id: Date.now() }])
  }

  const deleteExpense = (id) => {
    setExpenses(expenses.filter((e) => e.id !== id))
  }

  const totalSpent = useMemo(() => {
    return expenses.reduce((sum, e) => sum + e.amount, 0)
  }, [expenses])

  const filteredExpenses = filter === 'All' ? expenses : expenses.filter((e) => e.category === filter)

  return (
    <div className="container">
      <Header regNo="RA2311003011556" />
      <BudgetSummary budget={budget} totalSpent={totalSpent} setBudget={setBudget} />
      <ExpenseForm categories={CATEGORIES} onAdd={addExpense} />

      <div className="filter">
        Show:{' '}
        <select value={filter} onChange={(e) => setFilter(e.target.value)}>
          <option>All</option>
          {CATEGORIES.map((c) => (
            <option key={c}>{c}</option>
          ))}
        </select>
      </div>

      <ExpenseList expenses={filteredExpenses} onDelete={deleteExpense} />
    </div>
  )
}

export default App
