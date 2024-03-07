package model;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfExpenseTest {
    private ListOfExpense expenses;
    private Expense car;
    private Expense burger;
    private Expense rent;

    @BeforeEach
    public void setUp() {
        expenses = new ListOfExpense();
        car = new Expense("Toyota RAV-4", 30000.00);
        burger = new Expense("Big Mac", 5.50);
        rent = new Expense("Rent", 2200.00);
    }

    @Test
    public void addExpenseTest() {
        expenses.addExpense(car);
        expenses.addExpense(burger);
        expenses.addExpense(rent);

        assertEquals(car, expenses.getExpense(0));
        assertEquals(burger, expenses.getExpense(1));
        assertEquals(rent, expenses.getExpense(2));
    }

    @Test
    public void removeExpenseTest() {
        expenses.addExpense(car);
        expenses.addExpense(burger);
        expenses.addExpense(rent);

        assertEquals(3, expenses.returnNames().size());

        expenses.removeExpense("Toyota RAV-4");

        assertEquals(2, expenses.returnNames().size());
    }

    @Test
    public void getTotalTest() {
        assertEquals(expenses.getTotal(), 0);

        expenses.addExpense(car);

        assertEquals(expenses.getTotal(), 30000);

        expenses.addExpense(burger);
        expenses.addExpense(rent);

        assertEquals(expenses.getTotal(), 30000+5.50+2200);

        expenses.removeExpense("Toyota RAV-4");

        assertEquals(expenses.getTotal(), 5.50+2200);
    }

    @Test
    public void returnNames() {
        assertTrue(expenses.returnNames().isEmpty());

        expenses.addExpense(car);

        List<String> list = new ArrayList<>();
        list.add("Toyota RAV-4");
        assertIterableEquals(expenses.returnNames(), list);

        expenses.addExpense(burger);
        list.add("Big Mac");
        assertIterableEquals(expenses.returnNames(), list);
    }

    @Test
    public void findCost() {
        expenses.addExpense(car);
        expenses.addExpense(burger);
        expenses.addExpense(rent);

        assertEquals(expenses.findCost("Toyota RAV-4"), 30000);
        assertEquals(expenses.findCost("McChicken"), -1);
        assertEquals(expenses.findCost("Big Mac"), 5.50);
    }
}
