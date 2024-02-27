package model;

public class Expense {
    private final String name;
    private final double amount;

    public Expense(String expenseName, double expenseAmount) {
        this.name = expenseName;
        this.amount = expenseAmount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

}
