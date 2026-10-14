// Reg No: RA2311003011556
// Assignment 4: object with default parameter, 'this' and constant values

console.log("----- Assignment 4: default parameter, this, const -----");

const bankAccount = {
    BANK_NAME: "State Bank of India",   // constant values in the object
    MIN_BALANCE: 1000,
    accountHolder: "Student",
    balance: 5000,

    // default parameter: amount = 500
    deposit(amount = 500) {
        this.balance += amount;
        console.log(`Deposited Rs. ${amount}. New balance: Rs. ${this.balance}`);
    },

    // default parameter: amount = 500
    withdraw(amount = 500) {
        if (this.balance - amount < this.MIN_BALANCE) {
            console.log(`Cannot withdraw Rs. ${amount}. Minimum balance of Rs. ${this.MIN_BALANCE} must be kept.`);
        } else {
            this.balance -= amount;
            console.log(`Withdrew Rs. ${amount}. New balance: Rs. ${this.balance}`);
        }
    },

    showDetails() {
        console.log(`${this.BANK_NAME} | Account holder: ${this.accountHolder} | Balance: Rs. ${this.balance}`);
    }
};

// make the constant values read-only
Object.defineProperty(bankAccount, "BANK_NAME", { writable: false });
Object.defineProperty(bankAccount, "MIN_BALANCE", { writable: false });

bankAccount.showDetails();
bankAccount.deposit(2000);   // given amount
bankAccount.deposit();       // uses default amount 500
bankAccount.withdraw(3000);  // given amount
bankAccount.withdraw();      // uses default amount 500
bankAccount.withdraw(4000);  // blocked by MIN_BALANCE

bankAccount.MIN_BALANCE = 0; // try to change a constant value (ignored)
console.log("After trying to change MIN_BALANCE to 0, it is still:", bankAccount.MIN_BALANCE);
bankAccount.showDetails();
