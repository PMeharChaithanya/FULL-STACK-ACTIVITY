import ExpenseItem from './ExpenseItem.jsx'

function ExpenseList({ expenses, onDelete }) {
  if (expenses.length === 0) {
    return <p>No expenses to show.</p>
  }

  return (
    <table>
      <thead>
        <tr>
          <th>Title</th>
          <th>Category</th>
          <th>Date</th>
          <th>Amount (₹)</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        {expenses.map((expense) => (
          <ExpenseItem key={expense.id} expense={expense} onDelete={onDelete} />
        ))}
      </tbody>
    </table>
  )
}

export default ExpenseList
