function BudgetSummary({ budget, totalSpent, setBudget }) {
  const remaining = budget - totalSpent
  const used = budget > 0 ? Math.round((totalSpent / budget) * 100) : 0

  return (
    <div className="box">
      <h3>Budget</h3>
      <p>
        Monthly budget: ₹
        <input
          type="number"
          className="budget-input"
          value={budget}
          onChange={(e) => setBudget(Number(e.target.value))}
        />
      </p>
      <p>Total spent: ₹{totalSpent}</p>
      <p>Remaining: ₹{remaining}</p>
      <p>Used: {used}%</p>

      {used >= 100 && <p className="red">Budget exceeded!</p>}
      {used >= 80 && used < 100 && <p className="orange">Warning: more than 80% of budget used.</p>}
    </div>
  )
}

export default BudgetSummary
