import { useRef, useState } from 'react'

function ExpenseForm({ categories, onAdd }) {
  const [title, setTitle] = useState('')
  const [amount, setAmount] = useState('')
  const [category, setCategory] = useState(categories[0])
  const [date, setDate] = useState(new Date().toISOString().slice(0, 10))
  const [error, setError] = useState('')
  const titleRef = useRef(null)

  const handleSubmit = (e) => {
    e.preventDefault()

    if (title.trim() === '') {
      setError('Please enter a title')
      return
    }
    if (Number(amount) <= 0) {
      setError('Amount must be greater than 0')
      return
    }

    onAdd({ title: title.trim(), amount: Number(amount), category, date })
    setTitle('')
    setAmount('')
    setError('')
    titleRef.current.focus()
  }

  return (
    <div className="box">
      <h3>Add Expense</h3>
      <form onSubmit={handleSubmit}>
        <input ref={titleRef} placeholder="Title" value={title} onChange={(e) => setTitle(e.target.value)} />
        <input type="number" placeholder="Amount" value={amount} onChange={(e) => setAmount(e.target.value)} />
        <select value={category} onChange={(e) => setCategory(e.target.value)}>
          {categories.map((c) => (
            <option key={c}>{c}</option>
          ))}
        </select>
        <input type="date" value={date} onChange={(e) => setDate(e.target.value)} />
        <button type="submit">Add</button>
      </form>
      {error && <p className="red">{error}</p>}
    </div>
  )
}

export default ExpenseForm
