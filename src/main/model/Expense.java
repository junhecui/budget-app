package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an expense with an amount
public class Expense implements Writable {
    private final String name; // name of expense
    private final double amount; // price of expense

    // REQUIRES: name must not be empty, amount >= 0
    // EFFECTS: constructs an expense with name and amount
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

    @Override
    public JSONObject toJson() { // referred to jsondemo
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("amount", amount);
        return json;
    }

    @Override
    public String toString() {
        return String.format("%s - $%.2f", name, amount);
    }

}
