package model;

import java.util.ArrayList;
import java.util.List;

public class ListOfExpense {
    private final List<Expense> expenses; // list of expenses


    public ListOfExpense() {
        this.expenses = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds an expense to end of list of expenses
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    // REQUIRES: at least one expense in list of expenses
    // MODIFIES: this
    // EFFECTS: removes the expense based on name
    public void removeExpense(String expenseName) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getName().equals(expenseName)) {
                expenses.remove(i);
            }
        }
    }

    // REQUIRES: at least one expense in list
    // EFFECTS: returns the expense with corresponding index
    public Expense getExpense(int index) {
        return expenses.get(index);
    }

    // EFFECTS: adds each expense in the list and returns the total
    public double getTotal() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        return total;
    }

    // EFFECTS: returns a list of the names of all expenses
    public List<String> returnNames() {
        List<String> names = new ArrayList<>();
        for (Expense e: expenses) {
            names.add(e.getName());
        }
        return names;
    }

    // REQUIRES: expense is in list of expenses only once
    // EFFECTS: returns cost of expense that corresponds to string
    public double findCost(String s) {
        for (Expense e : expenses) {
            if (e.getName().equals(s)) {
                return e.getAmount();
            }
        }
        return -1;
    }


}
