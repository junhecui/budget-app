package persistence;

import model.Expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkExpense(String name, double amount, Expense expense) {
        assertEquals(name, expense.getName());
        assertEquals(amount, expense.getAmount());
    }
}