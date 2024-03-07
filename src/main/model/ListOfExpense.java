package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list of expenses with a name
public class ListOfExpense implements Writable {
    private String name; // name of owner
    private final List<Expense> expenses; // list of expenses

    // REQUIRES: name is not empty
    // EFFECTS: constructs an empty list of expenses with a name
    public ListOfExpense(String name) {
        this.name = name;
        this.expenses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // EFFECTS: returns an unmodifiable list of expenses in this list
    public List<Expense> getExpenses() {
        return Collections.unmodifiableList(expenses);
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

    // EFFECTS: returns the number of accounts in the list
    public int count() {
        return expenses.size();
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
        for (Expense e : expenses) {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("expenses", expensesToJson());
        return json;
    }

    // EFFECTS: returns expenses in this list as a JSON array
    private JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense e : expenses) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

}
